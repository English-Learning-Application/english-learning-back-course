package com.security.app.entities

import com.fasterxml.jackson.annotation.JsonIgnore
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
class PhrasalVerb {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var phrasalVerbId: UUID

    @Column(nullable = false)
    var englishPhrasalVerb: String = ""

    @Column(nullable = false)
    var vietnamesePhrasalVerb: String = ""

    @Column(nullable = false)
    var frenchPhrasalVerb: String = ""

    @Column(nullable = false)
    var englishDescription: String = ""

    @Column(nullable = false)
    var vietnameseDescription: String = ""

    @Column(nullable = false)
    var frenchDescription: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var exampleUsage: String = ""

    @ManyToMany(mappedBy = "phrasalVerbs", fetch = FetchType.LAZY)
    @JsonIgnore
    var languageCourseLearningContents: List<LanguageCourseLearningContent> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}