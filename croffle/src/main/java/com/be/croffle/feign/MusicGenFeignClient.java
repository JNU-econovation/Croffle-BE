package com.be.croffle.feign;

import com.be.croffle.music.dto.GeneratedUrl;
import com.be.croffle.music.dto.MusicGenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "musicGenFeignClient", url = "${feign.url}",
        configuration = MusicGenFeignClientConfig.class)
public interface MusicGenFeignClient {
    @PostMapping("/generate_audio")
    GeneratedUrl generateMusic(@RequestBody MusicGenRequest reqDto);

//    @PostMapping("/api/documents/summary")
 //   TestRespDto test(@RequestBody TestDto reqDto);

}
