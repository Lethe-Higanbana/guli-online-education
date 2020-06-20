package com.summersky.guli.service.vod.controller.admin;

import com.summersky.guli.common.base.result.R;
import com.summersky.guli.common.base.result.ResultCodeEnum;
import com.summersky.guli.common.base.util.ExceptionUtils;
import com.summersky.guli.service.base.exception.GuliException;
import com.summersky.guli.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author helen
 * @since 2020/4/24
 */
@Api(description="阿里云视频点播")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/vod/media")
@Slf4j
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().message("视频上传成功").data("videoId", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw  new GuliException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }


    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @ApiParam(name="vodId", value="阿里云视频id", required = true)
            @PathVariable String vodId){

        log.warn("service-vod MediaController：videoSourceId= " + vodId);
        try {
            videoService.removeVideo(vodId);

            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new GuliException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }
}
