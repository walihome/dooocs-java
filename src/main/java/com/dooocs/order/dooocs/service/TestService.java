package com.dooocs.order.dooocs.service;

import com.dooocs.order.dooocs.model.entity.Test;
import com.dooocs.order.dooocs.model.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public void addTest(Test test) {
        testMapper.insertTest(test);
    }

    public void removeTest(Integer id) {
        testMapper.deleteTest(id);
    }

    public void modifyTest(Test test) {
        testMapper.updateTest(test);
    }

    public Test getTest(Integer id) {
        return testMapper.selectTest(id);
    }

    public List<Test> getAllTests() {
        return testMapper.selectAllTests();
    }


}
