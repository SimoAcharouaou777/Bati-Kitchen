package Controller;

import Model.Client;
import Service.ClientService;

public class ClientController {
    private static ClientService clientService = new ClientService();
    public static void addClient(Client client){
        clientService.addClient(client);
    }
    public static void viewAllClients(){
        clientService.viewAllClients();
    }
}
