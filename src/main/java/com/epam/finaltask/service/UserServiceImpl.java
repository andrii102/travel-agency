package com.epam.finaltask.service;

import com.epam.finaltask.dto.UserDTO;
import com.epam.finaltask.exception.EntityAlreadyExistsException;
import com.epam.finaltask.exception.EntityNotFoundException;
import com.epam.finaltask.model.User;
import com.epam.finaltask.model.Role;
import com.epam.finaltask.exception.StatusCodes;
import com.epam.finaltask.mapper.UserMapper;
import com.epam.finaltask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.epam.finaltask.exception.StatusCodes.ENTITY_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMapper userMapper;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	@Override
	public UserDTO register(UserDTO userDTO) {
		if(userRepo.existsByUsername(userDTO.getUsername()))
			throw new EntityAlreadyExistsException(StatusCodes.DUPLICATE_USERNAME.name(), "This username is already exist");

		User user = userMapper.toUser(userDTO);
		if(user.getRole() == null)
			user.setRole(Role.USER);

		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setBalance(userDTO.getBalance() == null ? 0 : userDTO.getBalance());
		user.setActive(true);
		userRepo.save(user);
		return userMapper.toUserDTO(user);
	}

	@Override
	public UserDTO updateUser(String username, UserDTO userDTO) {
		Optional<User> user = userRepo.findUserByUsername(username);
		if(user.isPresent()) {
			User newUser = userMapper.toUser(userDTO);
			userRepo.save(newUser);
			return userMapper.toUserDTO(newUser);
		}

		throw new RuntimeException(String.valueOf(ENTITY_NOT_FOUND));
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		Optional<User> user = userRepo.findUserByUsername(username);
		if(user.isPresent())
			return userMapper.toUserDTO(user.get());
		throw new EntityNotFoundException(ENTITY_NOT_FOUND.name(),  username);
	}

	// NEEDS TO BE CHECKED
	@Override
	public UserDTO changeAccountStatus(UserDTO userDTO) {
		Optional<User> user = userRepo.findById(UUID.fromString(userDTO.getId()));
		if(user.isPresent()) {
			User newUser = userMapper.toUser(userDTO);
			userRepo.save(newUser);
			return userMapper.toUserDTO(newUser);
		}
		throw new EntityNotFoundException(ENTITY_NOT_FOUND.name(), "User not found");
	}

	@Override
	public UserDTO getUserById(UUID id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return userMapper.toUserDTO(user.get());
		}
		throw new EntityNotFoundException(ENTITY_NOT_FOUND.name(), "User not found");
	}

}
