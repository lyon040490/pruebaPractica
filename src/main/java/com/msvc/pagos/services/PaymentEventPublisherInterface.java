package com.msvc.pagos.services;

import com.msvc.pagos.entity.Payment;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

public interface PaymentEventPublisherInterface {
	
	public void publisStatusChangedEvent(Payment payment);

}
