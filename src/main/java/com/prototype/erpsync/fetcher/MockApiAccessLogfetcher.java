package com.prototype.erpsync.fetcher;

import com.prototype.erpsync.domain.AccessLogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockApiAccessLogfetcher implements AccessLogFetcher {

    // Spring이 자동으로 만들어준 구현체가 주입됩니다.
    private final MockApiClient mockApiClient;

    @Override
    public List<AccessLogDto> fetchLogsAfter(Long lastId) {
        try {
            return mockApiClient.getLogs(lastId);
        }catch (Exception e){
            log.error("API 호출 중 에러 발생: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
