package org.example.paymentservice.paymentGateways.razorPay;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Primary
public class RazorPayPaymentGateway implements PaymentGateway {

    private final RazorpayClient razorpayClient;

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
            long expireTimestamp = Instant.now().plus(30, ChronoUnit.MINUTES).getEpochSecond();
            paymentLinkRequest.put("expire_by", expireTimestamp);
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for order #" + orderId);
            JSONObject customer = new JSONObject();
            customer.put("name",phoneNumber);
            customer.put("contact","Rupdeep Dey");
            customer.put("email",email);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            paymentLinkRequest.put("callback_url","https://scaler.com?orderId=" + orderId);
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url").toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return "something is wrong";
        }
    }
}
