package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.RankingItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RankingItemMapper {
    
    @Insert("INSERT INTO ranking_items (id, list_id, title, description, `rank`, media_url, created_at )" +
            "VALUES (#{id}, #{listId}, #{title}, #{description}, #{rank}, #{mediaUrl}, #{createdAt})")
    void insertRankingItem(RankingItem rankingItem);

    @Delete("DELETE FROM ranking_items WHERE id = #{id}")
    void deleteRankingItem(Long id);

    @Update("UPDATE ranking_items SET title = #{title}, description = #{description}, media_url = #{mediaUrl}," +
            "`rank` = #{rank} WHERE id = #{id}")
    void updateRankingItem(RankingItem rankingItem);

    @Select("SELECT * FROM ranking_items WHERE list_id = #{listId} ORDER BY `rank` DESC")
    @Results({
            @Result(column = "list_id", property = "listId"),
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "media_url", property = "mediaUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<RankingItem> selectRankingItemsByListId(Long listId);

    @Select("SELECT * FROM ranking_items WHERE id = #{id}")
    @Results({
            @Result(column = "list_id", property = "listId"),
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "media_url", property = "mediaUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    RankingItem selectRankingItem(Long id);
} 