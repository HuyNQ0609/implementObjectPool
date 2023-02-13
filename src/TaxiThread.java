import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TaxiThread implements Runnable {
    private final TaxiPool taxiPool;

    public TaxiThread(TaxiPool taxiPool) {
        this.taxiPool = taxiPool;
    }

    @Override
    public void run() {
        takeATaxi();
    }

    private void takeATaxi() {
        try {
            System.out.println("New Client" + Thread.currentThread().getName());
            Taxi taxi = taxiPool.getTaxi();
            TimeUnit.MICROSECONDS.sleep(randInt());
            taxiPool.release(taxi);
            System.out.println("Served the client: " + Thread.currentThread().getName());
        } catch (InterruptedException | TaxiNotFoundException e) {
            System.out.println(">>>Rejected the client: " + Thread.currentThread().getName());
        }
    }

    private int randInt() {
        return new Random().nextInt((1500 - 1000) + 1) + 1000;
    }
}
