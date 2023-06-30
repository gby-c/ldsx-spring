package com.gby.video.bean;

import lombok.Data;

import java.util.List;

@Data
public class Video {
    private String id;
    private String videoTitle;
    private String videoCover;
    private String videoTypes;
    private String director;
    private String stars;
    private String publishTime;
    private Integer videoKind;
    private String videoPath;
    private String videoContent;
    private Integer videoStatus;
    private Double doubanScore;
    private List<VideoChildrens> videoChildrensList;
    private List<VideoChildrens> newChildrens;
}
