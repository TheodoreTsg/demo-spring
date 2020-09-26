package com.example.demo.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTHandling {

	@Value("${spring.security.authentication.jwt.secret}")
	private String secretKey;

	@Value("${spring.security.authentication.jwt.validity}")
	private long tokenValidityInMinutes;
	
	public String createJWT(Integer id,  String username) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expiry = now.plusMinutes(tokenValidityInMinutes);
		final Map<String, Object> claims = new HashMap<>();

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
											.setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
											.setSubject("Authorization")
											.setIssuer("Accenture")
											.signWith(signatureAlgorithm, signingKey);
		builder.setExpiration(Date.from(expiry.atZone(ZoneId.systemDefault()).toInstant()));
		claims.put("id", id.toString());
		claims.put("username", username);
		builder.addClaims(claims);
		
		return builder.compact();
	}
	
	public String getId(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt).getBody();

		return (String) claims.get("id");
	}

	public String getUsername(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt).getBody();

		return (String) claims.get("username");
	}
	
//	private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
	
	public Date getExpirationDateFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
		return (Date) claims.get("exp");
    }
}