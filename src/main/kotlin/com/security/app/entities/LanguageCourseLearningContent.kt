package com.security.app.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.security.app.model.LearningContentType
import jakarta.persistence.*
import lombok.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@EntityListeners(AuditingEntityListener::class)
class LanguageCourseLearningContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var languageCourseLearningContentId: UUID

    @ManyToOne
    @JoinColumn(name = "languageCourseId", referencedColumnName = "languageCourseId")
    @JsonIgnore
    var languageCourse: LanguageCourse? = null

    @ManyToOne
    @JoinColumn(name = "categoryCourseId", referencedColumnName = "categoryCourseId")
    @JsonIgnore
    var categoryCourse: CategoryCourse? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var learningContentType: LearningContentType = LearningContentType.WORD

    @Column(nullable = true)
    var vietnameseTitle: String? = null

    @Column(nullable = true)
    var englishTitle: String? = null

    @Column(nullable = true)
    var frenchTitle: String? = null

    @Column(nullable = true, columnDefinition = "TEXT")
    var vietnameseDescription: String? = null

    @Column(nullable = true, columnDefinition = "TEXT")
    var englishDescription: String? = null

    @Column(nullable = true, columnDefinition = "TEXT")
    var frenchDescription: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_words",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "wordId")],
    )
    var words: List<Word> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_expressions",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "expressionId")],
    )
    var expressions: List<Expression> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_idioms",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "idiomId")],
    )
    var idioms: List<Idiom> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_sentences",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "sentenceId")],
    )
    var sentences: List<Sentence> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_phrasal_verbs",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "phrasalVerbId")],
    )
    var phrasalVerbs: List<PhrasalVerb> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_tenses",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "tenseId")],
    )
    var tenses: List<Tense> = mutableListOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "language_course_learning_content_phonetics",
        joinColumns = [JoinColumn(name = "languageCourseLearningContentId")],
        inverseJoinColumns = [JoinColumn(name = "phoneticId")],
    )
    var phonetics: List<Phonetic> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}