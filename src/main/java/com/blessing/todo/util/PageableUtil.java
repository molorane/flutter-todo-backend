package com.blessing.todo.util;

import org.bouncycastle.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableUtil {

    public static Pageable pageable(final Integer pageNo,
                                    final Integer pageSize,
                                    final String sortBy,
                                    final String direction) {

        if (Objects.isNull(pageNo) || Objects.isNull(pageSize)) {
            return defaultPageable();
        } else if (Objects.isNull(sortBy) || sortBy.isBlank() || Objects.isNull(direction) || direction.isBlank()) {
            return defaultPageable();
        }
        return PageRequest.of(pageNo, pageSize,
                sort(direction, Sort.by(Strings.split(sortBy, ',')))
        );
    }

    /**
     * Get {@link Pageable} with default properties values
     *
     * @return pageable for searched object
     */
    public static Pageable defaultPageable() {
        final Sort sort = Sort.by("id");
        return PageRequest.of(0, 20, sort.descending());
    }

    private static Sort sort(final String direction,
                             final Sort sort) {
        if (Objects.isNull(direction) || direction.equalsIgnoreCase("asc")) {
            return sort.ascending();
        } else {
            return sort.descending();
        }
    }
}
