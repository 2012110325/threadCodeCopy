package codecopy.com.netflix.hystrix.util;

public class HystrixRollingNumber {


    static interface Time {
        public long getCurrentTimeInMillis();
    }

    private static class ActualTime implements Time {

        @Override
        public long getCurrentTimeInMillis() {
            return System.currentTimeMillis();
        }
    }
}
