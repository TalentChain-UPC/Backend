package com.gateway.gateway_service.filter;

import com.gateway.gateway_service.error.AuthorizationHeaderNotFoundException;
import com.gateway.gateway_service.error.JwtNotFoundException;
import com.gateway.gateway_service.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;
    private final RouteValidator routeValidator;

    public AuthenticationFilter(
            JwtUtil jwtUtil,
            RouteValidator routeValidator) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new AuthorizationHeaderNotFoundException();
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7);
                }
                try {
                    if(!jwtUtil.validateToken(authHeader))throw new JwtNotFoundException();
                }catch (Exception e){
                    System.out.println("Error: "+e.getMessage());
                    throw new JwtNotFoundException();
                }
            }

            return chain.filter(exchange);
        });
    }



    public static class Config{

    }
}
