package Service;

import Repository.ProjectRepository;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public boolean createProject(String name, double profitMargin){
        if(name == null || name.isEmpty() || profitMargin < 0){
            System.out.println("Invalid project details");
            return false;
        }
        return projectRepository.addProject(name,profitMargin);
    }
}
