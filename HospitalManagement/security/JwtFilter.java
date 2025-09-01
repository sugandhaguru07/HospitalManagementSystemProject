package com.hospital.management.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hospital.management.serviceClasses.UserAccountService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
   private final JwtUtil jwtUtil;
   private final UserAccountService userAccService;
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	    String path = request.getServletPath();
	    if (path.startsWith("/auth")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
	    String auth=request.getHeader(HttpHeaders.AUTHORIZATION);
	    if(auth!=null && auth.startsWith("Bearer "))
	    {
	    	String token=auth.substring(7);
	    	if(jwtUtil.isTokenValid(token))
	    	{
	    		String userName=jwtUtil.extractUserName(token);
	    		UserDetails userDetails=userAccService.loadUserByUsername(userName);
	            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	    	    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    	    SecurityContextHolder.getContext().setAuthentication(authToken);
	    	}
	    }
	    filterChain.doFilter(request,response);
}
   
}
