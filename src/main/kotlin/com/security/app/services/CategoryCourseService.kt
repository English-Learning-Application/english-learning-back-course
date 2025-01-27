package com.security.app.services

import com.security.app.entities.Category
import com.security.app.entities.CategoryCourse
import com.security.app.model.Language
import com.security.app.repositories.CategoryCourseRepository
import com.security.app.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryCourseService(
    private val categoryCourseRepository: CategoryCourseRepository,
    private val categoryRepository: CategoryRepository,
) {
    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun getCategoryCoursesByLanguage(
        categoryNames: List<String>,
        languageName: String,
    ): List<CategoryCourse> {
        val language = Language.fromString(languageName)
        if (categoryNames.isEmpty()) {
            return categoryCourseRepository.findAllByLanguage(language)
        }

        return categoryCourseRepository.findAllByCategoryCategoryKeyInAndLanguage(categoryNames, language)
    }
}