package com.gby.video.controller;

import com.gby.video.bean.Page;
import com.gby.video.bean.ResultVO;
import com.gby.video.bean.Video;
import com.gby.video.service.serviceImpl.VideoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping(value = "/video")
public class VideoController {
    @Resource
    private VideoServiceImpl service;


    @GetMapping("/get")
    public ResultVO getVideo(Page page, Video video) {
        return ResultVO.success("查询成功").setData(service.select(page, video));
    }

    @DeleteMapping("/del/video")
    public ResultVO delVideo(String id) {
        try {
            return ResultVO.success("删除" + service.delVideo(id) + "条数据");
        } catch (Exception e) {
            return ResultVO.error("请稍后重试");
        }
    }

    @DeleteMapping("/del/child")
    public ResultVO delVideoChild(Integer id) {
        try {
            return ResultVO.success("删除" + service.delVideoChild(id) + "条数据");
        } catch (Exception e) {

            return ResultVO.error("请稍后重试");
        }
    }

    @PutMapping("/update/video")
    public ResultVO editVideo(@RequestBody Video video) {
        try {
            return ResultVO.success("修改" + service.updateVideo(video) + "条数据");
        } catch (Exception e) {
            return ResultVO.error("请稍后重试");
        }
    }

    @PostMapping("/add/video")
    public ResultVO insertVideo(@RequestBody Video video) {
        try {
            return ResultVO.success("添加" + service.insertVideo(video) + "条数据");
        } catch (Exception e) {
            return ResultVO.error("请稍后重试");
        }
    }

    @PostMapping("/add/child")
    public ResultVO insertVideoChildrens(@RequestBody Video video) {
        try {
            return ResultVO.success("添加" + service.insertChildrens(video.getNewChildrens()) + "条数据");
        }catch (Exception e){
            return ResultVO.error("请稍后重试");
        }
    }
}
