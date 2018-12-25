/**
 * Build By SALMAN EL FADILI
 **/
package com.crazyideas.security;

import com.crazyideas.models.Thinker;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.crazyideas.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Thinker thinker = new ObjectMapper().readValue(request.getInputStream(), Thinker.class);
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(thinker.getEmail(),thinker.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expiraionTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_DATE, ChronoUnit.MILLIS);
        String token = Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
                .setExpiration(Date.from(expiraionTimeUTC.toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;
        response.getWriter().write(bearerToken);
        response.addHeader(HEADER, bearerToken);
    }
}
