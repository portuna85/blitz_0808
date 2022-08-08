package com.blitz.springboot.domain.posts;

import com.blitz.springboot.repository.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기() throws Exception {

        // given
        String title = "title";
        String content = "content";

        // when
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("portuna85@gmail.com")
                .build());

        // then
        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}