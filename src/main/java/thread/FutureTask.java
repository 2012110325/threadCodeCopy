package thread;


import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

public class FutureTask<V> implements RunnableFuture<V> {

    private volatile int state;
    private static final int NEW  = 0;
    private static final int COMPLETING   = 1;
    private static final int NORMAL       = 2;
    private static final int EXCEPTIONAL  = 3;
    private static final int CANCELLED    = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED  = 6;

    private Callable<V> callable;
    private Object outcome;
    private volatile Thread runner;
    private volatile WaitNode waiters;

    @Override
    public void run() {

    }

    static final class WaitNode {
        volatile Thread thread;
        volatile WaitNode next;
        WaitNode() { thread = Thread.currentThread(); }
    }

    private V report(int s) throws ExecutionException {
        Object x = outcome;
        if (s == NORMAL) {
            return (V)x;
        }

        if (s >= CANCELLED) {
            throw new CancellationException();
        }

        throw new ExecutionException((Throwable)x);
    }

    public FutureTask(Callable<V> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }

        this.callable = callable;
        this.state = NEW;
    }

    public FutureTask(Runnable runnable, V result) {
        // TODO
        this.callable = null; //Executors.callable(null, result);
        this.state = NEW;
    }

    public boolean isCancelled() {
        return state >= CANCELLED;
    }

    public boolean isDone() {
        return state != NEW;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {

        if (!(state == NEW && UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
                mayInterruptIfRunning ? INTERRUPTING : CANCELLED))) {
            return false;
        }

        try {
            if (mayInterruptIfRunning) {
                try {
                    Thread t = runner;
                    if (t != null) {
                        t.interrupt();
                    }
                } finally {
                    UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
                }
            }
        } finally {
            finishCompletion();
        }

        return true;
    }




    private void finishCompletion() {

        for (WaitNode q; (q = waiters) != null;) {
            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {

                for (;;) {
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        LockSupport.unpark(t);
                    }
                    WaitNode next = q.next;
                    if (next == null) {
                        break;
                    }
                    q.next = null;
                    q = next;
                }
            }
        }

        done();
        callable = null;
    }

    protected void done() {}

    private static final sun.misc.Unsafe UNSAFE;
    private static final long stateOffset;
    private static final long runnerOffset;
    private static final long waitersOffset;

    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = FutureTask.class;
            stateOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("state"));
            runnerOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("runner"));
            waitersOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }

    }














}
