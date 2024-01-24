package com.carservice.CarService.methodsTests;

import com.carservice.CarService.client.Client;
import com.carservice.CarService.client.ClientService;
import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.payment.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Test
    void getAllPayments() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        List<Payment> expectedPayments = Arrays.asList(
            new Payment(BigDecimal.valueOf(100), new Client(), PaymentStatus.PENDING),
            new Payment(BigDecimal.valueOf(150), new Client(), PaymentStatus.SUCCEEDED)
        );

        when(paymentRepository.findAll()).thenReturn(expectedPayments);

        List<Payment> actualPayments = paymentService.getAllPayments();

        assertEquals(expectedPayments, actualPayments);
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void getPaymentById() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        Long paymentId = 1L;
        Payment expectedPayment = new Payment(BigDecimal.valueOf(100), new Client(), PaymentStatus.PENDING);

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(expectedPayment));

        Payment actualPayment = paymentService.getPaymentById(paymentId);

        assertEquals(expectedPayment, actualPayment);
        verify(paymentRepository, times(1)).findById(paymentId);
    }

    @Test
    void getPaymentById_NotFound() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        Long paymentId = 1L;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> paymentService.getPaymentById(paymentId));
        verify(paymentRepository, times(1)).findById(paymentId);
    }

    @Test
    void createPayment() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(BigDecimal.valueOf(120), 1L);

        Client client = new Client();
        when(clientService.getClientById(1L)).thenReturn(client);

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> {
            Payment argument = invocation.getArgument(0);
            argument.setId(1L);
            return argument;
        });

        Long createdPaymentId = paymentService.createPayment(createPaymentRequest);

        assertNotNull(createdPaymentId);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void updatePayment() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        Long paymentId = 1L;
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest(BigDecimal.valueOf(50), 2L, PaymentStatus.PENDING);

        Payment existingPayment = new Payment(BigDecimal.valueOf(30), new Client(), PaymentStatus.EXPIRED);
        existingPayment.setId(paymentId);

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));

        Client client = new Client();
        when(clientService.getClientById(2L)).thenReturn(client);

        when(paymentRepository.save(any(Payment.class))).thenReturn(existingPayment);

        paymentService.updatePayment(paymentId, updatePaymentRequest);

        verify(paymentRepository, times(1)).findById(paymentId);
        verify(paymentRepository, times(1)).save(existingPayment);

        assertEquals(BigDecimal.valueOf(50), existingPayment.getAmount());
        assertEquals(client, existingPayment.getClient());
        assertEquals(PaymentStatus.PENDING, existingPayment.getPaymentStatus());
    }

    @Test
    void deletePayment() {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        ClientService clientService = Mockito.mock(ClientService.class);

        PaymentService paymentService = new PaymentService(paymentRepository, clientService);

        Long paymentId = 1L;

        paymentService.deletePayment(paymentId);

        verify(paymentRepository, times(1)).deleteById(paymentId);
    }
}
