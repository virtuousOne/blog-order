package com.web;

import com.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:  前台controller
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
    model.addAttribute("archiveMap",blogService.archiveBlog());
    model.addAttribute("blogCount",blogService.countBlog());
        return  "archives";
    }
}
