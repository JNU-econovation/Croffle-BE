package com.be.croffle.music.title;

import com.be.croffle.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleJpaRepository extends JpaRepository<Title, Long> {
}
