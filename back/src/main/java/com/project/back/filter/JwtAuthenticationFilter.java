package com.project.back.filter;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;


import com.project.back.entity.UserEntity;
import com.project.back.provider.JwtProvider;
import com.project.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{
  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException 
    {
    try 
    {
      String token = parseBearerToken(request);
      if (token == null) 
      {
        filterChain.doFilter(request, response);
        return;
      }
  
      String userEmailId = jwtProvider.validate(token);
      if (userEmailId == null) 
      {
        filterChain.doFilter(request, response);
        return;
      }

      UserEntity userEntity  = userRepository.findByUserEmailId(userEmailId);
      if (userEntity == null) 
      {
        filterChain.doFilter(request, response);
        return;
      }
      String role = userEntity.getUserRole();

      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(role));

      AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmailId, null, authorities);
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authenticationToken);

      SecurityContextHolder.setContext(securityContext);
    } 
    catch(Exception exception) 
    {
      exception.printStackTrace();
    }
    filterChain.doFilter(request, response);
  }
  
  private String parseBearerToken(HttpServletRequest request) 
  {
    String authorization = request.getHeader("Authorization");

    boolean hasAuthorization = StringUtils.hasText(authorization);
    if (!hasAuthorization) return null;

    boolean isBearer = authorization.startsWith("Bearer ");
    if (!isBearer) return null;

    String token = authorization.substring(7);
    return token;
  }
}
/* 분석 완료 */