package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.RankingList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RankingListMapper {

    @Insert("INSERT INTO ranking_lists (id, title, creator_id, description, cover_url, created_at, updated_at) " +
            "VALUES (#{id}, #{title}, #{creatorId}, #{description}, #{coverUrl}, #{createdAt}, #{updatedAt})")
    void insertRankingList(RankingList rankingList);

    @Delete("DELETE FROM ranking_lists WHERE id = #{id}")
    void deleteRankingList(Long id);

    @Update("UPDATE ranking_lists SET title = #{title}, description = #{description}, status = #{status}, cover_url = #{coverUrl}," +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    void updateRankingList(RankingList rankingList);

    @Select("SELECT * FROM ranking_lists WHERE id = #{id}")
    @Results({
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "cover_url", property = "coverUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    RankingList selectRankingList(Long id);

    @Select("SELECT * FROM ranking_lists")
    @Results({
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "cover_url", property = "coverUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<RankingList> selectAllRankingLists();
}