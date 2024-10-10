package com.be.croffle.music.repository;

import com.be.croffle.music.domain.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MusicJpaRepository extends JpaRepository<Music, Long> {

    @Query("SELECT m FROM Music m LEFT JOIN Like l ON m.id = l.music.id GROUP BY m.id ORDER BY COUNT(l.id) DESC")
    Page<Music> findAllOrderByLikeCountDesc(Pageable pageable);
}
