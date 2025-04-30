public class SpacedLogger implements Logger { // this class implements the Logger interface as a way of "connecting the dots"

    private String spaces(String message) { //Spaces method puts a space between each character when this class is called
        StringBuilder space = new StringBuilder();
        for (char c : message.toCharArray()) {
            space.append(c).append(' ');
        }
        return space.toString().trim(); // removes the trailing space
    }

    @Override
    public void log(String message) {
        System.out.println(spaces(message));
    }

    @Override
    public void error(String message) {
        System.out.println("ERROR: " + spaces(message));
    }
}
