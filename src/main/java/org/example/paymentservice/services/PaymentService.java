package org.example.paymentservice.services;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public String createPaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException {
        return paymentGateway.generatePaymentLink(orderId, email, phoneNumber, amount);
    }
}
