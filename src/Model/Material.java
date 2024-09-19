package Model;

public class Material extends Component {
    private double transportCost;
    private double qualityCoefficient;
    private double unitCost;
    private double quantity;

    public Material(String name, double unitCost, double quantity, double vatRate, int projectId, double transportCost, double qualityCoefficient) {
        super(name, "Material", vatRate, projectId);
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    // Getters and setters for Material-specific fields
    public double getTransportCost() { return transportCost; }
    public void setTransportCost(double transportCost) { this.transportCost = transportCost; }

    public double getQualityCoefficient() { return qualityCoefficient; }
    public void setQualityCoefficient(double qualityCoefficient) { this.qualityCoefficient = qualityCoefficient; }

    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double calculateMaterialCost() {
        return (unitCost * quantity + transportCost) * qualityCoefficient;
    }
}