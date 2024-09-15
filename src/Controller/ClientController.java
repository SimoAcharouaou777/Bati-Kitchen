package Controller;

import Service.ClientService;

public class ClientController {
    private static ClientService clientService = new ClientService();
    public static void addClient(String name, String address, String phone, boolean isProfessional){
        clientService.addClient(name,address,phone,isProfessional);
    }
}
