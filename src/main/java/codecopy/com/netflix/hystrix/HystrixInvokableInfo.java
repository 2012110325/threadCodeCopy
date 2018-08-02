package codecopy.com.netflix.hystrix;

public interface HystrixInvokableInfo<R> {

    HystrixCommandGroupKey getCommandGroup();

    HystrixCommandKey getCommandKey();

    HystrixThreadPoolKey getThreadPoolKey();

    String getPublicCacheKey();

    HystrixCollapserKey getOriginatingCollapserKey();



}
