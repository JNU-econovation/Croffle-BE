package com.be.croffle.music;

import com.be.croffle.common.ApiResponse;
import com.be.croffle.common.ApiResponseGenerator;
import com.be.croffle.music.dto.MusicGenWithTextRequest;
import com.be.croffle.music.dto.MusicGenResponse;
import com.be.croffle.music.dto.PlaylistResponse;
import com.be.croffle.music.dto.ServerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicGenController {

    private final MusicGenService musicGenService;

    @PostMapping("/api/generate-music")
    public ResponseEntity<ApiResponse.CustomBody<MusicGenResponse>> genMusic(@RequestBody MusicGenWithTextRequest reqDto){
        MusicGenResponse response = musicGenService.getMusicUrl(reqDto);
        return ApiResponseGenerator.success(response, HttpStatus.OK);

    }

    //이미지로 음악 생성하기
    @PostMapping(value = "/api/generate-music/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> genMusicWithImage(@RequestPart("image") MultipartFile image){
        log.info("image: {}", image);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("/api/playlist")
    public ResponseEntity<ApiResponse.CustomBody<PlaylistResponse>> getPlaylist() {
        PlaylistResponse response = musicGenService.getPlaylist();
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

        /*
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
     */

}