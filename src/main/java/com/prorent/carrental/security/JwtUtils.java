package com.prorent.carrental.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.prorent.carrental.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${prorent.app.jwtSecret}")
	private String jwtSecret;

	@Value("${prorent.app.jwtExpirationMs}")
	private long jwtExpirationMs;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String generateToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.subject(String.valueOf(userPrincipal.getId()))
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(getSigningKey())
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			logger.error("JWT Token expired {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT Token unsupported {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Malformed JWT Token {}", e.getMessage());
		} catch (SignatureException e) {
			logger.error("Invalid JWT Signature {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT illegal argument exception {}", e.getMessage());
		}

		return false;
	}

	public Long getIdFromJwtToken(String token) {
		return Long.parseLong(
				Jwts.parser()
					.verifyWith(getSigningKey())
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject());
	}
}
