package com.gby.video.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ShortVideoReply {
    private Integer id;
    private Integer shortVideoTalkeId;
    private Integer userId;
    private String shortReplyContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
    private String rname;
}
