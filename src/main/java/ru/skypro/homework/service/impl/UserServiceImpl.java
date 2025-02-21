package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserDTO(User user) {
        return userMapper.userToUserDTO(user);
    }

    @Override
    public User createUserFromDTO(UserDTO userDTO) {
        return userMapper.userDTOToUser(userDTO);
    }

    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(updateUserDTO.getFirstName())
                .setLastName(updateUserDTO.getLastName())
                .setPhone(updateUserDTO.getPhone());

        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }
}
