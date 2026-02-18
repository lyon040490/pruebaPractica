package com.msvc.pagos.config;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */
public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String code;

    public PaymentException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}