package com.amigoscode.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService() {
        this.restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    }

    public List<Post> getPosts() {
        return restClient.get().
                uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) ->
                {
                    throw new ResponseStatusException(response.getStatusCode(), "Client error occurred while fetching posts");
                }))
                .body(new ParameterizedTypeReference<List<Post>>() {}
        );
    }

    public Post getPostById(Integer id) {
        return restClient.get().
                uri("/posts/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) ->
                {
                    throw new ResponseStatusException(response.getStatusCode(), "Client error occurred while fetching posts");
                }))
                .body(Post.class);
    }

    public void createPost(Post post) {
        restClient.post().uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) ->
                {
                    throw new ResponseStatusException(response.getStatusCode(), "Client error occurred while creating post");
                })
                .body(Post.class);
    }

    public void updatePost(Integer id, Post post) {
        restClient.put().uri("/posts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) ->
                {
                    throw new ResponseStatusException(response.getStatusCode(), "Client error occurred while updating post");
                })
                .body(Post.class);
    }

    public void deletePost(Integer id) {
        restClient.delete().uri("/posts/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) ->
                {
                    throw new ResponseStatusException(response.getStatusCode(), "Client error occurred while deleting post");
                })
                .body(Void.class);
    }

}