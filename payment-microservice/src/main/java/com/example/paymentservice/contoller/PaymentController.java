package com.example.paymentservice.contoller;

import com.example.paymentservice.dtos.InitiatePaymentDTO;
import com.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    public PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentDTO request) {
        return paymentService.initiatePayment(request.getOrderId(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAmount());
    }
}
