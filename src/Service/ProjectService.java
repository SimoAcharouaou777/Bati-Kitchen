package Service;

import Model.Project;
import Repository.ProjectRepository;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }
    public void updateProjectProfitMargin(int projectId, double profitMargin) {
        projectRepository.updateProjectProfitMargin(projectId, profitMargin);
    }
}
