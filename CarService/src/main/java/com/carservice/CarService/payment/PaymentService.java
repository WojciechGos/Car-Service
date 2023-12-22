package com.carservice.CarService.payment;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ClientService clientService;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment with id [%s] not found.".formatted(paymentId)
                ));
    }


    public Long createPayment(CreatePaymentRequest createPaymentRequest) {
        Client client = clientService.getClientById(createPaymentRequest.clientId());

        Payment payment = new Payment(
                createPaymentRequest.amount(),
                client,
                PaymentStatus.PENDING
        );

        Payment createdPayment = paymentRepository.save(payment);
        return createdPayment.getId();
    }

    public void updatePayment(Long paymentId, UpdatePaymentRequest updatePaymentRequest) {
        Payment updatedPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment with id [%s] not found.".formatted(paymentId)
                ));

        if(updatePaymentRequest.amount() != null) {
            updatedPayment.setAmount(updatePaymentRequest.amount());
        }

        if(updatePaymentRequest.clientId() != null) {
            Client client = clientService.getClientById(updatePaymentRequest.clientId());
            updatedPayment.setClient(client);
        }

        if(updatePaymentRequest.paymentStatus() != null) {
            updatedPayment.setPaymentStatus(updatePaymentRequest.paymentStatus());
        }

        paymentRepository.save(updatedPayment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
