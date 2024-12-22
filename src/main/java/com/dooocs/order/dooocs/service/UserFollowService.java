package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.UserFollow;
import com.dooocs.order.dooocs.model.mapper.UserFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowService {
    
    @Autowired
    private UserFollowMapper userFollowMapper;
    
    public void followUser(UserFollow userFollow) {
        userFollowMapper.insertUserFollow(userFollow);
    }
    
    public void unfollowUser(Long id) {
        userFollowMapper.deleteUserFollow(id);
    }
    
    public List<UserFollow> getFollowing(Long followerId) {
        return userFollowMapper.selectFollowingByUserId(followerId);
    }
    
    public List<UserFollow> getFollowers(Long followingId) {
        return userFollowMapper.selectFollowersByUserId(followingId);
    }
} 