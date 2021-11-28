package hw12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WaterProducer {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new CombineAtoms());
    private static int waterCount;

    public static void setWaterCount(int waterCount) {
        WaterProducer.waterCount = waterCount;
    }

    public static class Atom implements Runnable {
        private final String element;
        private int produced = 0;

        public Atom(String element) {
            this.element = element;
        }

        @Override
        public void run() {
            while (produced < waterCount) {
                System.out.print(element);
                produced++;
                try {
                    BARRIER.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
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

