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
    public static boolean clientExists(int clientId){
        return clientService.clientExists(clientId);
    }
    public static Client getClientByName(String name){
        return clientService.getClientByName(name);
    }
    public static Client getClientById(int clientId) { return clientService.getClientById(clientId);}
}
