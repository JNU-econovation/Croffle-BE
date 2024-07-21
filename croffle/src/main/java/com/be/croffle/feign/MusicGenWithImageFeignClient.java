package com.be.croffle.feign;

import com.be.croffle.music.dto.gen.ServerResponse;
import com.be.croffle.music.dto.gen.ServerResponseImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "musicGenWithImageFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenWithImageFeignClient {
    @PostMapping(value = "/generate_audio_from_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ServerResponseImage generateMusic(@RequestPart("file") MultipartFile image);
}
