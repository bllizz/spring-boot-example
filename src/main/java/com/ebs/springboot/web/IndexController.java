package com.ebs.springboot.web;

import com.ebs.springboot.service.posts.PostService;
import com.ebs.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    /**
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    /**
     * @return
     */
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
