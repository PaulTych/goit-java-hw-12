package hw12;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable command) {
        Repeat repeat = command.getClass().getAnnotation(Repeat.class);
        if (repeat != null && repeat.count() != 0) {
            for (int i = 0; i < repeat.count(); i++) {
                command.run();
            }
        }
    }

}
