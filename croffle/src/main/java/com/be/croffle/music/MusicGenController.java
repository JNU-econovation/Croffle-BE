package com.be.croffle.music;

import com.be.croffle.common.ApiResponseGenerator;
import com.be.croffle.music.dto.MusicGenRequest;
import com.be.croffle.music.dto.MusicGenResponse;
import com.be.croffle.music.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MusicGenController {

    private final MusicGenService musicGenService;

    @PostMapping("/api/generate-music")
    public ResponseEntity<?> generateMusic(@RequestBody MusicGenRequest reqDto) {
        try {
            //음악 생성 후 업로드 된 파일 경로를 반환
            String fileUrl = musicGenService.generateMusic(reqDto);
            MusicGenResponse musicGenResponse = new MusicGenResponse(fileUrl);
            return ApiResponseGenerator.success(musicGenResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ApiResponseGenerator.fail("Music generation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/playlist")
    public ResponseEntity<?> getPlaylist() {
        PlaylistResponse response = musicGenService.getPlaylist();
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

}