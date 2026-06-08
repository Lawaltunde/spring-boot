package com.amigoscode.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {
    private final RestClient restClient;

    public PostService() {
        this.restClient = RestClient.create(
                "https://jsonplaceholder.typicode.com"
        );
    }

    public List<Post> getAllPosts() {
        return restClient.get().uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }
}