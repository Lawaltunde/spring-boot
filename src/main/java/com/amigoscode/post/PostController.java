package com.amigoscode.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Post post) {
        postService.createPost(post);
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Integer id, @RequestBody Post post) {
        postService.updatePost(id, post);
}

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }
}