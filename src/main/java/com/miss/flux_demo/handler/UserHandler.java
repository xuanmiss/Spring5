package com.miss.flux_demo.handler;

import com.miss.flux_demo.dao.UserRepository;
import com.miss.flux_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/22
 */

@Component
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> findByUsername(ServerRequest serverRequest)
    {

        return ok().contentType(MediaType.APPLICATION_JSON).body
                (userRepository.findByUsername(serverRequest.queryParam("username").get()),User.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest)
    {
        Mono<User> user = serverRequest.bodyToMono(User.class);
        Mono<User> responseUser =  user.flatMap(user1 -> userRepository.save(user1)
                                                    .onErrorResume(e ->
                                                            userRepository.findByUsername(user1.getUsername())
                                                                    .flatMap(originalUser ->{
                                                                        user1.setId(originalUser.getId());
                                                                        return userRepository.save(user1);
                                                                    })));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responseUser,User.class);
    }

    public Mono<ServerResponse> deleteByUsername(ServerRequest serverRequest)
    {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userRepository.deleteByUsername(serverRequest.queryParam("username").get()),Long.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest)
    {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userRepository.findAll(),User.class);
    }

    public Mono<ServerResponse> findByName(ServerRequest serverRequest)
    {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userRepository.findByName(serverRequest.queryParam("name").get()),User.class);
    }

}
