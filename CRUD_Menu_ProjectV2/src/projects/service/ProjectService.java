package projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private final ProjectDao projectDao = new ProjectDao();

    public Project addProject(Project project) {
        return projectDao.insertProject(project);
    }

    public List<Project> fetchAllProjects() {
        return projectDao.fetchAllProjects();
    }

    public Project fetchProjectById(Integer projectId) {
       return projectDao.fetchProjectById(projectId);
    }

    public void modifyProjectDetails(Project project) throws SQLException {
        if(!projectDao.modifyProjectDetails(project)) {
            throw new DbException("Project with ID: " + project.getProjectId() + " does not exist,");
        }
    }

    public void deleteProject(Integer projectId) {
        if(!projectDao.deleteProject(projectId)) {
            throw new DbException("Project with ID: " + projectId + " does not exist.");
        }
    }
}
