package com.miss.flux_demo.handler;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/21
 */
@Component
public class TimeHandler {

    public Mono<ServerResponse> getTime(ServerRequest serverRequest)
    {
        return ok().contentType(MediaType.APPLICATION_JSON).body
                (Mono.just("Now is "+ new SimpleDateFormat("HH:mm:ss").format(new Date())),String.class);
    }

    public Mono<ServerResponse> getDate(ServerRequest serverRequest)
    {
        return ok().contentType(MediaType.APPLICATION_JSON).body
                (Mono.just("Now is "+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())),String.class);
    }
}
