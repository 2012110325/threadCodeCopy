package demo;

public abstract class ForwardingClient extends Client {
    protected abstract Client delegate();

    @Override
    public void start(String say) {
        delegate().start(say);
    }
}
