package com.neodo.neodo_backend.topic.infrastructure;

import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicJpaRepository extends JpaRepository<TopicEntity, Long> {
}
