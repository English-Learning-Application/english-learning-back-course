package com.security.app.services

import com.security.app.entities.LanguageCourse
import com.security.app.model.Language
import com.security.app.model.LanguageLevel
import com.security.app.repositories.LanguageCourseRepository
import com.security.app.utils.toUUID
import org.springframework.stereotype.Service

@Service
class LanguageCourseService(
    private val languageCourseRepository: LanguageCourseRepository
) {
    fun getLanguageCourse(
        language: String
    ): List<LanguageCourse> {
        val languageKey = Language.valueOf(language.toUpperCase())
        return languageCourseRepository.findByLanguage(languageKey)
    }

    fun getLanguageCourseByLevelAndLanguage(
        language: String,
        level: String
    ): List<LanguageCourse> {
        val languageKey = Language.valueOf(language.toUpperCase())
        val levelKey = LanguageLevel.valueOf(level.toUpperCase())
        return languageCourseRepository.findByLanguageAndLevel(languageKey, levelKey)
    }

    fun getLanguageCourseByIds(
        languageCourseIds: List<String>,
    ): List<LanguageCourse> {
        return languageCourseRepository.findAllById(languageCourseIds.map { it.toUUID() })
    }
}