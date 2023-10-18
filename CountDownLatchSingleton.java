import java.util.concurrent.CountDownLatch;

public class CountDownLatchSingleton {

    private static CountDownLatchSingleton countDownLatchSingleton;
    private final CountDownLatch countDownLatch;

    private CountDownLatchSingleton(Integer count) {
        countDownLatch = new CountDownLatch(count);
    }

    public static void setCountDown(Integer count) {
        if (countDownLatchSingleton == null) {
            countDownLatchSingleton = new CountDownLatchSingleton(count);
        }
    }

    public static void await() {
        try {
            countDownLatchSingleton.countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void countDown() {
        countDownLatchSingleton.countDownLatch.countDown();
    }
}
