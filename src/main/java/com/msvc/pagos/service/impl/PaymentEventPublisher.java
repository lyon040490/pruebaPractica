package com.msvc.pagos.service.impl;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.msvc.pagos.config.RabbitConfig;
import com.msvc.pagos.dto.PaymentStatusChangeEvent;
import com.msvc.pagos.entity.Payment;
import com.msvc.pagos.services.PaymentEventPublisherInterface;

@Service
public class PaymentEventPublisher implements PaymentEventPublisherInterface {
	
	private final RabbitTemplate rabbitTemplate;
	
	public PaymentEventPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void publisStatusChangedEvent(Payment payment) {
		
		PaymentStatusChangeEvent event = new PaymentStatusChangeEvent(
				payment.getId(),
				payment.getStatus(),
				payment.getUpdatedAt()
		);
		
		rabbitTemplate.convertAndSend(
				RabbitConfig.EXCHANGE,
				RabbitConfig.ROUTING_KEY,
				event		
		);

	}

}
