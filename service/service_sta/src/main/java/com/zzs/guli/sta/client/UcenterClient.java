package com.zzs.guli.sta.client;

import com.zzs.guli.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-01 14:25
 **/
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/ucnterService/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);

}
