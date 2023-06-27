package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repo.UserRepo;
import com.blog.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user1= this.dtoToUser(userDto);
		User savedUser= this.userRepo.save(user1);
		return this.userToDto(savedUser);//savedUser ko bhi convert kiya Dto mein
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user1= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getPassword());
		user1.setAbout(userDto.getAbout());
		
		User updatedUser= this.userRepo.save(user1);
		UserDto userDto1= this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= this.userRepo.findAll();
		
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}

	//Converts DATATRANSFEROBJECT of user to User
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());//Setting id by getting id
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user; //Return kiya User(returntype of this method) ka object 
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
 