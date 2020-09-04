package tarasevich.nikolai;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Nikolai Tarasevich
 * @since 02.09.2020
 */
public class StepVerifierDemo {

    public static void main(String[] args) {
        var stepVerifierDemo = new StepVerifierDemo();

        stepVerifierDemo.expectFooBarComplete(Flux.just("foo", "bar"));
//        stepVerifierDemo.expectFooBarError(Flux.just("foo", "bar", new RuntimeException("Exception")));
        stepVerifierDemo.expectSkylerJesseComplete(Flux.just(User.builder().username("swhite").build(), User.builder().username("jpinkman").build()));
        stepVerifierDemo.expect10Elements(Flux.fromIterable(LongStream.rangeClosed(1, 10).boxed().collect(Collectors.toList())));
        stepVerifierDemo.expect3600Elements(() -> Flux.fromIterable(
            LongStream.rangeClosed(1, 3600).boxed().collect(Collectors.toList()))
            .delayElements(Duration.ofSeconds(1))
        );
    }

    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux)
            .expectNext("foo")
            .expectNext("bar")
            .expectComplete()
            .verify();
    }

//========================================================================================

    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
            .expectNext("foo")
            .expectNext("bar")
            .expectError(RuntimeException.class)
            .verify();
    }

//========================================================================================

    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
            .expectNextMatches(user -> user.getUsername().equals("swhite"))
            .expectNextMatches(user -> user.getUsername().equals("jpinkman"))
            .expectComplete()
            .verify();
    }

//========================================================================================

    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux)
            .expectNextCount(10)
            .expectComplete()
            .verify();
    }

//========================================================================================

    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
            .expectSubscription()
            .thenAwait(Duration.ofHours(1))
            .expectNextCount(3600)
            .expectComplete()
            .verify();
    }

}