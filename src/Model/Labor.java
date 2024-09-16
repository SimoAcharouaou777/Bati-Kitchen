package Model;

public class Labor extends  Component{
    private double hourlyRate;
    private double hoursWorked;
    private double productivity;

    public Labor(String name, String typeComponent, double taxRate, double hourlyRate, double hoursWorked, double productivity) {
        super(name, typeComponent, taxRate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.productivity = productivity;
    }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getProductivity() { return productivity; }
    public void setProductivity(double productivity) { this.productivity = productivity; }

    public double calculateLaborCost() {
        return hourlyRate * hoursWorked * productivity;
    }
}
