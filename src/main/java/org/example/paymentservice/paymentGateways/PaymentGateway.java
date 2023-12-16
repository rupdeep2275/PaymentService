package org.example.paymentservice.paymentGateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(Long amount, String orderId) throws StripeException;
}
