package Model;

public class Project {
    private int id;
    private String name;
    private double profitMargin;
    private double totalCost;
    private Client client;
    private ProjectStatus project_status;

    public enum ProjectStatus {
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    public Project(String name, Client client) {
        this.name = name;
        this.profitMargin = 0;
        this.totalCost = 0;
        this.project_status = ProjectStatus.IN_PROGRESS;
        this.client = client;
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getName() {
        return name;
    }
    public double getProfitMargin() {
        return profitMargin;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public ProjectStatus getStatus() {
        return project_status;
    }
    public void setStatus(ProjectStatus project_status) {
        this.project_status = project_status;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Client getClientId() {
        return client;
    }
    public void setClientId(Client client) {
        this.client = client;
    }



}








