package com.travelrecord.post.service;

import com.travelrecord.post.domain.Post;
import com.travelrecord.post.dto.PostDto;
import com.travelrecord.post.repository.PostRepository;
import com.travelrecord.post.application.port.out.RegionValidatorPortOut; // Post 모듈의 Outbound Port 임포트
import com.travelrecord.web.common.exception.*;
import org.springframework.data.domain.Page; // Added import
import org.springframework.data.domain.Pageable; // Added import

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    // private final RegionValidatorPortOut regionValidatorPortOut; // Post 모듈의 Outbound Port 주입

    @Transactional
    public Long createPost(PostDto.CreateRequest request) {
        // 현재 로그인한 사용자 ID를 가져옴
        Long currentUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        // Region 서비스 API 호출을 통한 regionCode 유효성 검증
        // regionValidatorPortOut.validateRegionCode(request.getRegionCode());

        String imageKey = mapImageKeyByRegionCode(request.getRegionCode());

        Post post = Post.builder()
                .regionCode(request.getRegionCode())
                .imageKey(imageKey)
                .title(request.getTitle())
                .content(request.getContent())
                .emotion(request.getEmotion())
                .userId(currentUserId) // 생성자 ID 저장
                .mainDisplay(false)
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional
    public void updatePost(Long postId, PostDto.UpdateRequest request) {
        Post post = findPostById(postId);
        Long currentUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!post.getUserId().equals(currentUserId)) {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        post.update(request.getTitle(), request.getContent(), request.getEmotion());
    }

    @Transactional(readOnly = true)
    public PostDto.Response getPost(Long postId) {
        return new PostDto.Response(findPostById(postId));
    }

    @Transactional(readOnly = true)
    public List<PostDto.Response> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostDto.Response::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PostDto.Response> getMyPostsByMainDisplay(Boolean mainDisplay, Pageable pageable) {
        Long currentUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        Page<Post> posts = postRepository.findPosts(currentUserId, mainDisplay, pageable);
        return posts.map(PostDto.Response::new);
    }

    @Transactional
    public void updatePostMainDisplayStatus(Long postId, PostDto.UpdateMainDisplayRequest request) {
        Post post = findPostById(postId);
        Long currentUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!post.getUserId().equals(currentUserId)) {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        post.setMainDisplay(request.getMainDisplay());
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        Long currentUserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!post.getUserId().equals(currentUserId)) {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        postRepository.deleteById(postId);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private String mapImageKeyByRegionCode(String regionCode) {
        if (regionCode == null || regionCode.length() < 2) {
            return "default_image.jpg";
        }
        String sidoCode = regionCode.substring(0, 2);
        return switch (sidoCode) {
            case "11" -> "seoul_image.jpg";
            case "28" -> "incheon_image.jpg";
            case "41" -> "gyeonggi_image.jpg";
            case "44" -> "chungnam_image.jpg";
            case "50" -> "jeju_image.jpg";
            default -> "default_image.jpg";
        };
    }
}