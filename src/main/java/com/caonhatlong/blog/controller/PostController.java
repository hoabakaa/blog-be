package com.caonhatlong.blog.controller;

import com.caonhatlong.blog.dto.PostDTO;
import com.caonhatlong.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDTO postDTO){
        postService.createPost(postDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPost(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDTO> getSinglePost(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
}
