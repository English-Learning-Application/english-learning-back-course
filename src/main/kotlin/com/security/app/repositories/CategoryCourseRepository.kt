package com.security.app.repositories

import com.security.app.entities.CategoryCourse
import com.security.app.model.Language
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryCourseRepository : JpaRepository<CategoryCourse, UUID> {
    fun findAllByCategoryCategoryKeyInAndLanguage(
        categoryKeys: List<String>,
        language: Language
    ): List<CategoryCourse>

    fun findAllByLanguage(
        language: Language
    ): List<CategoryCourse>
}