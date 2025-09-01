package com.hospital.management.security;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
  @Value("${app.jwt.secret}")
  private String secretKey;
  @Value("${app.jwt.expiration-ms}")
  private Long expiration;
  public String generateToken(String userName,Set<Role> roles)
  {
	  String csvRoles=roles.stream().map(Enum::name).collect(Collectors.joining(","));
	  Date now=new Date();
	  Date exp=new Date(now.getTime()+expiration);
	  Key key=Keys.hmacShaKeyFor(secretKey.getBytes());
	  return Jwts.builder()
			  .setSubject(userName)
			  .claim("roles",csvRoles)
			  .setIssuedAt(now)
			  .setExpiration(exp)
			  .signWith(key,SignatureAlgorithm.HS256)
			  .compact();
  }
  public String extractUserName(String token)
  {
	  return parse(token).getBody().getSubject();
  }
  public String extractCsvRoles(String token)
  {
	  Object j= parse(token).getBody().get("roles");
	  return j==null?" ":j.toString();
  }
  public boolean isTokenValid(String token)
  {
	  try {
		  parse(token);
		  return true;
	  }
	  catch(JwtException e)
	  {
		  return false;
	  }
  }
  private Jws<Claims> parse(String token)
  {
	  Key key=Keys.hmacShaKeyFor(secretKey.getBytes());
	  return Jwts.parserBuilder()
			  .setSigningKey(key)
			  .build()
			  .parseClaimsJws(token);
  }
}
