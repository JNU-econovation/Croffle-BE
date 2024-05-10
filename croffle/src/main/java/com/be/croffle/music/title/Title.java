package com.be.croffle.music.title;


import com.be.croffle.music.Music;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "title_tb")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int speed;

    @Column
    private String mood;

    @Column
    private String loc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @Builder
    public Title(int speed, String mood, String loc) {
        this.speed = speed;
        this.mood = mood;
        this.loc = loc;
    }

    public String createTitle(){
        return mood + " " + loc + "의 음악";
    }

}
