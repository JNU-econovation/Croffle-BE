package com.be.croffle.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.be.croffle.MusicConvert;
import com.be.croffle.music.Music;
import com.be.croffle.music.MusicJpaRepository;
import com.be.croffle.music.dto.MusicGenRequest;
import com.be.croffle.music.dto.EachMusicResponse;
import com.be.croffle.music.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MusicGenService {

    private final AmazonS3Client amazonS3Client;
    private final MusicJpaRepository musicJpaRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String workingDir = System.getProperty("user.dir");

    //TODO : 환경변수화

    //  @Value("${python.script.path}")
    private String scriptPath= workingDir + "/src/main/resources/script.py";

    //  @Value("${python.executable.path}")
    private String pythonExecutablePath= workingDir + "/myvenv/bin/python3";

    public String generateMusic(MusicGenRequest reqDto){

        JSONObject jsonObject = createJsonFromRequest(reqDto);
        String uuid = executePythonScript(jsonObject.toString());

        String musicUrl = uploadFileToS3(uuid);
        musicJpaRepository.save(Music
                .builder()
                .musicUrl(musicUrl)
                .build());

        return musicUrl;
    }

    private JSONObject createJsonFromRequest(MusicGenRequest reqDto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("speed", reqDto.speed());
        jsonObject.put("mood", reqDto.mood());
        jsonObject.put("loc", reqDto.loc());
        return jsonObject;
    }

    private String executePythonScript(String jsonInput) {
        ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutablePath, scriptPath);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(jsonInput);
                writer.flush();
            }

            // 성공적으로 실행된 경우, .wav 파일 경로를 반환
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String outputFromPythonScript1 = reader.readLine();

            logProcessOutput(reader);
            waitForProcessAndLogExitCode(process);

            return outputFromPythonScript1;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



    private void logProcessOutput(BufferedReader reader) throws IOException {
        String output = reader.lines().reduce("", (acc, line) -> acc + "\n" + line);
        log.info("Python script output: {}", output);
    }

    private void waitForProcessAndLogExitCode(Process process) throws InterruptedException {
        int exitCode = process.waitFor();
        log.info("Python script exited with code {}", exitCode);
    }

    private String uploadFileToS3(String musicName) {
        // AWS S3 업로드 로직 구현
        //TODO : 음악 확장자 확인
        File uploadFile =  new File(System.getProperty("user.dir") + "/musics/" + "music" + musicName +".wav");

        String fileName = "music" + "/" + uploadFile.getName(); // S3에 저장된 파일 이름
        String uploadMusicUrl = putS3(uploadFile, fileName); // S3로 업로드
        log.info("uploadImageUrl = " + uploadMusicUrl);
        removeNewFile(uploadFile);

        // 업로드된 음악 url 제공
        return uploadMusicUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    public PlaylistResponse getPlaylist() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Music> page = musicJpaRepository.findAll(pageable);

        List<Music> musicList = page.getContent();

        List<EachMusicResponse> responses = musicList.stream()
                .map(MusicConvert::convertToEachMusicResponse)
                .toList();

        return new PlaylistResponse(responses);
    }

}
