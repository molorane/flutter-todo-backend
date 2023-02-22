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

    public static Predicate descriptionLike(Root<Todo> root, CriteriaBuilder cb, String description) {
        return cb.and(cb.like(root.get("description"), "%" + description + "%"));
    }

    public static Predicate isDeleted(Root<Todo> root, CriteriaBuilder cb) {
        return cb.and(cb.equal(root.get("isDeleted"), true));
    }

    public static Specification<Todo> searchTodos(Long userId, TodoSearchDTO todoSearch) {
        System.out.println(userId);
        System.out.println(todoSearch);
        return new Specification<>() {

            final List<Predicate> predicates = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Account account = new Account();
                account.setId(userId);
                System.out.println(account);
                predicates.add(cb.and(cb.equal(root.get("account"), account)));

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

                if (Objects.nonNull(todoSearch.getDescription())) {
                    predicates.add(descriptionLike(root, cb, todoSearch.getDescription()));
                }

                if (todoSearch.getIsCompleted()) {
                    predicates.add(isCompleted(root, cb));
                }

                if (todoSearch.getIsDeleted()) {
                    predicates.add(isDeleted(root, cb));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
