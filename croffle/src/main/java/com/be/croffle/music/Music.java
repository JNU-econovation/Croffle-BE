package com.be.croffle.music;

import com.be.croffle.common.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "music_tb")
public class Music extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String musicUrl;

    @Column(nullable = false)
    private String title;

    @Builder
    public Music(String musicUrl, String title){
        this.musicUrl = musicUrl;
        this.title = title;
    }
}
