package demo;

public class InterceptTest {

    public static void main(String[] args) {
        Client client = new ClientImp();

        Client intercept1 = new ForwardingClientImpl(client) {
            @Override
            public void start(String say) {
                System.out.println("拦截器1");
                super.start(say);
            }
        };

        Client intercept2 = new ForwardingClientImpl(intercept1) {
            @Override
            public void start(String say) {
                System.out.println("拦截器2");
                super.start(say);
            }
        };
        intercept2.start("真是要执行的方法");
    }
}
