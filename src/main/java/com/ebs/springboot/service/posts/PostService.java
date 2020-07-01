package com.ebs.springboot.service.posts;

import com.ebs.springboot.domain.posts.Posts;
import com.ebs.springboot.domain.posts.PostsRepository;
import com.ebs.springboot.web.dto.PostsResponseDto;
import com.ebs.springboot.web.dto.PostsSaveRequestsDto;
import com.ebs.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    /**
     * @param requestsDto
     * @return
     */
    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }

    /**
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
                );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * @param id
     * @return
     */
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
                );

        return new PostsResponseDto(entity);
    }
}
