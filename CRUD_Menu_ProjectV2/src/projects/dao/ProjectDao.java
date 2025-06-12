package projects.dao;

import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;
import util.DaoBase;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import java.sql.PreparedStatement;


public class ProjectDao extends DaoBase {
    private static final String CATEGORY_TABLE = "category";
    private static final String MATERIAL_TABLE = "material";
    private static final String PROJECT_TABLE = "project";
    private static final String PROJECT_CATEGORY_TABLE = "project_category";
    private static final String STEP_TABLE = "step";


    public Project insertProject(Project project) {
        // @formatter:off
        String sql = " "
                + "INSERT INTO " + PROJECT_TABLE + " "
                + "(project_name, estimated_hours, actual_hours, difficulty, notes) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)";
        // @formatter:on

        try (Connection connection = MySqlConnection.getConnection()) {
            startTransaction(connection);

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                setParameter(stmt, 1, project.getProjectName(), String.class);
                setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
                setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
                setParameter(stmt, 4, project.getDifficulty(), Integer.class);
                setParameter(stmt, 5, project.getNotes(), String.class);

                stmt.executeUpdate();

                Integer projectId = getLastInsertId(connection, PROJECT_TABLE);
                commitTransaction(connection);

                project.setProjectId(projectId);
                return project;
            } catch (Exception e) {
                rollbackTransaction(connection);
                throw new DbException(e);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public List<Project> fetchAllProjects ()  {
        String sql = "SELECT * FROM " + PROJECT_TABLE + " ORDER BY project_name";

        try (Connection connection = MySqlConnection.getConnection()) {
            startTransaction(connection);

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                try(ResultSet rs = stmt.executeQuery()){
                    List<Project> projects = new LinkedList<>();
                    while(rs.next()) {
                       // projects.add(extract(rs, Project.class));
                        // Alternative approach that uses straight JDBC method calls. */

                        Project project = new Project();

                        project.setActualHours(rs.getBigDecimal("actual_hours"));
                        project.setDifficulty(rs.getObject("difficulty", Integer.class));
                        project.setEstimatedHours(rs.getBigDecimal("estimated_hours"));
                        project.setNotes(rs.getString("notes"));
                        project.setProjectId(rs.getObject("project_id", Integer.class));
                        project.setProjectName(rs.getString("project_name"));

                      projects.add(project);
                    }
                    return projects;
                }
            }
            catch (Exception e) {
                rollbackTransaction(connection);
                throw new DbException(e.getMessage());

            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public Project fetchProjectById(Integer projectId) {
        String sql = "SELECT * FROM " + PROJECT_TABLE + " WHERE project_id = ?";

        try(Connection connection = MySqlConnection.getConnection()){

            startTransaction(connection);

            try {
                Project project = null;

                try(PreparedStatement stmt = connection.prepareStatement(sql)) {
                    setParameter(stmt, 1, projectId, Integer.class);

                    try(ResultSet rs = stmt.executeQuery()) {
                        if(rs.next()) {

                            project = new Project();
                            project.setProjectId(rs.getInt("project_id"));
                            project.setProjectName(rs.getString("project_name"));
                            project.setEstimatedHours(rs.getBigDecimal("estimated_hours"));
                            project.setActualHours(rs.getBigDecimal("actual_hours"));
                            project.setDifficulty(rs.getObject("difficulty", Integer.class));
                            project.setNotes(rs.getString("notes"));

                        }
                    }
                }
                if(Objects.nonNull(project)) {
                    project.getMaterials().addAll(fetchMaterialsForProject(connection, projectId));
                    project.getSteps().addAll(fetchStepsForProject(connection, projectId));
                    project.getCategories().addAll(fetchCategoriesForProject(connection,projectId));
                }
                commitTransaction(connection);
                return project;
            }
            catch (Exception e) {
                rollbackTransaction(connection);
                throw new DbException(e.getMessage());
            }
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private List<Category> fetchCategoriesForProject(Connection connection, Integer projectId) throws SQLException {
        String sql = "SELECT c.* FROM " + CATEGORY_TABLE + " c " + "JOIN " + PROJECT_CATEGORY_TABLE +
                " pc USING ( category_id) " +
                "WHERE project_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameter(stmt, 1, projectId, Integer.class);

            try(ResultSet rs = stmt.executeQuery()) {
                List<Category> categories = new LinkedList<>();

                while (rs.next()) {
                    categories.add(extract(rs, Category.class));
                }
                return categories;
            }
        }
    }

    private List<Step> fetchStepsForProject(Connection connection, Integer projectId) throws SQLException {
        String sql = "" +"SELECT * FROM " + STEP_TABLE + " WHERE project_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameter(stmt, 1, projectId, Integer.class);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Step> steps = new LinkedList<>();

                while (rs.next()) {
                    steps.add(extract(rs,Step.class));
                }
                return steps;
            }

        }

    }

    private List<Material> fetchMaterialsForProject(Connection connection, Integer projectId) throws SQLException {

        String sql = "SELECT * FROM " + MATERIAL_TABLE + " WHERE project_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameter(stmt, 1 , projectId,Integer.class);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Material> materials = new LinkedList<>();

                while(rs.next()) {
                    materials.add(extract(rs, Material.class));
                }
                return materials;
            }
        }
    }


    public boolean modifyProjectDetails(Project project) throws SQLException {
        String sql =
                "UPDATE " + PROJECT_TABLE +
                        " SET " + "project_name = ?," +
                        " estimated_hours = ?," +
                        " actual_hours = ?," +
                        " difficulty = ?," +
                        " notes = ? "+
                        "WHERE project_id = ?";
        try(Connection connection = MySqlConnection.getConnection()) {
            startTransaction(connection);

            try(PreparedStatement stmt = connection.prepareStatement(sql)) {
                setParameter(stmt, 1, project.getProjectName(),String.class);
                setParameter(stmt,2, project.getEstimatedHours(),BigDecimal.class);
                setParameter(stmt,3, project.getActualHours(),BigDecimal.class);
                setParameter(stmt,4, project.getDifficulty(), Integer.class);
                setParameter(stmt, 5, project.getNotes(),String.class);
                setParameter(stmt,6,project.getProjectId(),Integer.class);

                boolean modified = stmt.executeUpdate() == 1;
                commitTransaction(connection);

                return modified;
            }
            catch (Exception e) {
                rollbackTransaction(connection);
                throw new DbException(e.getMessage());
            }
        }
        catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public boolean deleteProject(Integer projectId) {
        String sql =
                "DELETE FROM " + PROJECT_TABLE + " WHERE project_id = ?";

        try(Connection connection = MySqlConnection.getConnection()) {
            startTransaction(connection);
                try(PreparedStatement stmt = connection.prepareStatement(sql)) {
                    setParameter(stmt, 1,projectId, Integer.class);
                    boolean deleted = stmt.executeUpdate() ==1;

                    commitTransaction(connection);
                    return deleted;
                }
                catch (Exception e) {
                    rollbackTransaction(connection);
                    throw new DbException(e);
                }
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

    }
}




