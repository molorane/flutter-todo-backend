package com.blessing.todo.repository;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.entity.enums.TodoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoRepository extends AbstractRepository<Todo>, JpaSpecificationExecutor<Todo> {

    Optional<Todo> findByIdAndAccountId(Long todoId, Long userId);

    Page<Todo> findTodoByDescriptionContainingIgnoreCase(String title, Pageable pageable);

    Page<Todo> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted, Pageable pageable);

    Page<Todo> findAllTodosByAccountIdAndIsDeletedFalse(Long userId, Pageable pageable);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.id = :id")
    void deleteTodoById(Long id);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.id = :id AND a.account.id = :userId")
    void deleteByIdAndAccountId(Long id, Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.isDeleted = true WHERE a.account.id = :userId")
    void deleteByAccountId(Long userId);

    Page<Todo> findAllTodosByAccountIdAndDueDate(Long userId, LocalDate dueDate, Pageable pageable);

    Page<Todo> findTodosByAccountIdAndTodoType(Long userId, TodoType todoType, Pageable pageable);

    Page<Todo> findAllByAccountId(Long userId, Pageable pageable);
}
