package Model;

public abstract class Component {
    protected String name;
    protected String typeComponent;
    protected double taxRate;

    public Component(String name, String typeComponent, double taxRate) {
        this.name = name;
        this.typeComponent = typeComponent;
        this.taxRate = taxRate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTypeComponent() { return typeComponent; }
    public void setTypeComponent(String typeComponent) { this.typeComponent = typeComponent; }

    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }

}
