package com.prototype.erpsync.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

// JPA가 관리하는 테이블과 매핑될 객체임을 나타냅니다.
@Entity
@Getter
@Setter
@NoArgsConstructor // JPA는 기본 생성자를 필요로 합니다.
@AllArgsConstructor
public class SyncStatus {

    // 이 테이블의 Primary Key 입니다.
    @Id
    private String jobName;

    // 마지막으로 성공적으로 처리한 출입 기록의 ID
    private Long lastProcessedId;
}
