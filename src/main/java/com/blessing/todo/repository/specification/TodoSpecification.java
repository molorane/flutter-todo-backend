package com.blessing.todo.repository.specification;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.entity.enums.TodoType;
import com.blessing.todo.model.TodoSearchDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodoSpecification {

    public static Predicate todoTypeEqual(Root<Todo> root, CriteriaBuilder cb, TodoType todoType) {
        return cb.and(cb.equal(root.get("todoType"), todoType));
    }

    public static Predicate dueDateGreaterThanOrEqualTo(Root<Todo> root, CriteriaBuilder cb, LocalDate dueDate) {
        return cb.and(cb.greaterThanOrEqualTo(root.get("dueDate"), dueDate));
    }

    public static Predicate dueDateLessThanOrEqualTo(Root<Todo> root, CriteriaBuilder cb, LocalDate dueDate) {
        return cb.and(cb.lessThanOrEqualTo(root.get("dueDate"), dueDate));
    }

    public static Predicate isCompleted(Root<Todo> root, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.get("isCompleted"), true));
    }

    public static Predicate isNotCompleted(Root<Todo> root, CriteriaBuilder cb) {
        return cb.and(cb.notEqual(root.get("isCompleted"), true));
    }

    public static Predicate descriptionLike(Root<Todo> root, CriteriaBuilder cb, String description) {
        return cb.and(cb.like(root.get("description"), "%" + description + "%"));
    }

    public static Predicate isNotDeleted(Root<Todo> root, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.get("isDeleted"), false));
    }

    public static Predicate accountEqual(Long userId, Root<Todo> root, CriteriaBuilder cb) {
        final Account account = new Account();
        account.setId(userId);
        return cb.and(cb.equal(root.get("isDeleted"), false));
    }

    public static Specification<Todo> endDateEqualOrLessThan(Specification<Todo> spec, TodoSearchDTO todoSearch) {
        if(Objects.nonNull(todoSearch.getTodoType())){
            spec.and((root, query, cb) -> cb.and(cb.lessThanOrEqualTo(root.get("dueDate"), todoSearch.getStartDate())));
        }
        return spec;
    }


    public static Specification<Todo> startDateEqualOrGreaterThan(Specification<Todo> spec, TodoSearchDTO todoSearch) {
        if(Objects.nonNull(todoSearch.getTodoType())){
            spec.and((root, query, cb) -> cb.and(cb.greaterThanOrEqualTo(root.get("dueDate"), todoSearch.getStartDate())));
        }
        return endDateEqualOrLessThan(spec, todoSearch);
    }


    public static Specification<Todo> descriptionEqual(Specification<Todo> spec, TodoSearchDTO todoSearch) {
        if(Objects.nonNull(todoSearch.getTodoType())){
            spec.and((root, query, cb) -> cb.and(cb.equal(root.get("todoType"), todoSearch.getTodoType())));
        }
        return spec;
    }

    public static Specification<Todo> todoTypeEqual(Specification<Todo> spec, TodoSearchDTO todoSearch) {
        if(Objects.nonNull(todoSearch.getTodoType())){
            spec.and((root, query, cb) -> cb.and(cb.equal(root.get("todoType"), todoSearch.getTodoType())));
        }
        return descriptionEqual(spec, todoSearch);
    }

    public static Specification<Todo> build(Long userId, TodoSearchDTO todoSearch) {
        final Account account = new Account();
        account.setId(userId);
        return todoTypeEqual((root, query, cb) -> cb.and(cb.equal(root.get("account"), account)), todoSearch);
    }

    public static Specification<Todo> searchTodos(Long userId, TodoSearchDTO todoSearch) {
        final List<Predicate> predicates = new ArrayList<>();
        final Account account = new Account();
        account.setId(userId);
        return (@NonNull Root<Todo> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) -> {
            predicates.add(accountEqual(userId, root, cb));
            predicates.add(isNotDeleted(root, cb));
            return applyFilters(predicates, todoSearch, root, cb);
        };
    }

    private static Predicate applyFilters(final List<Predicate> predicates,
                                          TodoSearchDTO todoSearch,
                                          @NonNull Root<Todo> root,
                                          @NonNull CriteriaBuilder cb) {

        if (Objects.nonNull(todoSearch.getTodoType())) {
            predicates.add(todoTypeEqual(root, cb,
                    TodoType.valueOf(todoSearch.getTodoType().toString())
            ));
        }

        if (Objects.nonNull(todoSearch.getStartDate())) {
            predicates.add(dueDateGreaterThanOrEqualTo(root, cb, todoSearch.getStartDate()));
        }

        if (Objects.nonNull(todoSearch.getEndDate())) {
            predicates.add(dueDateLessThanOrEqualTo(root, cb, todoSearch.getEndDate()));
        }

        if (Objects.nonNull(todoSearch.getDescription()) && !todoSearch.getDescription().isBlank()) {
            predicates.add(descriptionLike(root, cb, todoSearch.getDescription()));
        }

        if (todoSearch.getIsCompleted()) {
            predicates.add(isCompleted(root, cb));
        } else {
            predicates.add(isNotCompleted(root, cb));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
