package com.example.lostitem.services;

import com.example.lostitem.models.Post;
import com.example.lostitem.models.PostType;
import com.example.lostitem.models.Users;
import com.example.lostitem.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getLostPosts() {
        return postRepository.findByPostType(PostType.lost);
    }

    public List<Post> getFoundPosts() {
        return postRepository.findByPostType(PostType.found);
    }

    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUser(Users user) {
        return postRepository.findByUser(user);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}