package com.zzs.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-07 15:31
 **/
public interface vodService {
    String uploadVideoAly(MultipartFile file);

    void removeMoreVideo(List<String> videoIdList);
}
