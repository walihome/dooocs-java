package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.User;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void userCRUDTest() throws Exception {
        // 创建测试用户
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        // 测试创建用户
        MvcResult createResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        // 测试获取所有用户
        MvcResult getAllResult = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andReturn();

        String content = getAllResult.getResponse().getContentAsString();
        User[] users = objectMapper.readValue(content, User[].class);
        assertTrue(users.length > 0);

        // 获取创建的用户ID
        Long userId = users[users.length - 1].getId();

        // 测试获取单个用户
        MvcResult getOneResult = mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        User retrievedUser = objectMapper.readValue(
                getOneResult.getResponse().getContentAsString(), 
                User.class);
        assertEquals("testUser", retrievedUser.getUsername());

        // 测试更新用户
        retrievedUser.setUsername("updatedUser");
        mockMvc.perform(put("/api/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(retrievedUser)))
                .andExpect(status().isOk());

        // 验证更新
        MvcResult getUpdatedResult = mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andReturn();

        User updatedUser = objectMapper.readValue(
                getUpdatedResult.getResponse().getContentAsString(), 
                User.class);
        assertEquals("updatedUser", updatedUser.getUsername());

        // 测试删除用户
        mockMvc.perform(delete("/api/users/" + userId))
                .andExpect(status().isOk());

        // 验证删除
//        mockMvc.perform(get("/api/users/" + userId))
//                .andExpect(status().isNotFound());
    }
} 