package test.responsibility;

public class Client {
    public static void main(String[] args) {
        PrevAttend prevAttend = new PrevAttend();
        AttendIng attendIng = new AttendIng();
        prevAttend.setNextHandler(attendIng);
        prevAttend.execution();
    }
}
