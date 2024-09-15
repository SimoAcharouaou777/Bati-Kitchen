package Service;

import Model.Project;
import Repository.ProjectRepository;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }
}
