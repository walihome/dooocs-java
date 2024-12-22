package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.RankingList;
import com.dooocs.order.dooocs.model.entity.RankingItem;
import com.dooocs.order.dooocs.service.RankingListService;
import com.dooocs.order.dooocs.service.RankingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {
    
    @Autowired
    private RankingListService rankingListService;
    
    @Autowired
    private RankingItemService rankingItemService;
    
    // 排行榜相关接口
    @PostMapping("/lists")
    public ResponseEntity<Void> createRankingList(@RequestBody RankingList rankingList) {
        rankingListService.createRankingList(rankingList);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/lists/{id}")
    public ResponseEntity<Void> deleteRankingList(@PathVariable Long id) {
        rankingListService.deleteRankingList(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/lists/{id}")
    public ResponseEntity<Void> updateRankingList(@PathVariable Long id, @RequestBody RankingList rankingList) {
        rankingList.setId(id);
        rankingListService.updateRankingList(rankingList);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/lists/{id}")
    public ResponseEntity<RankingList> getRankingList(@PathVariable Long id) {
        return ResponseEntity.ok(rankingListService.getRankingList(id));
    }
    
    @GetMapping("/lists")
    public ResponseEntity<List<RankingList>> getAllRankingLists() {
        return ResponseEntity.ok(rankingListService.getAllRankingLists());
    }
    
    // 排行榜项目相关接口
    @PostMapping("/items")
    public ResponseEntity<Void> createRankingItem(@RequestBody RankingItem rankingItem) {
        rankingItemService.createRankingItem(rankingItem);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteRankingItem(@PathVariable Long id) {
        rankingItemService.deleteRankingItem(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/items/{id}")
    public ResponseEntity<Void> updateRankingItem(@PathVariable Long id, @RequestBody RankingItem rankingItem) {
        rankingItem.setId(id);
        rankingItemService.updateRankingItem(rankingItem);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/lists/{listId}/items")
    public ResponseEntity<List<RankingItem>> getRankingItems(@PathVariable Long listId) {
        return ResponseEntity.ok(rankingItemService.getRankingItemsByListId(listId));
    }
    
    @GetMapping("/items/{id}")
    public ResponseEntity<RankingItem> getRankingItem(@PathVariable Long id) {
        return ResponseEntity.ok(rankingItemService.getRankingItem(id));
    }
} 