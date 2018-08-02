package codecopy.com.netflix.hystrix;

import codecopy.com.netflix.hystrix.util.InternMap;

public interface HystrixCommandKey extends HystrixKey {

    class Factory {
        private Factory()  {

        }

        private static final InternMap<String, HystrixCommandKeyDefault> intern
                = new InternMap<>(new InternMap.ValueConstructor<String, HystrixCommandKeyDefault>() {
            @Override
            public HystrixCommandKeyDefault create(String key) {
                return new HystrixCommandKeyDefault(key);
            }
        });

        private static class HystrixCommandKeyDefault extends HystrixKey.HystrixKeyDefault
                implements HystrixCommandKey {
            public HystrixCommandKeyDefault(String name) {
                super(name);
            }
        }

        static int getCommandCount() {
            return intern.size();
        }
    }


}
