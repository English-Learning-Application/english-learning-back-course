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
class TenseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var tenseFormId: UUID

    @Column(nullable = false)
    var tenseFormSubject: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormPositiveRule: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormNegativeRule: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormQuestionRule: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormPositiveExample: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormNegativeExample: String = ""

    @Column(nullable = false, columnDefinition = "TEXT")
    var tenseFormQuestionExample: String = ""

    @ManyToOne
    @JoinColumn(name = "tenseId", referencedColumnName = "tenseId")
    @JsonIgnore
    var tense: Tense? = null

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}