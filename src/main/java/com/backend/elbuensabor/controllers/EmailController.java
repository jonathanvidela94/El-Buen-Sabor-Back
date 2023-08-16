package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.services.impl.EmailServiceImp;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/email/")
public class EmailController {

    @Autowired
    private EmailServiceImp emailServiceImp;

    @PostMapping(path = "/send-pdf-email", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendPdfEmail(@RequestParam("email") String email, @RequestParam("pdf") MultipartFile pdfFile) {
        try {
            emailServiceImp.sendEmailWithAttachment(email, "Pedido de El Buen Sabor", "Puedes descargar tu factura.", pdfFile.getBytes());
            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (MessagingException | IOException e) {
            return new ResponseEntity<>("Unable to send the email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
