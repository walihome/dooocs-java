package com.dooocs.order.dooocs.model.mapper;
import com.dooocs.order.dooocs.model.entity.Test;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TestMapper {

    @Insert("INSERT INTO test (id, title) VALUES (#{id}, #{title})")
    void insertTest(Test test);

    @Delete("DELETE FROM test WHERE id = #{id}")
    void deleteTest(Integer id);

    @Update("UPDATE test SET title = #{title} WHERE id = #{id}")
    void updateTest(Test test);

    @Select("SELECT * FROM test WHERE id = #{id}")
    Test selectTest(Integer id);

    @Select("SELECT * FROM test")
    List<Test> selectAllTests();

}
