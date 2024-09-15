package Service;

import Repository.ClientRepository;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();

    public void addClient(String name, String address, String phone, boolean isProfessional){
        clientRepository.addClient(name,address,phone,isProfessional);
    }
}
