package tarasevich.nikolai;

import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 02.09.2020
 */
public class MonoDemo {

    @SneakyThrows
    public static void main(String[] args) {
        var monoDemo = new MonoDemo();

        monoDemo.emptyMono()
            .subscribe(System.out::println);

        monoDemo.monoWithNoSignal()
            .subscribe(System.out::println);

        monoDemo.fooMono()
            .subscribe(System.out::println);

        monoDemo.errorMono()
            .subscribe(System.out::println);
    }

    Mono<String> emptyMono() {
        return Mono.empty();
    }

    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    Mono<String> fooMono() {
        return Mono.just("foo");
    }

    Mono<String> errorMono() {
        return Mono.error(IllegalStateException::new);
    }

}
