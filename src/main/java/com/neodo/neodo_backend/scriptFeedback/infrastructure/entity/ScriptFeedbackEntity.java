package com.neodo.neodo_backend.scriptFeedback.infrastructure.entity;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "script_feedbacks")
public class ScriptFeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private ScriptEntity scriptEntity;

    private String conclusion;
}
