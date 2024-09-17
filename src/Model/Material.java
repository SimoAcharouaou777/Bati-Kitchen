package Model;

public class Material extends Component {
    private double transportCost;
    private double qualityCoefficient;

    public Material(String name, double unitCost, double quantity, double vatRate, int projectId, double transportCost, double qualityCoefficient) {
        super(name, unitCost, quantity, "Material", vatRate, projectId);
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    // Getters and setters for Material-specific fields
    public double getTransportCost() { return transportCost; }
    public void setTransportCost(double transportCost) { this.transportCost = transportCost; }

    public double getQualityCoefficient() { return qualityCoefficient; }
    public void setQualityCoefficient(double qualityCoefficient) { this.qualityCoefficient = qualityCoefficient; }

    public double calculateMaterialCost() {
        return (getUnitCost() * getQuantity() + transportCost) * qualityCoefficient;
    }
}