package codecopy.com.netflix.hystrix;

import java.util.concurrent.ConcurrentHashMap;

public interface HystrixCollapserKey {

    public String name();

    public static class Factory {

        private Factory() {

        }

        private static ConcurrentHashMap<String, HystrixCollapserKey> intern = new ConcurrentHashMap<>();

        public static HystrixCollapserKey asKey(String name) {
            HystrixCollapserKey k = intern.get(name);

            if (k == null) {
                intern.putIfAbsent(name, new HystrixCollapserKeyDefault(name));
            }

            return intern.get(name);
        }

        private static class HystrixCollapserKeyDefault implements HystrixCollapserKey {

            private String name;

            private HystrixCollapserKeyDefault(String name) {
                this.name = name;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String toString() {
                return name;
            }
        }
    }
}
