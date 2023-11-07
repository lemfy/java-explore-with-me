package ru.practicum.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.exceptions.BadRequestException;

@Service
public class PaginationService {
    public Pageable getPageable(Integer from, Integer size) {
        if (from == null || size == null) {
            return null;
        }

        if (from < 0 || size <= 0) {
            throw new BadRequestException("Invalid value param \"from\" or \"size\"");
        }

        int pageNumber = from / size;

        return PageRequest.of(pageNumber, size);
    }
}