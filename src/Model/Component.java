package Model;

public abstract class Component {
    private int id;
    private String name;
    private String componentType;
    private double vatRate;
    private int projectId;

    public Component(String name, String componentType, double vatRate, int projectId) {
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
        this.projectId = projectId;
    }

    // Getters and setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getComponentType() { return componentType; }
    public void setComponentType(String componentType) { this.componentType = componentType; }

    public double getVatRate() { return vatRate; }
    public void setVatRate(double vatRate) { this.vatRate = vatRate; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
}