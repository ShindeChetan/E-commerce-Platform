package com.example.paymentservice.paymentgateways;

import java.util.Random;

public class PaymentGatewayStrategyChooser {
    private RazorpayPaymentGateway razorpayPaymentGateway;
    private  StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayStrategyChooser(RazorpayPaymentGateway razorpayPaymentGateway,
                                         StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway(){
//        int randomInt = new Random().nextInt();
//
//        if (randomInt%2 == 0)
//        {
//            return razorpayPaymentGateway;
//        }
//        return stripePaymentGateway;

        return razorpayPaymentGateway;
    }
}
