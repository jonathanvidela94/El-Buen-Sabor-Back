package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.Auth0UserDTO;
import com.backend.elbuensabor.DTO.RoleDTO;
import com.backend.elbuensabor.DTO.UserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.squareup.okhttp.*;
import com.squareup.okhttp.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/v1/auth0")
public class Auth0TokenController {

    @Value("${AUTH0_DOMAIN}")
    private String domain;

    @Value("${AUTH0_API_CLIENT_ID}")
    private String apiClientID;

    @Value("${AUTH0_API_CLIENT_SECRET}")
    private String apiClientSecret;

    @Value("${AUTH0_API_AUDIENCE}")
    private String apiAudience;

    @PostMapping("/token")
    public String getTokenAPI() {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String requestBody = "grant_type=client_credentials&client_id=" + apiClientID +
                    "&client_secret=" + apiClientSecret +
                    "&audience=" + apiAudience;

            RequestBody body = RequestBody.create(mediaType, requestBody);

            Request request = new Request.Builder()
                    .url("https://" + domain + "/oauth/token")
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            String token = jsonNode.get("access_token").asText();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        try {
            String token = getTokenAPI();
            String url = "https://" + domain + "/api/v2/users";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);

            List<UserDTO> users = new ArrayList<>();
            for (JsonNode userNode : jsonNode) {
                String email = userNode.get("email").asText();
                String userId = userNode.get("user_id").asText();
                boolean blocked = userNode.has("blocked") && userNode.get("blocked").asBoolean();

                List<RoleDTO> roles = getUserRoles(userId);

                UserDTO userDTO = new UserDTO(email, blocked, roles);
                users.add(userDTO);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<RoleDTO> getAvailableRoles() {
        try {
            String token = getTokenAPI();
            String url = "https://" + domain + "/api/v2/roles";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            RoleDTO[] roles = mapper.readValue(responseBody, RoleDTO[].class);

            return Arrays.asList(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("users/{id}/roles")
    public List<RoleDTO> getUserRoles(@PathVariable("id") String userId) {
        try {
            String token = getTokenAPI();
            String encodedUserId = URLEncoder.encode(userId, StandardCharsets.UTF_8)
                    .replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId + "/roles";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            RoleDTO[] userRoles = mapper.readValue(responseBody, RoleDTO[].class);

            return Arrays.asList(userRoles);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createNewUser(@org.springframework.web.bind.annotation.RequestBody Auth0UserDTO newUser) {
        try {
            String token = getTokenAPI();
            String url = "https://" + domain + "/api/v2/users";

            JsonObject userObject = new JsonObject();
            userObject.addProperty("connection", "Username-Password-Authentication");
            userObject.addProperty("email", newUser.getEmail());
            userObject.addProperty("password", newUser.getPassword());
            userObject.addProperty("blocked", newUser.isBlocked());

            String requestBody = userObject.toString();
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            HttpStatus httpStatus = HttpStatus.valueOf(response.code());

            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating new user");
        }
    }
    @PostMapping("/users/{roleId}/roles")
    public ResponseEntity<String> assignUserToRole(@PathVariable String roleId, @org.springframework.web.bind.annotation.RequestBody Map<String, List<String>> requestBody) {
        try {
            String token = getTokenAPI();
            String url = "https://" + domain + "/api/v2/roles/" + roleId + "/users";

            OkHttpClient client = new OkHttpClient();

            List<String> users = requestBody.get("users");

            JsonObject requestBodyObject = new JsonObject();
            JsonArray usersArray = new JsonArray();

            for (String user : users) {
                usersArray.add(user);
            }

            requestBodyObject.add("users", usersArray);

            String requestBodyString = requestBodyObject.toString();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBodyString);

            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning user to role.");
        }
    }
    @DeleteMapping("/users/{userId}/roles")
    public ResponseEntity<String> deleteRolesFromUser(@PathVariable String userId, @org.springframework.web.bind.annotation.RequestBody Map<String, List<String>> requestBody) {
        try {
            String token = getTokenAPI();
            String encodedUserId = URLEncoder.encode(userId, StandardCharsets.UTF_8).replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId + "/roles";

            List<String> roles = requestBody.get("roles");

            JsonObject requestBodyObject = new JsonObject();
            JsonArray rolesArray = new JsonArray();

            for (String role : roles) {
                rolesArray.add(role);
            }

            requestBodyObject.add("roles", rolesArray);

            String requestBodyString = requestBodyObject.toString();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBodyString);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting roles from user");
        }
    }

    @PatchMapping("/users/{id}/block")
    public ResponseEntity<String> updateUserBlockedStatus(@PathVariable String id, @org.springframework.web.bind.annotation.RequestBody Map<String, Object> block) {
        try {
            String token = getTokenAPI();
            String encodedUserId = URLEncoder.encode(id, StandardCharsets.UTF_8).replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId;

            OkHttpClient client = new OkHttpClient();
            boolean blocked = (boolean) block.get("blocked");

            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("blocked", blocked);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, requestBody.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json")
                    .patch(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            HttpStatus httpStatus = HttpStatus.valueOf(response.code());

            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user blocked status");
        }
    }

    @GetMapping("/users/{id}/logins-count")
    public ResponseEntity<String> getUserLoginsCount(@PathVariable String id){
        try {
            String token = getTokenAPI();
            String encodedUserId = URLEncoder.encode(id, StandardCharsets.UTF_8).replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId + "?fields=logins_count";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            int loginsCount = jsonNode.get("logins_count").asInt();

            return ResponseEntity.ok(Integer.toString(loginsCount));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/users/{id}/change-password")
    public ResponseEntity<String> changeUserPassword(@PathVariable String id, @org.springframework.web.bind.annotation.RequestBody Map<String, Object> pass){
        try {
            String token = getTokenAPI();
            String encodedUserId = URLEncoder.encode(id, StandardCharsets.UTF_8).replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId;

            OkHttpClient client = new OkHttpClient();
            String password = (String) pass.get("password");

            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("password", password);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, requestBody.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json")
                    .patch(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            HttpStatus httpStatus = HttpStatus.valueOf(response.code());

            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user paswword");
        }
    }
}

