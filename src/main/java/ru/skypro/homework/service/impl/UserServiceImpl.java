package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDTO getUserDTO(User user) {
        return userMapper.userToUserDTO(user);
    }

    public User createUserFromDTO(UserDTO userDTO) {
        return userMapper.userDTOToUser(userDTO);
    }
}
