package com.summersky.guli.service.trade.feign.fallback;

import com.summersky.guli.service.base.dto.MemberDto;
import com.summersky.guli.service.trade.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zengfanbin
 * @since 2020/5/5
 */
@Service
@Slf4j
public class UcenterMemberServiceFallback implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
