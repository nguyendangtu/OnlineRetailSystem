package miu.edu.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import miu.edu.apigateway.domain.Role;
import miu.edu.apigateway.domain.User;
import miu.edu.apigateway.repository.RoleRepository;
import miu.edu.apigateway.repository.UserRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@Configuration
public class InterceptConfig implements WebFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if ("OPTIONS".equals(exchange.getRequest().getMethod())) {
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.SC_OK));
            return chain.filter(exchange);
        } else {
            String path = exchange.getRequest().getPath().toString();
            if (!isValidPath(path)) {
                HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
                List<String> headers = httpHeaders.get(HttpHeaders.AUTHORIZATION);
                String authHeader = null;
                if (headers != null) {
                    authHeader = headers.get(0);
                }
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED));
                    return exchange.getResponse().writeWith(Mono.empty());
                }
                return verifyToken(exchange, chain, authHeader);
            }
        }

        return chain.filter(exchange);
    }

    public boolean isValidPath(String path) {
        return path.contains("/login") || path.contains("/register");
    }

    public Mono<Void> verifyToken(ServerWebExchange exchange, WebFilterChain chain, String authHeader) {
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        if (null != claims) {
            String name = claims.getSubject();
            User user = userRepository.findByUsername(name);
            List<Role> roles = roleRepository.findByName(user.getRole());
            List<String> paths = new ArrayList<>();
            boolean isUnauthorized = false;
            if (null != roles && roles.size() > 0) {
                String path = exchange.getRequest().getPath().toString();
                roles.forEach(role -> paths.add(role.getPath()));
                if (!paths.contains(path.trim())) {
                    isUnauthorized = true;
                }
            } else {
                isUnauthorized = true;
            }
            if (isUnauthorized) {
                exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED));
                return exchange.getResponse().writeWith(Mono.empty());
            }
        }
        return chain.filter(exchange);
    }


}
