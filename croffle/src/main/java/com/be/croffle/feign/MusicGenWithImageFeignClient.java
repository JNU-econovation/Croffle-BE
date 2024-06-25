package com.be.croffle.feign;

import com.be.croffle.music.dto.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "musicGenWithImageFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenWithImageFeignClient {
    @PostMapping(value = "/generate_audio/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ServerResponse generateMusic(@RequestPart("image") MultipartFile image);
}
