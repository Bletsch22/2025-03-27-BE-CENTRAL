public class App {

    public static void main(String[] args){

        Logger AsteriskLogger = new AsteriskLogger();

        Logger SpacedLogger = new SpacedLogger();

        System.out.println("AsteriskLogger: ");
        AsteriskLogger.log("Hello");
        AsteriskLogger.error("Hello");
        System.out.println();

        System.out.println("SpacedLogger: ");
        SpacedLogger.log("World");
        SpacedLogger.error("World");

    }
}
