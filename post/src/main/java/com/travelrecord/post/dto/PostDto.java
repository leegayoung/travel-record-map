package com.travelrecord.post.dto;

import com.travelrecord.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostDto {

    @Getter @Setter
    public static class CreateRequest {
        @NotBlank(message = "지역 코드는 필수 입력값입니다.")
        private String regionCode;

        @NotBlank(message = "제목은 필수 입력값입니다.")
        private String title;

        private String content;
        private String emotion;
    }

    @Getter @Setter
    public static class UpdateRequest {
        private String title;
        private String content;
        private String emotion;
    }

    @Getter
    public static class Response {
        private Long id;
        private String regionCode;
        private String imageKey;
        private String title;
        private String content;
        private String emotion;
        private Long userId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Response(Post post) {
            this.id = post.getId();
            this.regionCode = post.getRegionCode();
            this.imageKey = post.getImageKey();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.emotion = post.getEmotion();
            this.userId = post.getUserId();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}