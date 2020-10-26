package com.myclass.service.impl;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public List<UserDto> findAll() {
		List<UserDto> liserUserDto = new ArrayList<UserDto>();
		List<User> listUser = userRepository.findAll();
		for (User user : listUser) {		
			liserUserDto.add(new UserDto(user.getId(), user.getEmail(), user.getFullname(), user.getAvatar(), user.getPhone(), user.getAddress(),
					user.getRole_id(), user.getRole().getName()));
		}
		return liserUserDto;
	}

	@Override
	public UserDto findByID(int id) {
		User user = userRepository.findById(id).get();
		UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getFullname(), user.getAvatar(), 
				user.getPhone(), user.getAddress(), user.getRole_id());
		return userDto;
	}
	
	@Override
	public boolean findByEmail(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public int add(UserDto userDto) {
		if (userDto.getPassword().compareTo(userDto.getConfirmPassword()) == -1) {
			return -1;
		}
		String password = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12));
		User user = new User(userDto.getId(), userDto.getEmail(), userDto.getFullname(), password, userDto.getAvatar(), userDto.getPhone(),
				userDto.getAddress(), userDto.getRole_id());
		userRepository.save(user);
		return 1;
	}

	@Override
	public int update(UserDto userDto) {
		User user = userRepository.findById(userDto.getId()).get();
		if (userDto.getPassword() != null) {
			String password = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12));
			user.setPassword(password);
		}
		user.setEmail(userDto.getEmail());
		user.setFullname(userDto.getFullname());
		user.setAvatar(userDto.getAvatar());
		user.setPhone(userDto.getPhone());
		user.setAddress(userDto.getAddress());
		user.setRole_id(userDto.getRole_id());
		userRepository.save(user); 
		return 1;
	}
	
	
	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean resgiter(UserDto userDto) {
			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setFullname(userDto.getFullname());
			user.setRole_id(userDto.getRole_id());
			userRepository.save(user);
			return true;
	}

	

}
