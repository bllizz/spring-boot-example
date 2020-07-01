package com.ebs.springboot.web.dto;

import com.ebs.springboot.domain.posts.Posts;
import lombok.Getter;


@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    /**
     * @param entity
     */
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
