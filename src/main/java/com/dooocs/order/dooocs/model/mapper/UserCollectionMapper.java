package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.UserCollection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserCollectionMapper {
    
    @Insert("INSERT INTO user_collections (id, user_id, list_id, status, created_at) " +
            "VALUES (#{id}, #{userId}, #{listId}, #{status}, #{createdAt})")
    void insertUserCollection(UserCollection userCollection);

    @Delete("DELETE FROM user_collections WHERE id = #{id}")
    void deleteUserCollection(Long id);

    @Select("SELECT * FROM user_collections WHERE user_id = #{userId}")
    @Results({
            @Result(column = "list_id", property = "listId"),
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "media_url", property = "mediaUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<UserCollection> selectCollectionsByUserId(Long userId);

    @Select("SELECT * FROM user_collections WHERE user_id = #{userId} AND list_id = #{listId} AND type = #{type}")
    @Results({
            @Result(column = "list_id", property = "listId"),
            @Result(column = "update_cycle", property = "updateCycle"),
            @Result(column = "media_url", property = "mediaUrl"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    UserCollection selectUserCollection(Long userId, Long listId, String type);
} 