package tarasevich.nikolai;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 05.09.2020
 */
public class ReactiveToBlockingDemo {

    User monoToValue(Mono<User> mono) {
        return mono.block();
    }

//========================================================================================

    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }

}
