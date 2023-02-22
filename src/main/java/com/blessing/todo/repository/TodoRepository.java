package com.blessing.todo.repository;

import com.blessing.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends AbstractRepository<Todo>, JpaSpecificationExecutor<Todo> {

    Optional<Todo> findByIdAndAccountId(Long id, Long userId);

    List<Todo> findTodoByDescriptionContainingIgnoreCase(String title);

    List<Todo> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted);

    List<Todo> findAllByAccountIdAndIsDeletedFalseOrderByDueDateDesc(Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.id = :id")
    void deleteTodoById(Long id);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.id = :id AND a.account.id = :userId")
    void deleteByIdAndAccountId(Long id, Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.account.id = :userId")
    void deleteByAccountId(Long userId);

    Long countByAccountIdAndIsDeletedTrue(long accountId);

    List<Todo> findAllTodosByAccountIdAndDueDate(Long userId, LocalDate dueDate);
}
