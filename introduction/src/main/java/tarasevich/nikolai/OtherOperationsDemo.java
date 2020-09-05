package tarasevich.nikolai;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 05.09.2020
 */
public class OtherOperationsDemo {

    //========================================================================================

    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
            .map(tuple3 -> new User(tuple3.getT1(), tuple3.getT2(), tuple3.getT3()));
    }

//========================================================================================

    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.first(mono1, mono2);
    }

//========================================================================================

    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.first(flux1, flux2);
    }

//========================================================================================

    Mono<Void> fluxCompletion(Flux<User> flux) {
        return flux.then();
    }

//========================================================================================

    Mono<User> nullAwareUserToMono(User user) {
        return Mono.justOrEmpty(user);
    }

//========================================================================================

    Mono<User> emptyToSkyler(Mono<User> mono) {
        return mono.switchIfEmpty(Mono.just(User.JESSE));
    }

}
