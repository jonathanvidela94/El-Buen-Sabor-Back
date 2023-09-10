package com.backend.elbuensabor.controllers;

import com.backend.elbuensabor.DTO.OrdersDTO;
import com.backend.elbuensabor.controllers.impl.GenericControllerImpl;
import com.backend.elbuensabor.entities.Orders;
import com.backend.elbuensabor.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/orders")
public class OrdersController extends GenericControllerImpl<Orders, OrdersDTO> {

    @Autowired
    private OrdersService service;

    private static final String ERROR_MESSAGE = "{\"error\":\"Error. Por favor intente nuevamente.\"}";

    @Override
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getOrder(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @GetMapping("/{id}/purchase-history")
    public ResponseEntity<?> getAllByCustomerId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrdersByCustomerId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody OrdersDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveOrder(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrdersDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateOrder(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id, @RequestBody OrdersDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.cancelOrder(id, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @GetMapping("/customerRanking")
    public ResponseEntity<?> getCustomerSummaryBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
            return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerSummaryBetweenDates(startDateTime, endDateTime));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

    @GetMapping("/itemsRanking")
    public ResponseEntity<?> getItemsWithSoldQuantitiesBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

            return ResponseEntity.status(HttpStatus.OK).body(service.getItemsWithSoldQuantitiesBetweenDates(startDateTime, endDateTime));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_MESSAGE);
        }
    }

}
