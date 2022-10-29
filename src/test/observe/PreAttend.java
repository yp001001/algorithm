package test.observe;

public class PreAttend implements Observer {

    private String message;

    public PreAttend(String message) {
        this.message = message;
    }

    @Override
    public void start() {
        System.out.println(message);
    }
}
