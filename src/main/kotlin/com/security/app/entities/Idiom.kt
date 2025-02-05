package com.security.app.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@EntityListeners(AuditingEntityListener::class)
class Idiom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var idiomId: String

    @Column(nullable = false)
    var englishIdiom: String = ""

    @Column(nullable = false)
    var vietnameseIdiom: String = ""

    @Column(nullable = false)
    var frenchIdiom: String = ""

    @Column(nullable = false)
    var englishIdiomMeaning: String = ""

    @Column(nullable = false)
    var vietnameseIdiomMeaning: String = ""

    @Column(nullable = false)
    var frenchIdiomMeaning: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var exampleUsage: String = ""

    @ManyToMany(mappedBy = "idioms", fetch = FetchType.LAZY)
    @JsonIgnore
    var languageCourseLearningContents: List<LanguageCourseLearningContent> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}