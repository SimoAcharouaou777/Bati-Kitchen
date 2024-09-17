package UserInerface;

import Controller.ClientController;
import Controller.LaborController;
import Controller.MaterialController;
import Controller.ProjectController;
import Model.Client;
import Model.Labor;
import Model.Material;
import Model.Project;
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
        System.out.println("=== Create New Project ===");
        System.out.println("1. Select existing client");
        System.out.println("2. Add a new client");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        Client client;
        if(option == 1){
            System.out.println("Enter Client name :");
            String name = sc.nextLine();
            client = ClientController.getClientByName(name);
            if(client == null){
                System.out.println("Client not found");
                return;
            }
        } else if (option == 2) {
            manageClients();
            return;
        }else{
            System.out.println("Invalid option");
            return;
        }

        System.out.print("Enter project name: ");
        String ProjectName = sc.nextLine();
        Project project = new Project(ProjectName,client);
        ProjectController.createProject(project);
        System.out.println("Project created successfully");

        String addMoreMaterials;
        do{
            System.out.println("=== Add Material ===");
            System.out.print("Enter material name: ");
            String materialName = sc.nextLine();
            System.out.print("Enter unit cost of the material: ");
            double unitCost = sc.nextDouble();
            System.out.print("Enter quantity: ");
            double quantity = sc.nextDouble();
            System.out.print("Enter transport cost: ");
            double transportCost = sc.nextDouble();
            System.out.print("Enter quality coefficient: ");
            double qualityCoefficient = sc.nextDouble();
            sc.nextLine();

            Material material = new Material(materialName,  unitCost, quantity,0.0,project.getId(), transportCost, qualityCoefficient);
            MaterialController.addMaterial(material);
            System.out.print("Do you want to add another material? (yes/no): ");
            addMoreMaterials = sc.nextLine();
        }while (addMoreMaterials.equalsIgnoreCase("yes"));

        String addMoreLabor;
        do{
            System.out.println("=== Add Labor ===");
            System.out.print("Enter labor name (e.g., 'Basic Worker'): ");
            String laborName = sc.nextLine();
            System.out.print("Enter hourly rate: ");
            double hourlyRate = sc.nextDouble();
            System.out.print("Enter hours worked: ");
            double hoursWorked = sc.nextDouble();
            System.out.print("Enter productivity coefficient: ");
            double productivity = sc.nextDouble();
            sc.nextLine();

            Labor labor = new Labor(laborName,hourlyRate,hoursWorked ,0.0,project.getId(),hourlyRate, hoursWorked, productivity);
            LaborController.addLabor(labor);
            System.out.print("Do you want to add another labor? (yes/no): ");
            addMoreLabor = sc.nextLine();
        }while(addMoreLabor.equalsIgnoreCase("yes"));


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
        Client client = new Client(name,address,phone,isProfessional);
        ClientController.addClient(client);
        System.out.println("Client added successfully");
    }
    public void viewClients(){
        ClientController.viewAllClients();
    }
    // fixing git issue
}
