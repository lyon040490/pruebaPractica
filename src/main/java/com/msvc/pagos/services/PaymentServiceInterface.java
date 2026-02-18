package com.msvc.pagos.services;

import com.msvc.pagos.entity.Payment;
import com.msvc.pagos.enums.PaymentStatus;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

public interface PaymentServiceInterface {
	
	/**
	 * Method to create a payment 
	 * @param payment
	 * @return Payment
	 */
	public Payment createPayment(Payment payment);
	
	/**
	 * Method for get a payments status
	 * @param payment
	 * @return Payment
	 */
	public Payment getPaymentStatus(String id);
	
	
	/**
	 * Method for updating the status of a payments 
	 * @param payment
	 * @return Payment
	 */
	public Payment updateStatus(String id, PaymentStatus newStatus);

}
