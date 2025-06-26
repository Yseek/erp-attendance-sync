-- 애플리케이션 시작 시 sync_status 테이블에 초기 데이터를 삽입합니다.
-- job_name이 'attendanceSync'인 작업의 마지막 처리 ID는 0이라고 설정합니다.
INSERT INTO sync_status (job_name, last_processed_id) VALUES ('attendanceSync', 0);