package com.blessing.todo.repository.specification;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.entity.enums.TodoType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TodoSpecification {

    public static Specification<Todo> deuDateGreaterThanOrEqualTo(LocalDate dueDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dueDate"), dueDate);
    }

    public static Specification<Todo> deuDateLessThanOrEqualTo(LocalDate dueDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("dueDate"), dueDate);
    }

    public static Specification<Todo> IsCompleted() {
        return (root, query, cb) -> cb.lessThan(root.get("isCompleted"), true);
    }

    public static Specification<Todo> descriptionLike(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Todo> todoTypeIsEqual(TodoType todoType) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("todoType"), todoType);
    }
}
