package com.prototype.erpsync.controller;

import com.prototype.erpsync.domain.AccessLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

@RestController
@Slf4j
public class MockApiController {

    // 이 API는 실제 출입 시스템 API를 흉내 냅니다.
    @GetMapping("/mock/access-logs")
    public List<AccessLogDto> getMockAccessLogs(@RequestParam Long sinceId) {
        log.info("[Mock API] {} 이후의 로그를 요청 받았습니다.", sinceId);

        // 100개의 가상 로그를 미리 생성합니다.
        final List<AccessLogDto> allLogs = LongStream.rangeClosed(1, 100)
                .mapToObj(i -> new AccessLogDto(
                        i,
                        "EMP" + (1000 + i),
                        LocalDateTime.of(2025, 6, 26, 9, 0, 0).plusMinutes(i * 5)
                        , "IN"
                ))
                .toList();

        return allLogs.stream()
                .filter(log -> log.id() > sinceId)
                .limit(5)
                .toList();
    }
}
