package com.carservice.CarService.client;

import com.carservice.CarService.exception.ResourceNotFoundException;
import com.carservice.CarService.vehicles.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id [%s] not found.".formatted(clientId)
                ));
    }


    public Long createClient(Client client) {
        Client createdClient = clientRepository.save(client);
        return createdClient.getId();
    }

    public void updateClient(Long clientId, Client client) {
        Client updatedClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with id [%s] not found.".formatted(clientId)
                ));

        updatedClient.setName(client.getName());
        updatedClient.setSurname(client.getSurname());
        if(client.getEmail() != null) {
            updatedClient.setEmail(client.getEmail());
        }
        if(client.getPhoneNumber() != null) {
            updatedClient.setPhoneNumber(client.getPhoneNumber());
        }

        clientRepository.save(updatedClient);
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }


    public Client getClientEntityById(Long clientId){
        return clientRepository.findById(clientId).orElseThrow(()-> new ResourceNotFoundException("Vehicle with id [%s] not found".formatted(clientId)));
    }
}
