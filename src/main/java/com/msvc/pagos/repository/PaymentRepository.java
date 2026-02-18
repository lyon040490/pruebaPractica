package com.msvc.pagos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.msvc.pagos.entity.Payment;

/**
 * @created 17/02/2026
 * @project msvc-payments
 * @author leonardo velazquez
 */
public interface PaymentRepository extends MongoRepository<Payment, String> {

}
