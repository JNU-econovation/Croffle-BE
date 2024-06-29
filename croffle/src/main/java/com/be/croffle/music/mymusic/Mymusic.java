package com.be.croffle.music.mymusic;

import com.be.croffle.common.utils.BaseEntity;
import com.be.croffle.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mymusic_tb")
public class Mymusic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String musicUrl;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Mymusic(String musicUrl, String title, Member member) {
        this.musicUrl = musicUrl;
        this.title = title;
        this.member = member;
    }
}
