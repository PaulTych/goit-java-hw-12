package hw12;

public class TaskTester {

    public static void main(String[] strings) throws InterruptedException {
        System.out.println("----------------------------Task 1 V1----------------------------------------------");
        WaterProducer.setWaterCount(10);
        Thread h1 = new Thread(new WaterProducer.Atom("H"));
        h1.start();
        Thread h2 = new Thread(new WaterProducer.Atom("H"));
        h2.start();
        Thread o = new Thread(new WaterProducer.Atom("O"));
        o.start();

        Thread.sleep(2000);
        System.out.println("----------------------------Task 1 V2----------------------------------------------");
        H2OProducer.setH2OProducer(10);
        Thread h1_2 = new Thread(H2OProducer::releaseHydrogen);
        h1_2.start();
        Thread h2_2 = new Thread(H2OProducer::releaseHydrogen);
        h2_2.start();
        Thread o2 = new Thread(H2OProducer::releaseOxygen);
        o2.start();
        Thread.sleep(2000);

        System.out.println("----------------------------Task 2 ------------------------------------------------");
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(10);
        customThreadPoolExecutor.execute(new MyRunnable());
    }
}

@Repeat(count = 6)
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello!");
    }
}
