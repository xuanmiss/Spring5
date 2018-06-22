package com.miss.flux_demo.dao;

import com.miss.flux_demo.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/21
 */
public interface UserRepository extends ReactiveCrudRepository<User,String> {

    Mono<User> findByUsername(String username);
    Flux<User> findByName(String name);
    Mono<Long> deleteByUsername(String username);
    Mono<User> save(User user);
}
