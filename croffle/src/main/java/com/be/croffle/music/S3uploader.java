package com.be.croffle.music;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3uploader {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;


    String uploadFileToS3(String musicName) {
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
}
