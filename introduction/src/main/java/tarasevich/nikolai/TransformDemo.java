package tarasevich.nikolai;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nikolai Tarasevich
 * @since 04.09.2020
 */
public class TransformDemo {

    public static void main(String[] args) {
        TransformDemo transformDemo = new TransformDemo();

        var user = User.builder()
            .username("nick")
            .firstname("nikolai")
            .lastname("tarasevich")
            .build();

        var capitalizedOne = transformDemo.capitalizeOne(Mono.just(user))
            .doOnNext(System.out::println)
            .block();
    }

    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
    }

//========================================================================================

    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> new User(user.getUsername().toUpperCase(), user.getFirstname().toUpperCase(), user.getLastname().toUpperCase()));
    }

//========================================================================================

    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
