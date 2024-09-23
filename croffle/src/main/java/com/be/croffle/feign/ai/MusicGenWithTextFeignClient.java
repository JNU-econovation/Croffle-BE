package com.be.croffle.feign.ai;

import com.be.croffle.feign.common.FeignClientConfig;
import com.be.croffle.music.dto.gen.request.MusicGenWithTextRequest;
import com.be.croffle.music.dto.gen.response.ServerTextResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "musicGenWithTextFeignClient", url = "${feign.url}",
        configuration = FeignClientConfig.class)
public interface MusicGenWithTextFeignClient {
    @PostMapping("/generate_audio")
    ServerTextResponse generateMusic(@RequestBody MusicGenWithTextRequest reqDto);

}
