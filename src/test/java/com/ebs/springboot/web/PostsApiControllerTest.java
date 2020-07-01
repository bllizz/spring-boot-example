package com.ebs.springboot.web;

import com.ebs.springboot.domain.posts.Posts;
import com.ebs.springboot.domain.posts.PostsRepository;
import com.ebs.springboot.web.dto.PostsSaveRequestsDto;
import com.ebs.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws  Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void createPosts() throws  Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestsDto requestsDto = PostsSaveRequestsDto.builder()
                .title(title)
                .content(content)
                .author("bllizz@naver.com")
                .build();

        StringBuilder url = new StringBuilder("http://localhost:");
        url.append(port);
        url.append("/api/v1/posts");

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url.toString(), requestsDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>>>>>>>>>> response body " + responseEntity.getBody());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updatePosts() throws Exception {
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savePosts.getId();
        String expecedTitle = "title2";
        String expecedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expecedTitle)
                .content(expecedContent)
                .build();

        StringBuilder url = new StringBuilder("http://localhost:");
        url.append(port);
        url.append("/api/v1/posts/");
        url.append(updateId);

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        System.out.println(">>>>>>>>>>>>> List : " + all.get(0));
        assertThat(all.get(0).getTitle()).isEqualTo(expecedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expecedContent);
        assertThat(all.get(0).getAuthor()).isEqualTo("author");
    }
}