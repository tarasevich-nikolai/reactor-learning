package tarasevich.nikolai;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;

/**
 * @author Nikolai Tarasevich
 * @since 02.09.2020
 */
public class FluxDemo {

    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        counter()
            .subscribe(System.out::println);
    }

    public static Flux<String> emptyFlux() {
        return Flux.empty();
    }

    public static Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    public static Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }

    public static Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

    public static Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100))
            .take(10);
    }

}
