package com.miss.flux_demo.routerConfig;

import com.miss.flux_demo.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author miss
 * <p>
 * Created by miss on 2018/6/21
 */
@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timeRouter()
    {
        return route(GET("/time"),req -> timeHandler.getTime(req))
                .andRoute(GET("/date"),timeHandler::getDate);
    }
}
