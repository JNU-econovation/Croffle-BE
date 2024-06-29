package com.be.croffle.music;

import com.be.croffle.common.utils.ApiResponse;
import com.be.croffle.common.utils.ApiResponseGenerator;
import com.be.croffle.common.security.UserDetailsImpl;
import com.be.croffle.music.dto.MusicGenWithTextRequest;
import com.be.croffle.music.dto.MusicGenResponse;
import com.be.croffle.music.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MusicGenController {

    private final MusicGenService musicGenService;

    @PostMapping("/api/generate-music")
    public ResponseEntity<ApiResponse.CustomBody<MusicGenResponse>> genMusic(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MusicGenWithTextRequest reqDto){
        MusicGenResponse response = musicGenService.genMusicUrl(reqDto, userDetails);
        return ApiResponseGenerator.success(response, HttpStatus.OK);

    }

    //이미지로 음악 생성하기
    @PostMapping(value = "/api/generate-music/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> genMusicWithImage(@RequestPart("image") MultipartFile image){
        log.info("image: {}", image);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    @GetMapping("/api/myplaylist")
    public ResponseEntity<ApiResponse.CustomBody<PlaylistResponse>> getMyPlaylist(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        PlaylistResponse response = musicGenService.getMyPlaylist(userDetails);
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    @GetMapping("/api/playlist")
    public ResponseEntity<ApiResponse.CustomBody<PlaylistResponse>> getPlaylist(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        PlaylistResponse response = musicGenService.getPlaylist();
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    @PostMapping("/api/music/{musicId}/like")
    public ResponseEntity<?> likeMusic(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("musicId") Long musicId) {
        musicGenService.likeMusic(userDetails, musicId);
        return ApiResponseGenerator.success(HttpStatus.OK);
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