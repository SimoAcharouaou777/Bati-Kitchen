package Service;

import Model.Client;
import Repository.ClientRepository;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();

    public void addClient(Client client){
        clientRepository.addClient(client);
    }
    public void viewAllClients(){
        clientRepository.viewAllClients();
    }
}
