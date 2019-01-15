package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:  前台关于controller层
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
