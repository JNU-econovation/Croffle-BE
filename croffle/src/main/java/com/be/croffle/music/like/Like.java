package com.be.croffle.music.like;

import com.be.croffle.member.Member;
import com.be.croffle.music.Music;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "like_tb")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @Builder
    public Like(Member member, Music music){
        this.member = member;
        this.music = music;
    }

}
