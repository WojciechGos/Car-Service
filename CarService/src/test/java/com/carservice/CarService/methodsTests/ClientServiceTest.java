package com.carservice.CarService.methodsTests;

import com.carservice.CarService.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.carservice.CarService.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Test
    void createClient() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        Client clientToCreate = new Client();
        clientToCreate.setName("John");
        clientToCreate.setSurname("Doe");
        clientToCreate.setEmail("john.doe@gmail.com");
        clientToCreate.setPhoneNumber("123456789");

        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientToCreate);
        Long createdClientId = clientService.createClient(clientToCreate);
        assertEquals(clientToCreate.getId(), createdClientId);

        Mockito.verify(clientRepository, Mockito.times(1)).save(Mockito.any(Client.class));
    }

    @Test
    void getAllClients() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876"));
        clients.add(new Client("Alex", "Maj", "alex@gmail.com", "555432897"));

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> retrievedClients = clientService.getAllClients();

        assertEquals(clients, retrievedClients);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientById() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        Long clientId = 1L;
        Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        wojtek.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(wojtek));

        Client retrievedClient = clientService.getClientById(clientId);

        assertEquals(wojtek, retrievedClient);
        verify(clientRepository, times(1)).findById(clientId);
        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientById(2L));
    }

    @Test
    void updateClient() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        Long clientId = 1L;
        Client existingClient = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        existingClient.setId(clientId);
        Client updatedClientData = new Client("Michael", "White", "michael@gmail.com", "234567890");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.updateClient(clientId, updatedClientData);

        assertEquals(updatedClientData.getName(), existingClient.getName());
        assertEquals(updatedClientData.getSurname(), existingClient.getSurname());
        assertEquals(updatedClientData.getEmail(), existingClient.getEmail());
        assertEquals(updatedClientData.getPhoneNumber(), existingClient.getPhoneNumber());


        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void deleteClient() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        Long clientId = 1L;

        clientService.deleteClient(clientId);
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void getClientEntityById() {
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

        ClientService clientService = new ClientService(clientRepository);
        Long clientId = 1L;
        Client wojtek = new Client("Wojciech", "Kowalski", "wojtek@gmail.com", "345234876");
        wojtek.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(wojtek));

        Client retrievedClient = clientService.getClientEntityById(clientId);
        assertEquals(wojtek, retrievedClient);

        verify(clientRepository, times(1)).findById(clientId);
        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientEntityById(2L));
    }
}
