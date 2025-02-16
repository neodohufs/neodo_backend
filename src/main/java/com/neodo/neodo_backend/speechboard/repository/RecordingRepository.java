package com.neodo.neodo_backend.speechboard.repository;

import com.neodo.neodo_backend.speechboard.model.RecordingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<RecordingModel, Long> {

}