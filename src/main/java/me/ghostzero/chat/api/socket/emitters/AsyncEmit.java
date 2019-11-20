package me.ghostzero.chat.api.socket.emitters;

public abstract class AsyncEmit {

    abstract public void run() throws Exception;

    public void exec(int sleep) throws Exception {
        Thread.sleep(sleep);
        run();
    }
}
