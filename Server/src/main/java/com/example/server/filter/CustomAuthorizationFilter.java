package com.example.server.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.model.Cliente;
import com.example.server.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                 String authorizationHeader=request.getHeader(AUTHORIZATION);
                 String username=null;
                 String token=null;
                 if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
                         token = authorizationHeader.substring("Bearer ".length());
                         Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                         JWTVerifier verifier = JWT.require(algorithm).build();
                         DecodedJWT decodedJWT = verifier.verify(token);
                         username = decodedJWT.getSubject();
                 }
                    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                        if(validateToken(token,username)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                                    username, null, new ArrayList<>()
                            );
                            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }

                    }
                filterChain.doFilter(request,response);
            }
    private boolean validateToken(String token, String email){
        String userName= getEmailFromToken(token);
        return (userName.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        final Date expiration = getDateFromToke(token);
        return expiration.before(new Date());
    }

    private Date getDateFromToke(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getExpiresAt();
    }

    private String getEmailFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
