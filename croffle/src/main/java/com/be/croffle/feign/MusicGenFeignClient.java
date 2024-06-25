package com.be.croffle.feign;

import com.be.croffle.music.dto.MusicGenRequest;
import com.be.croffle.music.dto.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "musicGenFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenFeignClient {
    @PostMapping("/generate_audio")
    ServerResponse generateMusic(@RequestBody MusicGenRequest reqDto);

}
