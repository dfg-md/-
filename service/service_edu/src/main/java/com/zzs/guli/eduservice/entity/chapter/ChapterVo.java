package com.zzs.guli.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-11-30 15:12
 **/
@Data
public class ChapterVo {
    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();



}
