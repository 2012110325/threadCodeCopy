package demo;

public class ForwardingClientImpl extends ForwardingClient {

    private final Client client;

    public ForwardingClientImpl(Client client) {
        this.client = client;
    }

    @Override
    protected Client delegate() {
        return client;
    }
}
