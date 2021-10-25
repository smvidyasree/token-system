package com.example.demo.token.file.JwtToken;


	import java.security.Key;
import java.time.Instant;
	import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
	import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
	@Component

	public class JwtlToken {
	public	static final  String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

		static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), 
		                            SignatureAlgorithm.HS256.getJcaName());
		
		public String generateTokent(String password,String fullname) {
			Instant now = Instant.now();
			String[] fullnam = fullname.split(" ");	
			String jwtToken = Jwts.builder()
			        .claim("password", password)
			        .claim("username",fullname)
			        .setSubject(fullnam[0])
			        .setId(UUID.randomUUID().toString())
			        .setIssuedAt(Date.from(now))
			        .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
			        .signWith(hmacKey)
			        .compact();
			return jwtToken;
			
		}
		public static Jws<Claims> parseJwt(String jwtString) {
		    
		    Jws<Claims> jwt = Jwts.parserBuilder()
		            .setSigningKey(hmacKey)
		            .build()
		            .parseClaimsJws(jwtString);

		    return jwt;
		}

	}

