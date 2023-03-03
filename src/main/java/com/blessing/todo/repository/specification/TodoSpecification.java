package com.blessing.todo.repository.specification;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.model.TodoSearchDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TodoSpecification {

    public static void dueDateGreaterThanOrEqualTo(final Set<Predicate> predicates,
                                                   final TodoSearchDTO todoSearch,
                                                   Root<Todo> root,
                                                   CriteriaBuilder cb) {
        if (Objects.nonNull(todoSearch.getStartDate())) {
            predicates.add(cb.and(cb.greaterThanOrEqualTo(root.get("dueDate"), todoSearch.getStartDate())));
        }

        dueDateLessThanOrEqualTo(predicates, todoSearch, root, cb);
    }

    public static void dueDateLessThanOrEqualTo(final Set<Predicate> predicates,
                                                final TodoSearchDTO todoSearch,
                                                Root<Todo> root,
                                                CriteriaBuilder cb) {
        if (Objects.nonNull(todoSearch.getEndDate())) {
            predicates.add(cb.and(cb.lessThanOrEqualTo(root.get("dueDate"), todoSearch.getEndDate())));
        }

        isCompleted(predicates, todoSearch, root, cb);
    }

    public static void isCompleted(final Set<Predicate> predicates,
                                   final TodoSearchDTO todoSearch,
                                   final Root<Todo> root,
                                   final CriteriaBuilder cb) {
        if (todoSearch.getIsCompleted())
            predicates.add(cb.equal(root.get("isCompleted"), true));
        else
            predicates.add(cb.notEqual(root.get("isCompleted"), true));
    }

    public static void descriptionLike(final Set<Predicate> predicates,
                                       final TodoSearchDTO todoSearch,
                                       final Root<Todo> root,
                                       final CriteriaBuilder cb) {
        if (Objects.nonNull(todoSearch.getDescription()) && !todoSearch.getDescription().isBlank()) {
            predicates.add(cb.and(cb.like(root.get("description"), "%" + todoSearch.getDescription() + "%")));
        }

        dueDateGreaterThanOrEqualTo(predicates, todoSearch, root, cb);
    }

    public static void todoTypeEqual(final Set<Predicate> predicates,
                                     final TodoSearchDTO todoSearch,
                                     final Root<Todo> root,
                                     final CriteriaBuilder cb) {
        if (Objects.nonNull(todoSearch.getTodoType())) {
            predicates.add(cb.and(cb.equal(root.get("todoType"), todoSearch.getTodoType())));
        }

        descriptionLike(predicates, todoSearch, root, cb);
    }

    public static void isNotDeleted(final Set<Predicate> predicates,
                                    final TodoSearchDTO todoSearch,
                                    final Root<Todo> root,
                                    final CriteriaBuilder cb) {
        predicates.add(cb.and(cb.equal(root.get("isDeleted"), false)));
        todoTypeEqual(predicates, todoSearch, root, cb);
    }

    public static void accountEqual(final Set<Predicate> predicates,
                                    final TodoSearchDTO todoSearch,
                                    final Long userId,
                                    final Root<Todo> root,
                                    final CriteriaBuilder cb) {
        final Account account = new Account();
        account.setId(userId);
        predicates.add(cb.and(cb.equal(root.get("account"), account)));

        isNotDeleted(predicates, todoSearch, root, cb);
    }


    public static Specification<Todo> searchTodos(final Long userId, final TodoSearchDTO todoSearch) {
        return (root, query, cb) -> {
            final Set<Predicate> predicates = new HashSet<>();
            accountEqual(predicates, todoSearch, userId, root, cb);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
