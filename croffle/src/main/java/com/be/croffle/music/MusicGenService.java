package com.be.croffle.music;


import com.be.croffle.feign.MusicGenFeignClient;
import com.be.croffle.music.dto.*;
import com.be.croffle.music.title.Title;
import com.be.croffle.music.title.TitleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MusicGenService {
    private final MusicJpaRepository musicJpaRepository;
    private final TitleJpaRepository titleJpaRepository;
    private final MusicGenFeignClient musicGenFeignClient;


    public MusicGenResponse getMusicUrl(MusicGenRequest request) {
        GeneratedUrl response =  musicGenFeignClient.generateMusic(request);

        String url = response.getResponse().getMusicURL();

        musicJpaRepository.save(Music
                .builder()
                .musicUrl(url)
                .build());

        return new MusicGenResponse(url);

    }



    /*
    public MusicGenResponse test(TestDto request) {
        TestRespDto response =  musicGenFeignClient.test(request);

        Long documentId = response.getData().getId();

        musicJpaRepository.save(Music
                .builder()
                .musicUrl(documentId.toString())
                .build());

        return new MusicGenResponse(documentId.toString());

    }

     */

  //  private final S3uploader s3uploader;
   // private final String workingDir = System.getProperty("user.dir");

    /*
    //  @Value("${python.script.path}")
    private String scriptPath= workingDir + "/scripts/infer_top_match.py";

    //  @Value("${python.executable.path}")
    private String pythonExecutablePath= workingDir + "/myvenv/bin/python3";

    public String generateMusic(MusicGenRequest reqDto){

        JSONObject jsonObject = createJsonFromRequest(reqDto);

        String loc = jsonObject.getString("loc");
        String mood = jsonObject.getString("mood");
        int speed = jsonObject.getInt("speed");

        String str = loc+mood+speed;
        String uuid = executePythonScript(str);


        String musicUrl = s3uploader.uploadFileToS3(uuid);
        musicJpaRepository.save(Music
                .builder()
                .musicUrl(musicUrl)
                .build());

        titleJpaRepository.save(Title
                .builder()
                .speed(reqDto.speed())
                .mood(reqDto.mood())
                .loc(reqDto.loc())
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

    private String executePythonScript(String prompt) {

        // 명령어 생성
        String[] command = {
                pythonExecutablePath,
                scriptPath, prompt,
                "--num_samples", "4",
                "--num_top_matches", "1",
                "--semantic_path", "./results/semantic/semantic.transformer.4000.pt",
                "--coarse_path", "./results/coarse/coarse.transformer.7000.pt",
                "--fine_path", "./results/fine/fine.transformer.3000.pt",
                "--rvq_path", "./results/clap_rvq/clap.rvq.990.pt",
                "--kmeans_path", "./results/hubert_kmeans/kmeans.joblib",
                "--model_config", "./configs/model/musiclm_small.json",
                "--duration", "4"
        };

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(prompt);
                writer.flush();
            }

            // 성공적으로 실행된 경우, .wav 파일 경로를 반환
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String outputFromPythonScript = reader.readLine();

            logProcessOutput(reader);
            waitForProcessAndLogExitCode(process);

            return outputFromPythonScript;

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

     */

    @Transactional(readOnly = true)
    public PlaylistResponse getPlaylist() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Music> page = musicJpaRepository.findAll(pageable);
        Page<Title> title = titleJpaRepository.findAll(pageable);

        List<Music> musicList = page.getContent();
        List<Title> titleList = title.getContent();

        List<String> musicTitle = titleList.stream()
                .map(Title::createTitle)
                .toList();

        List<EachMusicResponse> responses = IntStream.range(0, musicList.size())
                .mapToObj(index -> new EachMusicResponse(musicList.get(index).getId(), musicList.get(index).getMusicUrl(), musicTitle.get(index)))
                .collect(Collectors.toList());

        return new PlaylistResponse(responses);
    }

}
