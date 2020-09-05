package tarasevich.nikolai;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author Nikolai Tarasevich
 * @since 05.09.2020
 */
public class BlockingToReactiveDemo {

    Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
            .subscribeOn(Schedulers.elastic());
    }

//========================================================================================

    Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return flux.publishOn(Schedulers.elastic())
            .doOnNext(repository::save)
            .then();
    }

    interface BlockingRepository<T> {

        List<T> findAll();

        void save(T t);

    }

}
