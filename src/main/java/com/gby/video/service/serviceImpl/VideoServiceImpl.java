package com.gby.video.service.serviceImpl;

import com.gby.video.bean.Page;
import com.gby.video.bean.Video;
import com.gby.video.bean.VideoChildrens;
import com.gby.video.mapper.VideoMapper;
import com.gby.video.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper mapper;

    @Override
    public Page<Video> select(Page page, Video video) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Video> videos = mapper.select(video);
        PageInfo<Video> info = new PageInfo<>(videos);
        page.setTotal(info.getTotal());
        page.setRows(videos);
        return page;
    }

    @Override
    public int delVideo(String id) {
        return delVideoChildByVideoId(id) + mapper.del(id);
    }

    @Override
    public int delVideoChildByVideoId(String id) {
        return mapper.delChildrenByVideoId(id);
    }

    @Override
    public int delVideoChild(Integer id) {
        return mapper.delChildren(id);
    }

    @Override
    public int updateVideo(Video video) {
        return mapper.updateVideo(video);
    }

    @Override
    public int insertVideo(Video video) {
        video.setId(DigestUtils.md5DigestAsHex(video.getVideoTitle().getBytes(StandardCharsets.UTF_8)));
        return mapper.insertVideo(video);
    }

    @Override
    public int insertChildrens(List<VideoChildrens> childrens){
        return mapper.insertChildrens(childrens);
    }
}
