package com.blessing.todo.repository;

import com.blessing.todo.entity.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends AbstractRepository<Todo> {

    List<Todo> findTodoByTitleContaining(String title);

    List<Todo> findAllByCompleted(boolean completed);

    List<Todo> findAllByAccountIdOrderByIdDesc(Long userId);

    @Modifying
    @Query("update Todo a SET a.deleted = true where a.id = :id")
    void deleteTodoById(Long id);

    @Modifying
    @Query("update Todo a SET a.deleted = false where a.id = :id")
    void restoreDeleted(Long id);

}
