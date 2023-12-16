package org.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {
    private String orderId;
    private String email;
    private String phoneNumber;
    private long amount;
}
