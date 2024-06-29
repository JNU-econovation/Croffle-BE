package com.be.croffle.music.mymusic;

import com.be.croffle.music.mymusic.Mymusic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMusicJpaRepository extends JpaRepository<Mymusic, Long> {
}
