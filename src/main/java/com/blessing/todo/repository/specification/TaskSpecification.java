package com.blessing.todo.repository.specification;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.Task;
import com.blessing.todo.entity.enums.TaskType;
import com.blessing.todo.model.TaskSearchDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TaskSpecification {

    public static Predicate dueDateGreaterThanOrEqualTo(final Set<Predicate> predicates,
                                                        final TaskSearchDTO taskSearch,
                                                        Root<Task> root,
                                                        CriteriaBuilder cb) {
        if (Objects.nonNull(taskSearch.getStartDate())) {
            predicates.add(cb.and(cb.greaterThanOrEqualTo(root.get("dueDate"), taskSearch.getStartDate())));
        }

        return dueDateLessThanOrEqualTo(predicates, taskSearch, root, cb);
    }

    public static Predicate dueDateLessThanOrEqualTo(final Set<Predicate> predicates,
                                                     final TaskSearchDTO taskSearch,
                                                     Root<Task> root,
                                                     CriteriaBuilder cb) {
        if (Objects.nonNull(taskSearch.getEndDate())) {
            predicates.add(cb.and(cb.lessThanOrEqualTo(root.get("dueDate"), taskSearch.getEndDate())));
        }

        return isCompleted(predicates, taskSearch, root, cb);
    }

    public static Predicate isCompleted(final Set<Predicate> predicates,
                                        final TaskSearchDTO taskSearch,
                                        final Root<Task> root,
                                        final CriteriaBuilder cb) {
        if (taskSearch.getIsCompleted())
            predicates.add(cb.equal(root.get("isCompleted"), true));
        else
            predicates.add(cb.notEqual(root.get("isCompleted"), true));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    public static Predicate descriptionLike(final Set<Predicate> predicates,
                                            final TaskSearchDTO taskSearch,
                                            final Root<Task> root,
                                            final CriteriaBuilder cb) {
        if (Objects.nonNull(taskSearch.getDescription()) && !taskSearch.getDescription().isBlank()) {
            predicates.add(cb.and(cb.like(root.get("description"), "%" + taskSearch.getDescription() + "%")));
        }

        return dueDateGreaterThanOrEqualTo(predicates, taskSearch, root, cb);
    }

    public static Predicate taskTypeEqual(final Set<Predicate> predicates,
                                          final TaskSearchDTO taskSearch,
                                          final Root<Task> root,
                                          final CriteriaBuilder cb) {
        if (Objects.nonNull(taskSearch.getTaskType())) {
            predicates.add(cb.and(cb.equal(root.get("taskType"),
                    TaskType.valueOf(taskSearch.getTaskType().toString()))));
        }

        return descriptionLike(predicates, taskSearch, root, cb);
    }

    public static Predicate isDeleted(final Set<Predicate> predicates,
                                      final TaskSearchDTO taskSearch,
                                      final Root<Task> root,
                                      final CriteriaBuilder cb) {
        if (taskSearch.getIsDeleted())
            predicates.add(cb.equal(root.get("isDeleted"), true));
        else
            predicates.add(cb.notEqual(root.get("isDeleted"), true));
        return taskTypeEqual(predicates, taskSearch, root, cb);
    }

    public static Predicate accountEqual(final Set<Predicate> predicates,
                                         final TaskSearchDTO taskSearch,
                                         final Long userId,
                                         final Root<Task> root,
                                         final CriteriaBuilder cb) {
        final Account account = new Account();
        account.setId(userId);
        predicates.add(cb.and(cb.equal(root.get("account"), account)));

        return isDeleted(predicates, taskSearch, root, cb);
    }

    public static Specification<Task> searchTasks(final Long userId, final TaskSearchDTO taskSearch) {
        return (root, query, cb) -> {
            final Set<Predicate> predicates = new HashSet<>();
            return accountEqual(predicates, taskSearch, userId, root, cb);
        };
    }
}
