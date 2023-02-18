package com.blessing.todo.repository;

import com.blessing.todo.entity.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends AbstractRepository<Todo> {

    List<Todo> findTodoByDescriptionContainingIgnoreCase(String title);

    List<Todo> findAllByCompleted(boolean completed);

    List<Todo> findAllByAccountIdAndDeletedFalseOrderByDueDateDesc(Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.deleted = true WHERE a.id = :id")
    void deleteTodoById(Long id);

    @Modifying
    @Query("UPDATE Todo a SET a.deleted = false WHERE a.id = :id AND a.account.id = :userId")
    void restoreDeletedTodo(Long id, Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.deleted = true WHERE a.id = :id AND a.account.id = :userId")
    void deleteByIdAndAccountId(Long id, Long userId);

    @Modifying
    @Query("UPDATE Todo a SET a.deleted = true WHERE a.account.id = :userId")
    void deleteByAccountId(Long userId);

    Long countByAccountIdAndDeletedTrue(long accountId);
}
