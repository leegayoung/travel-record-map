package com.travelrecord.post.domain;

import com.travelrecord.persistence.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Region 서비스 분리로 인해 Group 참조 제거
    @Column(nullable = false)
    private String regionCode;

    @Column(nullable = false)
    private String imageKey;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private String emotion;

    @Column(nullable = false)
    private Long userId; // Post 생성자의 ID

    @Builder
    private Post(String regionCode, String imageKey, String title, String content, String emotion, Long userId) {
        this.regionCode = regionCode;
        this.imageKey = imageKey;
        this.title = title;
        this.content = content;
        this.emotion = emotion;
        this.userId = userId;
    }

    public void update(String title, String content, String emotion) {
        this.title = title;
        this.content = content;
        this.emotion = emotion;
    }
}