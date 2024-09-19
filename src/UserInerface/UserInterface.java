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
import java.util.ArrayList;
import java.util.List;
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

    public void createProject() {
        System.out.println("=== Create New Project ===");
        System.out.println("1. Select existing client");
        System.out.println("2. Add a new client");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        Client client;
        while (true) {
            if (option == 1) {
                System.out.println("Enter Client name :");
                String name = sc.nextLine();
                client = ClientController.getClientByName(name);
                if (client == null) {
                    System.out.println("Client not found");
                    return;
                } else {
                    System.out.println("Client Information  : ");
                    System.out.println("Name: " + client.getName());
                    System.out.println("Address: " + client.getAddress());
                    System.out.println("Phone: " + client.getPhone());
                    System.out.println("Is Professional: " + client.isProfessional());
                    System.out.print("Continue with this client ? (yes/no) : ");
                    String continueWithClient = sc.nextLine();
                    if (continueWithClient.equalsIgnoreCase("yes")) {
                        break;
                    }
                }
            } else if (option == 2) {
                manageClients();
                return;
            } else {
                System.out.println("Invalid option");
                return;
            }
        }

        System.out.print("Enter project name: ");
        String ProjectName = sc.nextLine();
        System.out.println("Enter the surface area of the project: ");
        double surfaceArea = sc.nextDouble();
        sc.nextLine();
        Project project = new Project(ProjectName,client);
        ProjectController.createProject(project);
        System.out.println("Project created successfully");

        List<Material> materials = new ArrayList<>();
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
            materials.add(material);

            System.out.print("Do you want to add another material? (y/n): ");
            addMoreMaterials = sc.nextLine();
        }while (addMoreMaterials.equalsIgnoreCase("y"));

        List<Labor> laborList = new ArrayList<>();
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

            Labor labor = new Labor(laborName, 0.0, project.getId(), hourlyRate, hoursWorked, productivity);
            LaborController.addLabor(labor);
            laborList.add(labor);

            System.out.print("Do you want to add another labor? (y/n): ");
            addMoreLabor = sc.nextLine();
        }while(addMoreLabor.equalsIgnoreCase("y"));

        System.out.println("--- Calculate Total Cost ---");
        System.out.print("Apply VAT? (y/n): ");
        boolean applyVAT = sc.nextLine().equalsIgnoreCase("y");
        double vatPercentage = 0.0;
        if (applyVAT) {
            System.out.print("Enter VAT percentage: ");
            vatPercentage = sc.nextDouble();
            sc.nextLine();
            materials = MaterialController.findMaterialByProjectId(project.getId());
            for(Material material : materials){
                material.setVatRate(vatPercentage);
                MaterialController.updateMaterialTVA(material);
            }
            laborList = LaborController.findLaborByProjectId(project.getId());
            for(Labor labor : laborList){
                labor.setVatRate(vatPercentage);
                LaborController.updateLaborTVA(labor);
            }
        }

        System.out.print("Apply profit margin? (y/n): ");
        boolean applyMargin = sc.nextLine().equalsIgnoreCase("y");
        double marginPercentage = 0.0;
        if (applyMargin) {
            System.out.print("Enter profit margin percentage: ");
            marginPercentage = sc.nextDouble();
            sc.nextLine();
            ProjectController.updateProjectProfitMargin(project.getId(),marginPercentage);

        }
        List<Material> materials1 = MaterialController.findMaterialByProjectId(project.getId());
        List<Labor> laborList1 = LaborController.findLaborByProjectId(project.getId());
        double materialsTotal = 0.0;
        for (Material material : materials1) {
            double materialCost = (material.getUnitCost() * material.getQuantity()) + material.getTransportCost();
            materialCost *= material.getQualityCoefficient();
            materialsTotal += materialCost;
        }

        double laborTotal = 0.0;
        for (Labor labor : laborList1) {
            double laborCost = (labor.getHourlyRate() * labor.getHoursWorked()) * labor.getWorkerProductivity();
            laborTotal += laborCost;
        }

        double totalBeforeVAT = materialsTotal + laborTotal;
        double totalVAT = applyVAT ? (totalBeforeVAT * vatPercentage / 100) : 0.0;
        double totalAfterVAT = totalBeforeVAT + totalVAT;
        double profitMargin = applyMargin ? (totalAfterVAT * marginPercentage / 100) : 0.0;
        double totalCost = totalAfterVAT + profitMargin;

        System.out.println("--- Cost Calculation ---");
        System.out.printf("Total materials cost before VAT: %.2f €%n", materialsTotal);
        System.out.printf("Total labor cost before VAT: %.2f €%n", laborTotal);
        System.out.printf("Total cost before VAT: %.2f €%n", totalBeforeVAT);
        System.out.printf("VAT (%.2f%%): %.2f €%n", vatPercentage, totalVAT);
        System.out.printf("Profit Margin (%.2f%%): %.2f €%n", marginPercentage, profitMargin);
        System.out.printf("Total project cost: %.2f €%n", totalCost);

        System.out.println("--- Save the Quote ---");
        System.out.print("Enter quote issue date (dd/MM/yyyy): ");
        String issueDate = sc.nextLine();
        System.out.print("Enter quote validity date (dd/MM/yyyy): ");
        String validityDate = sc.nextLine();
        System.out.print("Save the quote? (y/n): ");
        boolean saveQuote = sc.nextLine().equalsIgnoreCase("y");

        if (saveQuote) {
            // Save the quote to the database or file here
            System.out.println("Quote saved successfully!");
        } else {
            System.out.println("Quote not saved.");
        }

        System.out.println("Project creation process completed.");

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
