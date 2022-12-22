package com.caonhatlong.blog.service;

import com.caonhatlong.blog.dto.PostDTO;

import java.util.List;

public interface PostService {

    void createPost(PostDTO postDTO);


    List<PostDTO> showAllPost();

    PostDTO readSinglePost(Long id);
}
