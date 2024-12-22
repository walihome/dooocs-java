package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.RankingList;
import com.dooocs.order.dooocs.model.mapper.RankingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingListService {
    
    @Autowired
    private RankingListMapper rankingListMapper;
    
    public void createRankingList(RankingList rankingList) {
        rankingListMapper.insertRankingList(rankingList);
    }
    
    public void deleteRankingList(Long id) {
        rankingListMapper.deleteRankingList(id);
    }
    
    public void updateRankingList(RankingList rankingList) {
        rankingListMapper.updateRankingList(rankingList);
    }
    
    public RankingList getRankingList(Long id) {
        return rankingListMapper.selectRankingList(id);
    }
    
    public List<RankingList> getAllRankingLists() {
        return rankingListMapper.selectAllRankingLists();
    }
} 