package com.be.croffle.music.like;

import com.be.croffle.member.Member;
import com.be.croffle.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {
    boolean existsByMemberAndMusic(Member member, Music music);
}
