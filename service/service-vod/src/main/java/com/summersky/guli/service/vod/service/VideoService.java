package com.summersky.guli.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;

/**
 * @author helen
 * @since 2020/4/24
 */
public interface VideoService {

    String uploadVideo(InputStream inputStream, String originalFilename);

    void removeVideo(String videoId) throws ClientException;
}
