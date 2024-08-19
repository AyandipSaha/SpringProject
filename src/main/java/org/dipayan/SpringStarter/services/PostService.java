package org.dipayan.SpringStarter.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.dipayan.SpringStarter.models.Post;
import org.dipayan.SpringStarter.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Page<Post> getAll(int offset, int pageSize, String field) {
        return postRepository.findAll(PageRequest.of(offset, pageSize).withSort(Direction.ASC, field));
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post save(Post post) {
        if(post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
