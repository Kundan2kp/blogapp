package com.blog.blogapp.controller;

import com.blog.blogapp.payload.PostDto;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.service.PostService;
import com.blog.blogapp.utils.AppContstants;
import com.sun.deploy.association.utility.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid  @RequestBody PostDto postdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postdto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppContstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppContstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue =AppContstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value= "sortDir", defaultValue = AppContstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize, sortBy, sortDir);
    }
    //http://localhost:8080/api/posts/1
    @GetMapping ("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post Entity Deleted Successfully",HttpStatus.OK);
    }

}
