package com.neodo.neodo_backend.speechBoard.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpeechBoardJpaRepository extends JpaRepository<SpeechBoardEntity, Long> {

}
