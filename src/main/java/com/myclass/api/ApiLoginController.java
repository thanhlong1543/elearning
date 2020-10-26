package com.myclass.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;
import com.myclass.util.UrlConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = UrlConstants.Admin.API_AUTH)
public class ApiLoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = UrlConstants.Admin.LOGIN)
	public Object login(@RequestBody LoginDto dto) {
		
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPasword());
			authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			Date now = new Date();
			
			String token = Jwts.builder()
			.setSubject(dto.getEmail())
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + 864000000L))
			.signWith(SignatureAlgorithm.HS512, "KeyPass")
			.compact();
			
			return new ResponseEntity<String>(token, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("jwt Token", HttpStatus.OK);
	}
}
