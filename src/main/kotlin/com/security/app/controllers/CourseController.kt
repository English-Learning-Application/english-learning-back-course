package com.security.app.controllers

import com.security.app.entities.Category
import com.security.app.entities.CategoryCourse
import com.security.app.entities.LanguageCourse
import com.security.app.entities.LanguageCourseLearningContent
import com.security.app.model.ListMessage
import com.security.app.model.Message
import com.security.app.services.CategoryCourseService
import com.security.app.services.LanguageCourseLearningContentService
import com.security.app.services.LanguageCourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/courses")
class CourseController(
    private val languageCourseService: LanguageCourseService,
    private val categoryCourseService: CategoryCourseService,
    private val languageCourseLearningContentService: LanguageCourseLearningContentService
) {
    @GetMapping("/{language}")
    fun getCourses(
        @PathVariable("language") language: String
    ): ResponseEntity<ListMessage<LanguageCourse>> {
        try {
            val courses = languageCourseService.getLanguageCourse(language)
            return ResponseEntity.ok(ListMessage.Success("Courses found", courses))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Language not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/{language}/{level}")
    fun getCoursesByLanguageAndLevel(
        @PathVariable("language") language: String,
        @PathVariable("level") level: String
    ): ResponseEntity<ListMessage<LanguageCourse>> {
        try {
            val courses = languageCourseService.getLanguageCourseByLevelAndLanguage(language, level)
            return ResponseEntity.ok(ListMessage.Success("Courses found", courses))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Language not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/learning-content/{contentId}")
    fun getLearningContent(
        @PathVariable("contentId") contentId: String
    ): ResponseEntity<Message<LanguageCourseLearningContent>> {
        try {
            val languageContent = languageCourseLearningContentService.getLanguageCourseLearningContent(contentId)
                ?: return ResponseEntity.badRequest().body(Message.NotFound("Content not found"))
            return ResponseEntity.ok(Message.Success("Content found", languageContent))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(Message.BadRequest("Content not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(Message.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/learning-content")
    fun getLearningContentsList(
        @RequestParam("contentIds") contentIds: List<String>
    ): ResponseEntity<ListMessage<LanguageCourseLearningContent>> {
        try {
            val languageContents =
                languageCourseLearningContentService.getLanguageCourseLearningContentByListOfLanguageCourseLearningContentId(
                    contentIds
                )
            return ResponseEntity.ok(ListMessage.Success("Contents found", languageContents))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Content not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<ListMessage<Category>> {
        try {
            val categories = categoryCourseService.getCategories()
            return ResponseEntity.ok(ListMessage.Success("Categories found", categories))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Category not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/categories/{language}")
    fun getCoursesByCategory(
        @PathVariable("language") language: String,
        /// Query parameter
        @RequestParam("q") category: List<String>
    ): ResponseEntity<ListMessage<CategoryCourse>> {
        try {
            val courses = categoryCourseService.getCategoryCoursesByLanguage(category, language)
            return ResponseEntity.ok(ListMessage.Success("Courses found", courses))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Category not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("/categories/courses")
    fun getCategoryCoursesByIds(
        @RequestParam("ids") categoryCourseIds: List<String>
    ): ResponseEntity<ListMessage<CategoryCourse>> {
        try {
            val courses = categoryCourseService.getCategoryCoursesByIds(categoryCourseIds)
            return ResponseEntity.ok(ListMessage.Success("Courses found", courses))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Category not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }

    @GetMapping("")
    fun getCoursesByIds(
        @RequestParam("ids") courseIds: List<String>
    ): ResponseEntity<ListMessage<LanguageCourse>> {
        try {
            val courses = languageCourseService.getLanguageCourseByIds(courseIds)
            return ResponseEntity.ok(ListMessage.Success("Courses found", courses))
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Course not found"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(ListMessage.BadRequest("Error occurred"))
        }
    }
}