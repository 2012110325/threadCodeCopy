package codecopy.com.netflix.hystrix;

import rx.Observable;

import java.util.concurrent.Future;

public interface HystrixExecutable<R> extends HystrixInvokable<R> {

    public R execute();

    public Future<R> queue();

    public Observable<R> observe();
}
