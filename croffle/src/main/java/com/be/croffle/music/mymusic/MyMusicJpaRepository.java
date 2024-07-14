package com.be.croffle.music.mymusic;

import com.be.croffle.music.mymusic.Mymusic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyMusicJpaRepository extends JpaRepository<Mymusic, Long> {

    Page<Mymusic> findAllByMemberId(Pageable pageable, Long memberId);
}
