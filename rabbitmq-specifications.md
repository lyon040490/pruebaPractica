# Documento de definición de RabbitMQ - Sistema de pagos

exchanges:
  - name: payments.exchange
    type: direct
    durable: true
    description: "Exchange principal para enviar eventos de pagos"

---

queues:
  - name: payment.status.queue
    durable: true
    description: "Cola para notificar cambios de estatus"
    bindings:
      - exchange: payments.exchange
        routing_key: payment.status.changed

---

messages:
  - PaymentStatus:
  -  description: "Mensaje enviado cuando un pago cambia de estatus"
  -  payload:
      id: string
      status: string
      updatedAt: datetime
   - headers:
      eventType: string
      source: string
