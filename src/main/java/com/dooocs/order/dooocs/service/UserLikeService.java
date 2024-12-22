package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.UserLike;
import com.dooocs.order.dooocs.model.mapper.UserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLikeService {
    
    @Autowired
    private UserLikeMapper userLikeMapper;
    
    public void addUserLike(UserLike userLike) {
        userLikeMapper.insertUserLike(userLike);
    }
    
    public void removeUserLike(Long id) {
        userLikeMapper.deleteUserLike(id);
    }
    
    public List<UserLike> getUserLikes(Long userId) {
        return userLikeMapper.selectUserLikesByUserId(userId);
    }
    
    public UserLike checkUserLike(Long userId, Long targetId, String type) {
        return userLikeMapper.selectUserLike(userId, targetId, type);
    }
} 