package org.example.paymentservice.services;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentGateway StripePaymentGateway;

    public String createPaymentLink(Long orderId) throws StripeException {
        return StripePaymentGateway.generatePaymentLink(10000L, orderId.toString());
    }
}
