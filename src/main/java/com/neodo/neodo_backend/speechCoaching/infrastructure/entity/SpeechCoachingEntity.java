package com.neodo.neodo_backend.speechCoaching.infrastructure.entity;

import com.neodo.neodo_backend.topic.infrastructure.entity.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Entity
@Table(name = "speech_coachings")
@Getter
public class SpeechCoachingEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String record;

    private String title;

}
