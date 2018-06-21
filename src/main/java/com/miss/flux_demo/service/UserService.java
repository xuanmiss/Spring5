package com.miss.flux_demo.service;

import com.miss.flux_demo.dao.UserRepository;
import com.miss.flux_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/21
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> save(User user)
    {
        return userRepository.save(user)
                .onErrorResume(e ->
                        userRepository.findByUsername(user.getUsername())
                                .flatMap(originalUser -> {
                                    user.setId(originalUser.getId());
                                    return userRepository.save(user);
                                }));
    }

    public Mono<Long> deleteByUsername(String username)
    {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll()
    {
        return userRepository.findAll();
    }
}
