package com.zzs.guli.order.client;

import com.zzs.guli.util.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/ucnterService/member/getUser/{id}")
    public UcenterMemberOrder getUserById(@PathVariable("id") String id);
}
