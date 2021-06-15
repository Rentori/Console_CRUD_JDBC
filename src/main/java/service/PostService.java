package service;

import model.Post;
import repository.PostRepository;
import repository.io.PostRepositoryImpl;

import java.util.List;

public class PostService {
    PostRepository postRepository = PostRepositoryImpl.getInstance();

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post type) {
        return postRepository.save(type);
    }

    public Post update(Post type) {
        return postRepository.update(type);
    }

    public Post getById(Long aLong) {
        return postRepository.getById(aLong);
    }

    public void deleteById(Long aLong) {
        postRepository.deleteById(aLong);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }
 }
