package com.summersky.guli.service.edu.feign.fallback;

import com.summersky.guli.common.base.result.R;
import com.summersky.guli.service.edu.feign.OssFileService;
import com.summersky.guli.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 失败回调
 * @author zengfanbin
 * @since 2020/6/13
 */
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }
}
