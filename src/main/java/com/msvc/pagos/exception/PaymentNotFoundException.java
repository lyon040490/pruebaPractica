package com.msvc.pagos.exception;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

import com.msvc.pagos.config.PaymentException;

public class PaymentNotFoundException extends PaymentException {

	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(String paymentId) {
        super("BE-001", "Pago con ID " + paymentId + " no encontrado");
        
    }
}
