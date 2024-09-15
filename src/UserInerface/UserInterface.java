package UserInerface;

import Controller.ClientController;
import Utils.DatabaseConnection;
import java.sql.Connection;
import java.util.Scanner;

public class UserInterface {
    private Scanner sc = new Scanner(System.in);
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private ClientController clientController = new ClientController();

    public void showMainMenu(){
        while(true){
            System.out.println("=== Welcome to Bati-Cuisine ===");
            System.out.println("1. Create a new project");
            System.out.println("2. View existing projects");
            System.out.println("3. Manage Clients");
            System.out.println("4. Calculate project cost");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch(option){
                case 1:
                    createProject();
                    break;
                case 2:
                    viewProjects();
                    break;
                case 3:
                    manageClients();
                    break;
                case 4:
                    calculateProjectCost();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    DatabaseConnection.closeConnection(connection);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void createProject(){
        System.out.println("Creating a new project...");
    }
    public void viewProjects(){
        System.out.println("Viewing existing projects...");
    }
    public void manageClients(){
        System.out.println("=== Client Management ===");
        System.out.println("1. Add new client");
        System.out.println("2. View all clients");
        System.out.println("3. Go back to main menu");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch(option){
            case 1:
                addClient();
                break;
            case 2:
                viewClients();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid option");
        }
    }
    public void calculateProjectCost(){
        System.out.println("Calculating project cost...");
    }
    public void addClient(){
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
        System.out.print("Enter client address: ");
        String address = sc.nextLine();
        System.out.print("Enter client phone: ");
        String phone = sc.nextLine();
        System.out.print("Is the client a professional? (true/false): ");
        boolean isProfessional = sc.nextBoolean();
        sc.nextLine();
        ClientController.addClient(name,address,phone,isProfessional);
        System.out.println("Client added successfully");
    }
    public void viewClients(){
        System.out.println("Viewing all clients...");
    }
}
