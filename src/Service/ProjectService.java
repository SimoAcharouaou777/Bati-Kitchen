package Service;

import Model.Project;
import Repository.ProjectRepository;

import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }
    public void updateProjectProfitMargin(int projectId, double profitMargin) {
        projectRepository.updateProjectProfitMargin(projectId, profitMargin);
    }
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }
    public void updateProjectTotalCost(int projectId, double totalCost) {
        projectRepository.updateProjectTotalCost(projectId, totalCost);
    }
}
