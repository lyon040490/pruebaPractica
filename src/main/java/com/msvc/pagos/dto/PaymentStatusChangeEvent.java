package com.msvc.pagos.dto;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

import java.io.Serializable;
import java.time.LocalDateTime;

import com.msvc.pagos.enums.PaymentStatus;

public class PaymentStatusChangeEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	private String paymentId;
	private PaymentStatus status;
	private LocalDateTime updatedAt;
	
	public PaymentStatusChangeEvent(String paymentId, PaymentStatus status, LocalDateTime updatedAt) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.updatedAt = updatedAt;
	}

	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
