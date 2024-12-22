package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.UserLike;
import com.dooocs.order.dooocs.model.entity.UserFollow;
import com.dooocs.order.dooocs.model.entity.UserCollection;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserInteractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void likeOperationsTest() throws Exception {
        // 创建点赞记录
        UserLike userLike = new UserLike();
        userLike.setUserId(1L);
        userLike.setTargetId(2L);
        userLike.setIp("dd");
        userLike.setTargetType("post");
        userLike.setCreatedAt(new Date());

        // 测试添加点赞
        mockMvc.perform(post("/api/interactions/likes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLike)))
                .andExpect(status().isOk());

        // 测试获取用户点赞列表
        MvcResult getLikesResult = mockMvc.perform(get("/api/interactions/likes/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        UserLike[] likes = objectMapper.readValue(
                getLikesResult.getResponse().getContentAsString(), 
                UserLike[].class);
        assertTrue(likes.length > 0);

        // 测试删除点赞
        Long likeId = likes[0].getId();
        mockMvc.perform(delete("/api/interactions/likes/" + likeId))
                .andExpect(status().isOk());
    }

    @Test
    public void followOperationsTest() throws Exception {
        // 创建关注记录
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(1L);
        userFollow.setTargetId(2L);
        userFollow.setCreatedAt(new Date());

        // 测试添加关注
        mockMvc.perform(post("/api/interactions/follows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFollow)))
                .andExpect(status().isOk());

        // 测试获取关注列表
        MvcResult getFollowingResult = mockMvc.perform(get("/api/interactions/follows/following/1"))
                .andExpect(status().isOk())
                .andReturn();

        UserFollow[] following = objectMapper.readValue(
                getFollowingResult.getResponse().getContentAsString(), 
                UserFollow[].class);
        assertTrue(following.length > 0);

        // 测试获取粉丝列表
        MvcResult getFollowersResult = mockMvc.perform(get("/api/interactions/follows/followers/2"))
                .andExpect(status().isOk())
                .andReturn();

        // 测试取消关注
        Long followId = following[0].getId();
        mockMvc.perform(delete("/api/interactions/follows/" + followId))
                .andExpect(status().isOk());
    }

    @Test
    public void collectionOperationsTest() throws Exception {
        // 创建收藏记录
        UserCollection collection = new UserCollection();
        collection.setUserId(1L);
        collection.setListId(2L);
        collection.setStatus(1);
        collection.setCreatedAt(new Date());

        // 测试添加收藏
        mockMvc.perform(post("/api/interactions/collections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(collection)))
                .andExpect(status().isOk());

        // 测试获取收藏列表
        MvcResult getCollectionsResult = mockMvc.perform(get("/api/interactions/collections/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        UserCollection[] collections = objectMapper.readValue(
                getCollectionsResult.getResponse().getContentAsString(), 
                UserCollection[].class);
        assertTrue(collections.length > 0);

        // 测试删除收藏
        Long collectionId = collections[0].getId();
        mockMvc.perform(delete("/api/interactions/collections/" + collectionId))
                .andExpect(status().isOk());
    }
} 