package test.responsibility;


public abstract class Handler {

    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public final void execution() {
        this.handler();
        if (this.nextHandler != null) {
            nextHandler.handler();
        }
    }

    public abstract void handler();
}
