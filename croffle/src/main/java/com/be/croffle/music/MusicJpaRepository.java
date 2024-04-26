package com.be.croffle.music;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicJpaRepository extends JpaRepository<Music, Long> {
}
