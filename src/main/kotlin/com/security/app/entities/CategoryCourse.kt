package com.security.app.entities

import com.security.app.model.Language
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
class CategoryCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var categoryCourseId: UUID

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    var category: Category? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val language: Language = Language.ENGLISH

    @Column(nullable = false)
    val englishName: String = ""

    @Column(nullable = false)
    val vietnameseName: String = ""

    @Column(nullable = false)
    val frenchName: String = ""

    @OneToMany(mappedBy = "categoryCourse", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var languageCourseLearningContent: List<LanguageCourseLearningContent> = mutableListOf()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}