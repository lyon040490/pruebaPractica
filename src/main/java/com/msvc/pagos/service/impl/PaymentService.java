package com.msvc.pagos.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.msvc.pagos.entity.Payment;
import com.msvc.pagos.enums.PaymentStatus;
import com.msvc.pagos.exception.PaymentNotFoundException;
import com.msvc.pagos.repository.PaymentRepository;
import com.msvc.pagos.services.PaymentEventPublisherInterface;
import com.msvc.pagos.services.PaymentServiceInterface;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

@Service
public class PaymentService implements PaymentServiceInterface {
	
	private PaymentRepository repository;
	private PaymentEventPublisherInterface publisher;
	
	public PaymentService(PaymentRepository repository,
						  PaymentEventPublisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	@Override
	public Payment createPayment(Payment payment) {
        payment.setStatus(PaymentStatus.CREATED);
        payment.setCreatedAt(LocalDateTime.now());
        return repository.save(payment);
	}

	@Override
	public Payment getPaymentStatus(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException(id));
	}

	@Override
	public Payment updateStatus(String id, PaymentStatus newStatus) {
		Payment payment = repository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException(id));
		
		payment.setStatus(newStatus);
		payment.setUpdatedAt(LocalDateTime.now());
		
		Payment updated = repository.save(payment);
		publisher.publisStatusChangedEvent(updated);
		return updated;
	}

}
