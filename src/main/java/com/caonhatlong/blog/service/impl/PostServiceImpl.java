package com.caonhatlong.blog.service.impl;

import com.caonhatlong.blog.dto.PostDTO;
import com.caonhatlong.blog.exception.PostNotFoundException;
import com.caonhatlong.blog.model.Post;
import com.caonhatlong.blog.repository.PostRepository;
import com.caonhatlong.blog.service.AuthService;
import com.caonhatlong.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {

    private final AuthService authService;

    private final PostRepository postRepository;
    @Override
    public void createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        User username = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No user logged in"));
        post.setUserName(username.getUsername());
        post.setCreateAt(Instant.now());
        post.setUpdateAt(Instant.now());
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> showAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    @Override
    public PostDTO readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " +id));
        return mapFromPostToDto(post);
    }

    private PostDTO mapFromPostToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setUsername(post.getUserName());
        return postDTO;
    }
}
