package test.observe;

import java.util.ArrayList;
import java.util.List;


public class ClassAction implements Action {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void publish() {
        for (Observer observer : observerList) {
            observer.start();
        }
    }
}
