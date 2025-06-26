package com.prototype.erpsync.repository;

import com.prototype.erpsync.domain.SyncStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncStatusRepository extends JpaRepository<SyncStatus, String> {
}
