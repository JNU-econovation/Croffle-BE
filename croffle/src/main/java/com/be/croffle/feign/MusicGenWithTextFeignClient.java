package com.be.croffle.feign;

import com.be.croffle.music.dto.MusicGenWithTextRequest;
import com.be.croffle.music.dto.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "musicGenWithTextFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenWithTextFeignClient {
    @PostMapping("/generate_audio")
    ServerResponse generateMusic(@RequestBody MusicGenWithTextRequest reqDto);

}
