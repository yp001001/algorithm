package test.observe;

public class Client {
    public static void main(String[] args) {
        Observer o1 = new PreAttend("准备上课了...");
        Observer o2 = new AttendIng("正在上课...");
        Action action = new ClassAction();
        action.attach(o1);
        action.attach(o2);
        action.publish();
        //
    }
}
