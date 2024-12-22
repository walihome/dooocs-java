package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.UserCollection;
import com.dooocs.order.dooocs.model.mapper.UserCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionService {
    
    @Autowired
    private UserCollectionMapper userCollectionMapper;
    
    public void addCollection(UserCollection collection) {
        userCollectionMapper.insertUserCollection(collection);
    }
    
    public void removeCollection(Long id) {
        userCollectionMapper.deleteUserCollection(id);
    }
    
    public List<UserCollection> getUserCollections(Long userId) {
        return userCollectionMapper.selectCollectionsByUserId(userId);
    }
    
    public UserCollection checkCollection(Long userId, Long targetId, String type) {
        return userCollectionMapper.selectUserCollection(userId, targetId, type);
    }
} 