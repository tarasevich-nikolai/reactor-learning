package tarasevich.nikolai;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 04.09.2020
 */
public class ErrorDemo {

    public static void main(String[] args) {
        ErrorDemo errorDemo = new ErrorDemo();

    }

    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono.onErrorReturn(User.SAUL);
    }

//========================================================================================

    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(e -> Flux.fromArray(new User[]{User.SAUL, User.JESSE}));
    }

//========================================================================================

    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> {
            try {
                return capitalizeUser(user);
            } catch (GetOutOfHereException e) {
                throw Exceptions.propagate(e);
            }
        });
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {

        private static final long serialVersionUID = 0L;

    }

}
