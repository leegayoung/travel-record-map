package com.travelrecord.post.controller;

import com.travelrecord.post.dto.PostDto;
import com.travelrecord.post.service.PostService;
import com.travelrecord.web.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post", description = "게시물 관련 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // 이 컨트롤러의 모든 API에 JWT 인증 필요
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 생성", description = "새로운 게시물을 생성합니다.")
    @PostMapping
    public ApiResponse<Long> createPost(@Valid @RequestBody PostDto.CreateRequest request) {
        return ApiResponse.success(postService.createPost(request));
    }

    @Operation(summary = "게시물 수정", description = "특정 게시물을 수정합니다.")
    @PutMapping("/{postId}")
    public ApiResponse<Void> updatePost(@PathVariable Long postId, @Valid @RequestBody PostDto.UpdateRequest request) {
        postService.updatePost(postId, request);
        return ApiResponse.success(null);
    }

    @Operation(summary = "단일 게시물 조회", description = "특정 ID의 게시물을 조회합니다.")
    @GetMapping("/{postId}")
    public ApiResponse<PostDto.Response> getPost(@PathVariable Long postId) {
        return ApiResponse.success(postService.getPost(postId));
    }

    @Operation(summary = "전체 게시물 조회", description = "모든 게시물을 조회합니다.")
    @GetMapping
    public ApiResponse<List<PostDto.Response>> getAllPosts() {
        return ApiResponse.success(postService.getAllPosts());
    }

    @Operation(summary = "게시물 삭제", description = "특정 게시물을 삭제합니다.")
    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ApiResponse.success(null);
    }
}