package test.observe;

public class AttendIng implements Observer {

    private String message;

    public AttendIng(String message) {
        this.message = message;
    }

    @Override
    public void start() {
        System.out.println(message);
    }
}
