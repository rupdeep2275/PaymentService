package org.example.paymentservice.controllers;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dtos.CreatePaymentLinkRequestDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {
    private final PaymentService paymentService;

    @RequestMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto request) throws StripeException {
        return paymentService.createPaymentLink(request.getOrderId());
    }
}
