package com.travelrecord.post.domain;

import com.travelrecord.common.persistence.BaseEntity;
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

    private Boolean mainDisplay;

    @Builder
    private Post(String regionCode, String imageKey, String title, String content, String emotion, Long userId, Boolean mainDisplay) {
        this.regionCode = regionCode;
        this.imageKey = imageKey;
        this.title = title;
        this.content = content;
        this.emotion = emotion;
        this.userId = userId;
        this.mainDisplay = mainDisplay;
    }

    public void update(String title, String content, String emotion) {
        this.title = title;
        this.content = content;
        this.emotion = emotion;
    }

    public void setMainDisplay(Boolean mainDisplay) {
        this.mainDisplay = mainDisplay;
    }
}