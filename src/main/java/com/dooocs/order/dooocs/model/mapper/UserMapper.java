package com.dooocs.order.dooocs.model.mapper;

import com.dooocs.order.dooocs.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO users (id, username, password, email, avatar, created_at, updated_at) " +
            "VALUES (#{id}, #{username}, #{password}, #{email}, #{avatar}, #{createdAt}, #{updatedAt})")
    void insertUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);

    @Update("UPDATE users SET username = #{username}, password = #{password}, " +
            "email = #{email}, avatar = #{avatar}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateUser(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    User selectUser(Long id);

    @Select("SELECT * FROM users")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    List<User> selectAllUsers();
} 