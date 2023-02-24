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
        Sort sort = Sort.by(Strings.split(sortBy, ','));
        sort(direction, sort);

        return PageRequest.of(pageNo, pageSize, sort);
    }

    /**
     * Get {@link Pageable} with default properties values
     *
     * @param pageable {@link Pageable} object providing default settings
     * @return pageable for searched object
     */
    public static Pageable defaultPageable(final Pageable pageable) {
        final Sort sort = Sort.by("name");
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private static void sort(final String direction,
                             final Sort sort) {
        if (Objects.isNull(direction) || direction.equalsIgnoreCase("asc")) {
            sort.ascending();
        } else {
            sort.descending();
        }
    }
}
