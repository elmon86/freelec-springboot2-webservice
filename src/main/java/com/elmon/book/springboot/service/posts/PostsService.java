package com.elmon.book.springboot.service.posts;

import com.elmon.book.springboot.domain.posts.Posts;
import com.elmon.book.springboot.domain.posts.PostsRepository;
import com.elmon.book.springboot.web.dto.PostSaveRequestDto;
import com.elmon.book.springboot.web.dto.PostsResponseDto;
import com.elmon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {

        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}