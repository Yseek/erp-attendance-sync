package com.prototype.erpsync.scheduler;

import com.prototype.erpsync.service.AttendanceSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttendanceSyncScheduler {

    private final AttendanceSyncService attendanceSyncService;

    // 10초마다 작업을 실행합니다. (fixedRate = 밀리초 단위)
    // 실제 운영 환경에서는 "0 0 4 * * *" 와 같은 cron 표현식을 사용합니다.
    @Scheduled(fixedRate = 10000)
    public void runSyncJob() {
        log.info("==================== 출퇴근 기록 동기화 작업 시작 ====================");
        try {
            attendanceSyncService.syncAttendance();
        } catch (Exception e) {
            log.error("동기화 작업 중 예상치 못한 에러 발생", e);
        }
        log.info("==================== 출퇴근 기록 동기화 작업 종료 ====================\n");
    }
}
