package Model;

public class Component {
    private int id;
    private int projectId;
    private String name;
    private String type;
    private double unitCost;
    private int quantity;
    private double vatRate;

    public enum ComponentType {
        MATERIAL,
        LABOUR
    }
    public Component(int projectId, String name, String type, double unitCost, int quantity, double vatRate) {
        this.projectId = projectId;
        this.name = name;
        this.type = type;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.vatRate = vatRate;
    }

    public int getId() {
        return id;
    }
    public int getProjectId() {
        return projectId;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getUnitCost() {
        return unitCost;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getVatRate() {
        return vatRate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

}
