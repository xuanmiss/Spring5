package com.miss.flux_demo.routerConfig;

import com.miss.flux_demo.handler.TimeHandler;
import com.miss.flux_demo.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
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

    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> timeRouter()
    {
        return route(GET("/time"),req -> timeHandler.getTime(req))
                .andRoute(GET("/date"),timeHandler::getDate)
                .andRoute(GET("/times"),timeHandler::sendTimePerSec);
    }

    @Bean
    public RouterFunction<ServerResponse> userRouter()
    {
        return route(GET("/api/user"),userHandler::findByUsername)
                .andRoute(POST("/api/addUser").and(accept(MediaType.APPLICATION_JSON)),userHandler::save)
                .andRoute(DELETE("/api/deleteUser").and(accept(MediaType.APPLICATION_JSON)),userHandler::deleteByUsername)
                .andRoute(GET("/api/users").and(accept(MediaType.APPLICATION_JSON)),userHandler::findAll)
                .andRoute(GET("/api/userByName").and(accept(MediaType.APPLICATION_JSON)),userHandler::findByName);
    }


}
