package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.RankingList;
import com.dooocs.order.dooocs.model.entity.RankingItem;
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
public class RankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void rankingListCRUDTest() throws Exception {
        // 创建排行榜
        RankingList rankingList = new RankingList();
        rankingList.setTitle("Test Ranking");
        rankingList.setDescription("Test Description");
        rankingList.setCreatorId(1L);
        rankingList.setUpdatedAt(new Date());
        rankingList.setCreatedAt(new Date());

        // 测试创建排行榜
        mockMvc.perform(post("/api/rankings/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rankingList)))
                .andExpect(status().isOk());

        // 测试获取所有排行榜
        MvcResult getAllResult = mockMvc.perform(get("/api/rankings/lists"))
                .andExpect(status().isOk())
                .andReturn();

        RankingList[] lists = objectMapper.readValue(
                getAllResult.getResponse().getContentAsString(), 
                RankingList[].class);
        assertTrue(lists.length > 0);

        Long listId = lists[0].getId();

        // 测试更新排行榜
        lists[0].setTitle("Updated Ranking");
        mockMvc.perform(put("/api/rankings/lists/" + listId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lists[0])))
                .andExpect(status().isOk());

        // 测试获取单个排行榜
        MvcResult getOneResult = mockMvc.perform(get("/api/rankings/lists/" + listId))
                .andExpect(status().isOk())
                .andReturn();

        RankingList updatedList = objectMapper.readValue(
                getOneResult.getResponse().getContentAsString(), 
                RankingList.class);
        assertEquals("Updated Ranking", updatedList.getTitle());

        // 测试删除排行榜
        mockMvc.perform(delete("/api/rankings/lists/" + listId))
                .andExpect(status().isOk());
    }

    @Test
    public void rankingItemCRUDTest() throws Exception {
        // 创建排行榜项目
        RankingItem rankingItem = new RankingItem();
        rankingItem.setListId(1L);
        rankingItem.setTitle("Test Item");
        rankingItem.setDescription("Test Description");
        rankingItem.setStatus(1);
        rankingItem.setCreatedAt(new Date());

        // 测试创建排行榜项目
        mockMvc.perform(post("/api/rankings/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rankingItem)))
                .andExpect(status().isOk());

        // 测试获取排行榜项目列表
        MvcResult getItemsResult = mockMvc.perform(get("/api/rankings/lists/1/items"))
                .andExpect(status().isOk())
                .andReturn();

        RankingItem[] items = objectMapper.readValue(
                getItemsResult.getResponse().getContentAsString(), 
                RankingItem[].class);
        assertTrue(items.length > 0);

        Long itemId = items[0].getId();

        // 测试更新排行榜项目
        items[0].setTitle("Updated Item");
        mockMvc.perform(put("/api/rankings/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(items[0])))
                .andExpect(status().isOk());

        // 测试获取单个排行榜项目
        MvcResult getOneResult = mockMvc.perform(get("/api/rankings/items/" + itemId))
                .andExpect(status().isOk())
                .andReturn();

        RankingItem updatedItem = objectMapper.readValue(
                getOneResult.getResponse().getContentAsString(), 
                RankingItem.class);
        assertEquals("Updated Item", updatedItem.getTitle());

        // 测试删除排行榜项目
        mockMvc.perform(delete("/api/rankings/items/" + itemId))
                .andExpect(status().isOk());
    }
} 