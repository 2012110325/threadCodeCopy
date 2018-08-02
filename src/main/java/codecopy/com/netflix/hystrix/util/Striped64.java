package codecopy.com.netflix.hystrix.util;

import java.util.Random;

abstract class Striped64 extends Number{

    private static final long serialVersionUID = -3403386352761423917L;

    static final class Cell {
        volatile long p0, p1, p2, p3, p4, p5, p6;
        volatile long value;
        volatile long q0, q1, q2, q3, q4, q5, q6;
        Cell(long x) {
            value = x;
        }

        final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        private static final sun.misc.Unsafe UNSAFE;
        private static final long valueOffset;

        static {
            try {
                UNSAFE = getUnsafe();
                Class<?> ak = Cell.class;
                valueOffset = UNSAFE.objectFieldOffset(ak.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static final class HashCode {
        static final Random rng = new Random();
        int code;
        HashCode() {
            int h = rng.nextInt();
            code = (h == 0) ? 1 : h;
        }
    }

    static final class ThreadHashCode extends ThreadLocal<HashCode> {
        public HashCode initialValue() {
            return new HashCode();
        }
    }

    static final ThreadHashCode threadHashCode = new ThreadHashCode();
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    transient volatile Cell[] cells;

    transient volatile long base;

    transient volatile int busy;

    Striped64() {

    }

    final boolean casBase(long cmp, long val) {

    }

    private static final sun.misc.Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static {
        try {
            UNSAFE = getUnsafe();
            Class<?> sk = Striped64.class;
            baseOffset = UNSAFE.objectFieldOffset(sk.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(sk.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }


    private static sun.misc.Unsafe getUnsafe() {
        return sun.misc.Unsafe.getUnsafe();
    }
}
