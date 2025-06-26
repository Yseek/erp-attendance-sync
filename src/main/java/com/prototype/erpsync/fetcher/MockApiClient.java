package com.prototype.erpsync.fetcher;

import com.prototype.erpsync.domain.AccessLogDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

// 이 인터페이스의 구현체는 Spring이 자동으로 만들어줍니다.
public interface MockApiClient {

    @GetExchange("/mock/access-logs")
    List<AccessLogDto> getLogs(@RequestParam("sinceId") Long sinceId);
}
