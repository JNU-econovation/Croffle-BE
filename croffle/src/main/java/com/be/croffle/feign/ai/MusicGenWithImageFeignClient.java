package com.be.croffle.feign.ai;

import com.be.croffle.feign.common.FeignClientConfig;
import com.be.croffle.music.dto.gen.response.ServerImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "musicGenWithImageFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenWithImageFeignClient {
    @PostMapping(value = "/generate_audio_from_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ServerImageResponse generateMusic(@RequestPart("file") MultipartFile image);
}
