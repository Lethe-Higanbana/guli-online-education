package com.summersky.guli.service.edu.feign;

import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.feign.fallback.OssFileServiceFallBack;
import com.summersky.guli.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zengfanbin
 * @since 2020/6/13
 */
@Service
@FeignClient(value = "service-oss", fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    /**
     * 测试
     * @return
     */
    @GetMapping("/admin/oss/file/test")
    R test();

    /**
     * 删除讲师头像
     * @param url
     * @return
     */
    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
