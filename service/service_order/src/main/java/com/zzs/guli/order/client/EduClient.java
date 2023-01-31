package com.zzs.guli.order.client;


import com.zzs.guli.util.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-edu")
public interface EduClient {
    @PostMapping("/eduService/courseFront/{id}")
    CourseWebVoOrder getCourseById(@PathVariable("id") String id);
}
