//package thread;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.AbstractQueuedSynchronizer;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class ThreadPoolExecutor extends AbstractExecutorService {
//
//
//    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//    private static final int COUNT_BITS = Integer.SIZE - 3;
//    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
//
//    // runState is stored in the High-order bits
//    private static final int RUNNING = -1 << COUNT_BITS;
//    private static final int SHUTDOWN = 0 << COUNT_BITS;
//    private static final int STOP = 1 << COUNT_BITS;
//    private static final int TIDYINT = 2 << COUNT_BITS;
//    private static final int TERMINATED = 3 << COUNT_BITS;
//
//
//    private static int runStateOf(int c) { return c & ~CAPACITY; }
//    private static int workerCountOf(int c) { return c & CAPACITY; }
//    private static int ctlOf(int rs, int wc) { return rs | wc; }
//
//
//    private static boolean runStateLessThan(int c, int s) { return c < s; }
//    private static boolean runStateAtLeast(int c, int s) {  return c >= s; }
//    private static boolean isRunning(int c) { return c < SHUTDOWN; }
//
//    private boolean compareAndIncrementWorkerCount(int expect) { return ctl.compareAndSet(expect, expect + 1); }
//
//    private boolean compareAndDecrementWorkerCount(int expect) { return ctl.compareAndSet(expect, expect - 1); }
//
//    private void decrementWorkerCount() { do {} while (! compareAndDecrementWorkerCount(ctl.get()));}
//
//    private BlockingQueue<Runnable> workQueue;
//
//    private final ReentrantLock mainLock = new ReentrantLock();
//
//
//    private volatile ThreadFactory threadFactory;
//
//    private final class Worker extends AbstractQueuedSynchronizer
//    implements Runnable {
//        private static final long serialVersionUID = 6138294804551838833L;
//        final Thread thread;
//        Runnable firstTask;
//        volatile long completedTasks;
//
//        Worker(Runnable firstTask) {
//            setState(-1);
//            this.firstTask = firstTask;
//            this.thread = getThreadFactory().newThread(this);
//        }
//
//        public void run() {
//            runWorker(this);
//        }
//
//        protected boolean isHeldExclusively() {return getState() != 0; }
//
//
//    }
//
//    final void runWorker (Worker w) {
//
//    }
//
//    public ThreadFactory getThreadFactory() {
//        return threadFactory;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
