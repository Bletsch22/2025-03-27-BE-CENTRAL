
// 4
public class AsteriskLogger implements Logger{

    @Override
    public void log(String message) {
        System.out.println("***" + message + "***"); // 4 a)
        System.out.println();
    }

    @Override
    public void error(String message) {
        String fault = "***error:".toUpperCase() + message +"***";
        String border = "*".repeat(fault.length() );

        System.out.println(border);
        System.out.println(fault);
        System.out.println(border);
    }
}
