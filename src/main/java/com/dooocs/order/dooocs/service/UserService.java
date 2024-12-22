package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.User;
import com.dooocs.order.dooocs.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public void createUser(User user) {
        userMapper.insertUser(user);
    }
    
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
    
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
    
    public User getUserById(Long id) {
        return userMapper.selectUser(id);
    }
    
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }
} 