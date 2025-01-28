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
class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var expressionId: String

    @Column(nullable = false)
    var englishExpression: String = ""

    @Column(nullable = false)
    var vietnameseExpression: String = ""

    @Column(nullable = false)
    var frenchExpression: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var exampleUsage: String = ""

    @ManyToMany(mappedBy = "expressions", fetch = FetchType.LAZY)
    @JsonIgnore
    var languageCourseLearningContents: List<LanguageCourseLearningContent> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}