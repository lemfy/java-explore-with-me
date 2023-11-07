package ru.practicum.categories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.exceptions.DataNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceHelper {
    private final CategoryRepository categoryRepository;

    public Category findById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new DataNotFoundException(Category.class.getName(), id);
        }
        return category.get();
    }
}