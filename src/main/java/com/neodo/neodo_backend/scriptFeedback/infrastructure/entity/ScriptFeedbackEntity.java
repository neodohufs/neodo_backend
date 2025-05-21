package com.neodo.neodo_backend.scriptFeedback.infrastructure.entity;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "script_feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScriptFeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private ScriptEntity scriptEntity;

    @Lob
    @Setter
    private String feedback;

    @Builder
    public ScriptFeedbackEntity(ScriptEntity scriptEntity, String feedback) {
        this.scriptEntity = scriptEntity;
        this.feedback = feedback;
    }
}
