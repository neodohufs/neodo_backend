package com.neodo.neodo_backend.speechBoards.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "speech_boards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeechBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speech_board_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    private String record;

    private Short atmosphere;

    private Short purpose;

    private Short scale;

    private Short audience;

    private Short deadline;

    public void setTitle(String title) {
        this.title = title;
    }
}
