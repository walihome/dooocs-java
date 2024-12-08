package com.dooocs.order.dooocs.controller;

import com.dooocs.order.dooocs.model.entity.Test;
import com.dooocs.order.dooocs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping
    public void addTest(@RequestBody Test test) {
        testService.addTest(test);
    }

    @DeleteMapping("/{id}")
    public void removeTest(@PathVariable Integer id) {
        testService.removeTest(id);
    }

    @PutMapping
    public void modifyTest(@RequestBody Test test) {
        testService.modifyTest(test);
    }

    @GetMapping("/{id}")
    public Test getTest(@PathVariable Integer id) {
        return testService.getTest(id);
    }

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

}

