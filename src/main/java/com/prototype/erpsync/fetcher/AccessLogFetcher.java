package com.prototype.erpsync.fetcher;

import com.prototype.erpsync.domain.AccessLogDto;

import java.util.List;

/**
 * 출입 시스템으로부터 출입 기록을 가져오는 기능의 인터페이스입니다.
 * 이 인터페이스를 구현하는 클래스는 "어떻게" 데이터를 가져올지 구체적인 방법을 정의해야 합니다.
 */
public interface AccessLogFetcher {

    /**
     * 특정 ID 이후의 출입 기록 로그를 가져옵니다.
     * @param lastId 마지막으로 성공적으로 처리한 로그의 ID (이 ID보다 큰 값을 가져옵니다)
     * @return List<AccessLogDto> 새로운 출입 기록 리스트
     */
    List<AccessLogDto> fetchLogsAfter(Long lastId);
}
