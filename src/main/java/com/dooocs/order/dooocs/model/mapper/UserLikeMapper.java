package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.UserLike;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserLikeMapper {
    
    @Insert("INSERT INTO user_likes (id, user_id, target_id, target_type, ip, created_at) " +
            "VALUES (#{id}, #{userId}, #{targetId}, #{targetType}, #{ip}, #{createdAt})")
    void insertUserLike(UserLike userLike);

    @Delete("DELETE FROM user_likes WHERE id = #{id}")
    void deleteUserLike(Long id);

    @Select("SELECT * FROM user_likes WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "target_id", property = "targetId"),
            @Result(column = "target_type", property = "targetType"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<UserLike> selectUserLikesByUserId(Long userId);

    @Select("SELECT * FROM user_likes WHERE user_id = #{userId} AND target_id = #{targetId} AND target_type = #{targetType}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "target_id", property = "targetId"),
            @Result(column = "target_type", property = "targetType"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    UserLike selectUserLike(Long userId, Long targetId, String targetType);
} 