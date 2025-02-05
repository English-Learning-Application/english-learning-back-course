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
class Phonetic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var phoneticId: UUID

    @Column(nullable = false)
    var phoneticSymbol: String = ""

    @Column(nullable = false)
    var phoneticSound: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var englishPhoneticGuide: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var vietnamesePhoneticGuide: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var frenchPhoneticGuide: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var exampleUsage: String = ""

    @ManyToMany(mappedBy = "phonetics", fetch = FetchType.LAZY)
    @JsonIgnore
    var languageCourseLearningContents: List<LanguageCourseLearningContent> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}