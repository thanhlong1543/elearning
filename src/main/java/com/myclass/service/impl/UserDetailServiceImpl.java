package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDetailDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) throw new UsernameNotFoundException("Không tìm thấy tài khoảng!");

		String roleName = user.getRole().getDescription();
		
		// create list role user
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleName));
		UserDetailDto userDetaildto = new UserDetailDto(user.getEmail(), user.getPassword(), authorities);
		
		userDetaildto.setFullname(user.getFullname());
		userDetaildto.setAvatar(user.getAvatar());
		
		return userDetaildto;
	}

}