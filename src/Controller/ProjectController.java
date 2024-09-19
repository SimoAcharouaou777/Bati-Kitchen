package Controller;

import Model.Project;
import Service.ProjectService;

public class ProjectController {
    private static ProjectService projectService = new ProjectService();
    public static void createProject(Project project){
        projectService.createProject(project);
    }

    public static void updateProjectProfitMargin(int projectId,double profitMargin){
        projectService.updateProjectProfitMargin(projectId,profitMargin);
    }

}
