package tarasevich.nikolai;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 04.09.2020
 */
public class MergeDemo {

    public static void main(String[] args) {
        var mergeDemo = new MergeDemo();

        var flux1 = Flux.just(User.builder().username("kolya").build());
        var flux2 = Flux.just(User.builder().username("tanya").build());

        mergeDemo.mergeFluxWithInterleave(flux1, flux2)
            .doOnNext(System.out::println)
            .blockLast();
    }

    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.concatWith(flux2);
    }

    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return mono1.mergeWith(mono2);
    }

}
