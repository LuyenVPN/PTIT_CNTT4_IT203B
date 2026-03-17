package Session9.engine;
import Session9.entity.Vehicle;
import Session9.exception.CollisionException;
import Session9.util.Logger;

import java.util.concurrent.Semaphore;

public class Intersection {

    private final Semaphore semaphore = new Semaphore(2);

    public void enter(Vehicle v) throws InterruptedException, CollisionException {

        if (!semaphore.tryAcquire()) {
            throw new CollisionException("Quá tải giao lộ!");
        }

        try {
            Logger.log(v.getId() + " đang đi vào ngã tư");
            Thread.sleep(1000);
            Logger.log(v.getId() + " đã đi qua ngã tư");
        } finally {
            semaphore.release();
        }
    }
}
