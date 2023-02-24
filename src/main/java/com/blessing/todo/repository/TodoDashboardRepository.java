package com.blessing.todo.repository;

import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoDashboardRepository extends AbstractRepository<Todo>, JpaSpecificationExecutor<Todo> {

    Long countByAccountIdAndIsDeletedTrue(long accountId);

    @Query("SELECT t.todoType AS todoType, t.isCompleted AS isCompleted, COUNT(t.isCompleted) AS totalTodos " +
            "FROM Todo AS t " +
            "WHERE t.account.id = :userId AND t.isDeleted = TRUE " +
            "GROUP BY t.todoType, t.isCompleted " +
            "ORDER BY totalTodos")
    List<ITodoGroupCount> todoGroupCountByUserId(Long userId);
}
