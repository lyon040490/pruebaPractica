package com.msvc.pagos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msvc.pagos.entity.Payment;
import com.msvc.pagos.enums.PaymentStatus;
import com.msvc.pagos.services.PaymentServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

@RestController
@RequestMapping("payments")
public class PaymentController {
	
	private PaymentServiceInterface service;
	
	public PaymentController(PaymentServiceInterface service) {
		this.service = service;
	}
	
    @Operation(summary = "Create a new payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
	@PostMapping
	public ResponseEntity<Payment> create(@RequestBody Payment payment){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.createPayment(payment));
	}

    @Operation(summary = "Get to status payment")
	@GetMapping("/{id}/status")
	public ResponseEntity<Payment> created(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.getPaymentStatus(id));
	}
	
    @Operation(summary = "Update a status payment by id")
	@PatchMapping("/{id}/status")
	public ResponseEntity<Payment> getStatus(@PathVariable String id,
											@RequestParam PaymentStatus status){
		return ResponseEntity.ok(service.updateStatus(id, status));
	}
	
}
