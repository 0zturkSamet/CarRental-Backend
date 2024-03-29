package com.prorent.carrental.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.prorent.carrental.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
	private static final Logger logger=LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${prorent.app.jwtSecret}")
	private String jwtSecret;

	@Value("${prorent.app.jwtExpirationMs}")
	private long jwtExpirationMs;

	public String generateToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject("" + (userPrincipal.getId())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			logger.error("JWT Token expired {}",e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT Token unsupported {}",e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Malformed JWT Token {}",e.getMessage());
		} catch (SignatureException e) {
			logger.error("Invalid JWT Signature {}",e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT illegal argument exception {}",e.getMessage());
		}
		
		return false;
	}
	
	
	public Long getIdFromJwtToken(String token) {
		return Long.parseLong( Jwts.parser().setSigningKey(jwtSecret).
				parseClaimsJws(token).getBody().getSubject());
	}
	
}
