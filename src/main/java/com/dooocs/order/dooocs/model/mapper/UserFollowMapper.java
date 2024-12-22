package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.UserFollow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserFollowMapper {
    
    @Insert("INSERT INTO user_follows (id, user_id, target_id, status, created_at) " +
            "VALUES (#{id}, #{userId}, #{targetId}, #{status}, #{createdAt})")
    void insertUserFollow(UserFollow userFollow);

    @Delete("DELETE FROM user_follows WHERE id = #{id}")
    void deleteUserFollow(Long id);

    @Select("SELECT * FROM user_follows WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "target_id", property = "targetId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<UserFollow> selectFollowingByUserId(Long userId);

    @Select("SELECT * FROM user_follows WHERE target_id = #{targetId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "target_id", property = "targetId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<UserFollow> selectFollowersByUserId(Long targetId);
} 