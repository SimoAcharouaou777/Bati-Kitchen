package Model;

public class Labor extends Component {
    private double hourlyRate;
    private double hoursWorked;
    private double workerProductivity;

    public Labor(String name, double vatRate, int projectId, double hourlyRate, double hoursWorked, double workerProductivity) {
        super(name,"Labor", vatRate, projectId);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.workerProductivity = workerProductivity;
    }

    // Getters and setters for Labor-specific fields
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getWorkerProductivity() { return workerProductivity; }
    public void setWorkerProductivity(double workerProductivity) { this.workerProductivity = workerProductivity; }

    public double calculateLaborCost() {
        return hourlyRate * hoursWorked * workerProductivity;
    }


}