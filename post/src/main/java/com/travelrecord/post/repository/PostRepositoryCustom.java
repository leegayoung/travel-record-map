package com.travelrecord.post.repository;

import com.travelrecord.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findPosts(Long userId, Boolean mainDisplay);
    Page<Post> findPosts(Long userId, Boolean mainDisplay, Pageable pageable);
}
