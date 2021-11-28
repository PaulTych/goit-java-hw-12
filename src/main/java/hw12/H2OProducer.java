package hw12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class H2OProducer {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new CombineAtoms());
    private static int waterCount;

    public static void setH2OProducer(int waterCount) {
        H2OProducer.waterCount = waterCount;
    }
    public static void releaseHydrogen() {
        int produced =0;
        while(produced < waterCount) {
            try {
                System.out.print("H");
                produced++;
                BARRIER.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void releaseOxygen () {
        int produced = 0;

        while (produced < waterCount) {
            try {
                System.out.print("O");
                produced++;
                BARRIER.await();
            } catch (InterruptedException |BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CombineAtoms implements Runnable {
        @Override
        public void run() {
            System.out.println(" - Molecula made");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

