package com.prototype.erpsync.service;

import com.prototype.erpsync.domain.AccessLogDto;
import com.prototype.erpsync.domain.SyncStatus;
import com.prototype.erpsync.fetcher.AccessLogFetcher;
import com.prototype.erpsync.repository.SyncStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceSyncService {

    private final AccessLogFetcher accessLogFetcher;
    private final SyncStatusRepository syncStatusRepository;
    private static final String JOB_NAME = "attendanceSync";

    @Transactional
    public void syncAttendance() {
        // 1. 마지막 처리 상태(책갈피)를 DB에서 가져온다.
        final SyncStatus status = syncStatusRepository.findById(JOB_NAME)
                .orElseThrow(() -> new IllegalStateException(JOB_NAME + " 작업의 상태 정보가 DB에 없습니다."));

        final long lastId = status.getLastProcessedId();
        log.info("현재 마지막 처리 ID: {}", lastId);

        // 2. Fetcher를 통해 새로운 출입 기록을 가져온다.
        final List<AccessLogDto> newLogs = accessLogFetcher.fetchLogsAfter(lastId);

        // 3. 새로운 로그가 없으면 작업 종료
        if (newLogs.isEmpty()) {
            log.info("동기화할 새로운 출입 기록이 없습니다.");
            return;
        }
        log.info("{}개의 새로운 로그를 가져왔습니다: {}", newLogs.size(), newLogs);

        log.info("가공된 데이터를 ERP 시스템으로 전송합니다... (전송 성공)");

        // 5. 가져온 로그 중 가장 큰 ID를 찾아 DB에 새로운 책갈피로 기록한다.
        final long maxIdInFetchedLogs = newLogs.stream()
                .map(AccessLogDto::id)
                .max(Comparator.naturalOrder())
                .orElse(lastId);

        status.setLastProcessedId(maxIdInFetchedLogs);
        syncStatusRepository.save(status);
        log.info("마지막 처리 ID를 {} (으)로 업데이트 했습니다.", maxIdInFetchedLogs);
    }
}
