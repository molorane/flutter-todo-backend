package com.blessing.todo.repository;

import com.blessing.todo.entity.Task;
import com.blessing.todo.entity.enums.TaskType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskRepository extends AbstractRepository<Task>, JpaSpecificationExecutor<Task> {

    Optional<Task> findByIdAndAccountId(Long todoId, Long userId);

    Page<Task> findTaskByDescriptionContainingIgnoreCase(String title, Pageable pageable);

    Page<Task> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted, Pageable pageable);

    Page<Task> findAllTasksByAccountIdAndIsDeletedFalse(Long userId, Pageable pageable);

    @Modifying
    @Query("UPDATE Task a SET a.isDeleted = true WHERE a.id = :id")
    void deleteTaskById(Long id);

    @Modifying
    @Query("UPDATE Task a SET a.isDeleted = true WHERE a.id = :id AND a.account.id = :userId")
    void deleteByIdAndAccountId(Long id, Long userId);

    @Modifying
    @Query("UPDATE Task a SET a.isDeleted = true WHERE a.account.id = :userId")
    void deleteByAccountId(Long userId);

    Page<Task> findAllTasksByAccountIdAndDueDate(Long userId, LocalDate dueDate, Pageable pageable);

    Page<Task> findTasksByAccountIdAndTaskType(Long userId, TaskType todoType, Pageable pageable);

    Page<Task> findAllByAccountId(Long userId, Pageable pageable);
}
