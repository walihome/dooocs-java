package com.dooocs.order.dooocs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String redirectToRankings() {
        return "redirect:/api/rankingList/lists";
    }
}
