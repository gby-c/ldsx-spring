package com.gby.video.mapper;

import com.gby.video.bean.Video;
import com.gby.video.bean.VideoChildrens;

import java.util.List;

public interface VideoMapper {
    List<Video> select(Video video);
    List<VideoChildrens> selectChildren(String id);

    int del(String id);

    int delChildrenByVideoId(String videoId);

    int delChildren(Integer id);

    int updateVideo(Video video);

    int insertVideo(Video video);

    int insertChildrens(List<VideoChildrens> childrens);
}
