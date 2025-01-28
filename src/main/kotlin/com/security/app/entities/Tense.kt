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
class Tense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var tenseId: UUID

    @Column(nullable = false)
    var englishTenseName: String = ""

    @Column(nullable = false)
    var vietnameseTenseName: String = ""

    @Column(nullable = false)
    var frenchTenseName: String = ""

    @Column(nullable = false)
    var englishDescription: String = ""

    @Column(nullable = false)
    var vietnameseDescription: String = ""

    @Column(nullable = false)
    var frenchDescription: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseRule: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseExample: String = ""

    @ManyToMany(mappedBy = "tenses", fetch = FetchType.LAZY)
    @JsonIgnore
    var languageCourseLearningContents: List<LanguageCourseLearningContent> = mutableListOf()

    @OneToMany(mappedBy = "tense", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var tenseForms: List<TenseForm> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}