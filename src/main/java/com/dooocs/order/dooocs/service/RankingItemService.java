package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.RankingItem;
import com.dooocs.order.dooocs.model.mapper.RankingItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingItemService {
    
    @Autowired
    private RankingItemMapper rankingItemMapper;
    
    public void createRankingItem(RankingItem rankingItem) {
        rankingItemMapper.insertRankingItem(rankingItem);
    }
    
    public void deleteRankingItem(Long id) {
        rankingItemMapper.deleteRankingItem(id);
    }
    
    public void updateRankingItem(RankingItem rankingItem) {
        rankingItemMapper.updateRankingItem(rankingItem);
    }
    
    public List<RankingItem> getRankingItemsByListId(Long rankingListId) {
        return rankingItemMapper.selectRankingItemsByListId(rankingListId);
    }
    
    public RankingItem getRankingItem(Long id) {
        return rankingItemMapper.selectRankingItem(id);
    }
} 