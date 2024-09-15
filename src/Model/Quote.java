package Model;

import java.time.LocalDate;

public class Quote {
    private int id;
    private int projectId;
    private double estimatedAmount;
    private LocalDate issueDate;
    private LocalDate validityDate;
    private boolean isAccepted;

    public Quote(int projectId, double estimatedAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted) {
        this.projectId = projectId;
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.isAccepted = isAccepted;
    }
    public int getId() {
        return id;
    }
    public int getProjectId() {
        return projectId;
    }
    public double getEstimatedAmount() {
        return estimatedAmount;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public LocalDate getValidityDate() {
        return validityDate;
    }
    public boolean isAccepted() {
        return isAccepted;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }
    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

}
