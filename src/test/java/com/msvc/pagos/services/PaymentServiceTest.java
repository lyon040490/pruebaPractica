package com.msvc.pagos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.msvc.pagos.entity.Payment;
import com.msvc.pagos.enums.PaymentStatus;
import com.msvc.pagos.repository.PaymentRepository;
import com.msvc.pagos.service.impl.PaymentEventPublisher;
import com.msvc.pagos.service.impl.PaymentService;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentEventPublisher publisher;

    @InjectMocks
    private PaymentService service;

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setId("6994e769c1586041c6303159");
        payment.setConcept("Compra Laptop");
        payment.setAmmount(15000);
        payment.setSender("Juan");
        payment.setRecipient("Tienda Tech");
        payment.setAmountTotal(BigDecimal.valueOf(15300));
        payment.setStatus(PaymentStatus.CREATED);
    }

    @Test
    void testCreatePayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = service.createPayment(payment);

        assertNotNull(result);
        assertEquals(PaymentStatus.CREATED, result.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPaymentStatus_found() {
        when(paymentRepository.findById("6994e769c1586041c6303159")).thenReturn(Optional.of(payment));

        Payment result = service.getPaymentStatus("6994e769c1586041c6303159");

        assertNotNull(result);
        assertEquals("Compra Laptop", result.getConcept());
    }

    @Test
    void testGetPaymentStatus_notFound() {
        when(paymentRepository.findById("6994e769c1586041c6303159")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getPaymentStatus("6994e769c1586041c6303159");
        });

        assertEquals("Pago no encontrado", exception.getMessage());
    }

    @Test
    void testUpdateStatus() {
        when(paymentRepository.findById("6994e769c1586041c6303159")).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment updated = service.updateStatus("6994e769c1586041c6303159", PaymentStatus.COMPLETED);

        assertEquals(PaymentStatus.COMPLETED, updated.getStatus());
        verify(publisher, times(1)).publisStatusChangedEvent(updated);
    }
}
