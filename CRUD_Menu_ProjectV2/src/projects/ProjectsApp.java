/*
This application menu uses all of the C.R.U.D operations
 */
package projects;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProjectsApp {

    // instantiate
    private final ProjectService projectService = new ProjectService();
    private Project curProject = new Project();
    private final Scanner scanner = new Scanner(System.in);

    // list of operations in the menu
    private final List<String> operations = List.of(
            "1) Add a Project",
            "2) List Projects",
            "3) Select a Project",
            "4) Update Project Details",
            "5) Delete Project"

    );

    //MAIN
    public static void main(String[] args) {
        new ProjectsApp().processUserSelections();
    }
    private void processUserSelections() {
        boolean done = false; // To run the program continuously

        while (!done) {
            try {
                int selection = getUserSelection();

                switch (selection) {
                    case -1:
                        done = exitMenu();
                        break;
                    case 1:
                        createProject();
                        break;
                    case 2:
                        listProjects();
                        break;
                    case 3:
                        selectProject();
                        break;
                    case 4:
                        updateProjectDetails();
                        break;
                    case 5:
                        deleteProject();
                        break;
                    default:
                        System.out.println("\n" + selection + " is not a valid selection. Try again");
                }
            } catch (Exception e) {
                System.out.println("Try again " + e.getMessage());
                //e.printStackTrace(); // to get more details on error with "e.getMessage()".
            }
        }
    }

    public int getUserSelection() { //Prints operation when selected
        printOperations();

        Integer input = getIntInput("\nEnter a menu selection");

        return Objects.isNull(input) ? -1 : input;
    }

    private void createProject() {      //Create Projects
        String projectName = getStringInput("Enter Project Name");
        BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
        BigDecimal actualHours = getDecimalInput("Enter the actual hours");
        Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
        String notes = getStringInput("Enter the project notes");

        Project project = new Project();

        project.setProjectName(projectName);
        project.setActualHours(estimatedHours);
        project.setActualHours(actualHours);
        project.setDifficulty(difficulty);
        project.setNotes(notes);

        Project dbProject = projectService.addProject(project);
        System.out.println("You have successfully created project: " + dbProject);
    }

//In case the est hours/ act hours are not a full hour
    private BigDecimal getDecimalInput(String prompt) {
        String input = getStringInput(prompt);

        if (Objects.isNull(input)) {
            return null;
        }
        try {
            return new BigDecimal(input).setScale(2);
        } catch (NumberFormatException e) {
            throw new DbException(input + " is not a valid decimal number.");
        }
    }

    private boolean exitMenu() {     //Menu exit message
        System.out.println("Exiting Menu");
        return true;
    }

    public void listProjects() {
        List<Project> projects = projectService.fetchAllProjects();

        System.out.println("Projects: ");

        projects.forEach(project -> System.out.println(" " + project.getProjectId() +
                ": " + project.getProjectName()));
    }

    private void selectProject(){
        listProjects();
        Integer projectId = getIntInput("Enter a project ID to select a project");

        curProject = projectService.fetchProjectById(projectId);

        if (curProject == null) {
            System.out.println("No project is found");
        }else {
            System.out.println("Project Selected: " + curProject.getProjectName());
        }

    }

    private void updateProjectDetails() throws SQLException {
        if (Objects.isNull(curProject)) {
            System.out.println("\nPlease select a project to be updated");
            return;
        }

        String projectName = getStringInput("Enter the updated project Name [" + curProject.getProjectName() + "]");
        BigDecimal estimatedHours = getDecimalInput("Enter estimated hours [" + curProject.getEstimatedHours());
        BigDecimal actualHours = getDecimalInput("Enter the Actual hours [" + curProject.getActualHours() + "]");
        Integer difficulty = getIntInput("Enter difficulty (1-5) [" + curProject.getDifficulty() + "]");
        String notes = getStringInput("Enter project notes [" + curProject.getNotes() + "]");

        Project project = new Project();
        project.setProjectId(curProject.getProjectId());
        project.setProjectName(projectName != null ? projectName : curProject.getProjectName());
        project.setEstimatedHours(estimatedHours != null? actualHours : curProject.getEstimatedHours());
        project.setActualHours(actualHours!= null? actualHours : curProject.getActualHours());
        project.setDifficulty( difficulty != null? difficulty : curProject.getDifficulty());
        project.setNotes(notes != null? notes : curProject.getNotes());

        projectService.modifyProjectDetails(project);
        curProject = projectService.fetchProjectById(curProject.getProjectId());

    }

    private void deleteProject() {

        //Lists all the available projects
        listProjects();

        // asks you to enter a project to delete. If you have no project selected it will return to the main menu.

        Integer projectId = getIntInput("Please select a project to remove");
        Project project = projectService.fetchProjectById(projectId);

        // If you do have a project entered, it will confirm if that is the project you wish to delete.
        // If you do not confirm the delete method will return to the main menu also

        if (projectId == null) {
            System.out.println("No input found, Delete Canceled.\nReturning to Main Menu...");;
            return;
        }

        String confirmation = getStringInput("Are you sure you want to delete Project: " + projectId + ":" + "(Y/N)");

        if (confirmation != null && confirmation.trim().equalsIgnoreCase("Y")) {
            projectService.deleteProject(projectId);
            System.out.println("Project " + project.getProjectName() + " was deleted successfully.");

            // if the current project is being deleted. This updates the selected project to null
            if (curProject != null && curProject.getProjectId().equals(projectId)) {
                curProject = null;
                System.out.println("No longer working with a project.");
            }
        }
        else {
             System.out.println("\nNo confirmation. Delete Canceled\n Returning to main menu");
        }
    }

/*
 Prints a prompt on the console and then gets the user's input from the console.
 It then converts the input to an Integer

 */

    private Integer getIntInput(String prompt) {
        String input = getStringInput(prompt);

        if (Objects.isNull(input)) {
            return null;
        }

        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            throw new DbException(input + " is not a valid number.");
        }
    } // Uses a number input to process menu selections

    private String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        return input.isBlank() ? null : input.trim();
    }

    private void printOperations() {
        System.out.println("\nPress a Menu selections OR press the ENTER key to quit:");

        operations.forEach(line -> System.out.println("  " + line));

        if (Objects.isNull(curProject)){
            System.out.println("\n You are not working with a project.");

         }else if (curProject.getProjectId() != null) {
          System.out.println("\nYou are working with project: " + curProject);
         }
    }
}


