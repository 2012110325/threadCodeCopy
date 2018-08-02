package codecopy.com.netflix.hystrix;


import codecopy.com.netflix.hystrix.HystrixInvokable;
import rx.Observable;

public interface HystrixObservable<R> extends HystrixInvokable<R> {

    public Observable<R> observe();

    public Observable<R> toObservable();

}
