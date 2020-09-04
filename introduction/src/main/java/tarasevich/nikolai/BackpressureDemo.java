package tarasevich.nikolai;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Nikolai Tarasevich
 * @since 04.09.2020
 */
public class BackpressureDemo {

    public static void main(String[] args) {

    }

    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux)
            .expectNextCount(4)
            .expectComplete();
    }

//========================================================================================

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux)
            .expectNext(User.builder().username("skyler").build())
            .expectNext(User.builder().username("jesse").build())
            .thenCancel();

    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
//        return repository.findAll().log();
        return Flux.just(User.builder().username("skyler").build())
            .log();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return Flux.just(User.builder().username("skyler").build())
            .doOnSubscribe(elem -> System.out.println("Starring:"))
            .doOnNext(elem -> System.out.println(elem.getFirstname() + " " + elem.getLastname()))
            .doOnComplete(() -> System.out.println("The end!"));
    }

}
