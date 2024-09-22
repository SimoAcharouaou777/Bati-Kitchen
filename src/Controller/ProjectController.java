package Controller;

import Model.Project;
import Service.ProjectService;

import java.util.List;

public class ProjectController {
    private static ProjectService projectService = new ProjectService();
    public static void createProject(Project project){
        projectService.createProject(project);
    }

    public static void updateProjectProfitMargin(int projectId,double profitMargin){
        projectService.updateProjectProfitMargin(projectId,profitMargin);
    }
    public static List<Project> getAllProjects(){
        return projectService.getAllProjects();

    }

}
