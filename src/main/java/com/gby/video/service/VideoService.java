package com.gby.video.service;

import com.gby.video.bean.Page;
import com.gby.video.bean.Video;
import com.gby.video.bean.VideoChildrens;

import java.util.List;

public interface VideoService {
    Page<Video> select(Page page, Video video);

    int delVideo(String id);

    int delVideoChildByVideoId(String id);

    int delVideoChild(Integer id);

    int updateVideo(Video video);

    int insertVideo(Video video);

    int insertChildrens(List<VideoChildrens> childrens);
}
