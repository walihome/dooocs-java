package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.UserLike;
import com.dooocs.order.dooocs.model.entity.UserFollow;
import com.dooocs.order.dooocs.model.entity.UserCollection;
import com.dooocs.order.dooocs.service.UserLikeService;
import com.dooocs.order.dooocs.service.UserFollowService;
import com.dooocs.order.dooocs.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class UserInteractionController {
    
    @Autowired
    private UserLikeService likeService;
    
    @Autowired
    private UserFollowService followService;
    
    @Autowired
    private UserCollectionService collectionService;
    
    // 点赞相关接口
    @PostMapping("/likes")
    public ResponseEntity<Void> addLike(@RequestBody UserLike userLike) {
        likeService.addUserLike(userLike);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/likes/{id}")
    public ResponseEntity<Void> removeLike(@PathVariable Long id) {
        likeService.removeUserLike(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/likes/user/{userId}")
    public ResponseEntity<List<UserLike>> getUserLikes(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getUserLikes(userId));
    }
    
    // 关注相关接口
    @PostMapping("/follows")
    public ResponseEntity<Void> followUser(@RequestBody UserFollow userFollow) {
        followService.followUser(userFollow);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/follows/{id}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long id) {
        followService.unfollowUser(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/follows/following/{userId}")
    public ResponseEntity<List<UserFollow>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }
    
    @GetMapping("/follows/followers/{userId}")
    public ResponseEntity<List<UserFollow>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }
    
    // 收藏相关接口
    @PostMapping("/collections")
    public ResponseEntity<Void> addCollection(@RequestBody UserCollection collection) {
        collectionService.addCollection(collection);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/collections/{id}")
    public ResponseEntity<Void> removeCollection(@PathVariable Long id) {
        collectionService.removeCollection(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/collections/user/{userId}")
    public ResponseEntity<List<UserCollection>> getUserCollections(@PathVariable Long userId) {
        return ResponseEntity.ok(collectionService.getUserCollections(userId));
    }
} 