package guava.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * http://ifeve.com/google-guava-listenablefuture/
 */
public class ListenableFutureTester {
    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListeningExecutorService callbackService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Explosion> explosion = service.submit(ListenableFutureTester::pushBigRedButton);
        Futures.addCallback(explosion, new FutureCallback<Explosion>() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(Explosion explosion) {
                walkAwayFrom(explosion);
            }

            public void onFailure(Throwable thrown) {
                battleArchNemesis(thrown); // escaped the explosion!
            }
        }, callbackService);

    }

    private static void battleArchNemesis(Throwable throwable) {

    }

    private static void walkAwayFrom(Explosion explosion) {
        System.out.println(explosion);
    }

    private static Explosion pushBigRedButton() {
        return new Explosion();
    }
}

class Explosion {
    @Override
    public String toString() {
        return "Explosion{1}";
    }
}