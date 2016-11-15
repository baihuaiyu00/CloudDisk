package org.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Admin on 2016/4/24.
 */

@Controller
public class HomePageController {

    @RequestMapping("/Home.htm")
    public String HomePageTurn(){
        return "page/index";
    }

    @RequestMapping("/Turn.htm")
    public String SharePageTurn(){ return "page/showShare"; }
}
