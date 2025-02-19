package com.neodo.neodo_backend.speechboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "speech_boards")
public class SpeechBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //id 변수는 자동 증가하는 값을 가지게 됨, PK
    private Long speech_board_id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime created_at;

    @Column(name = "file_name")
    private String fileName;  // 난수 파일명 지정

    private String record;  // URL

    private Integer atmosphere;

    private Integer purpose;

    private Integer scale;

    private Integer audience;

    private Integer deadline;


    //Constructor
    public SpeechBoardEntity() {
    }

    //Getter
    public Long getSpeech_board_id() {
        return speech_board_id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getRecord() {
        return record;
    }

    public Long getUser_id() { return user_id; }

    public String getTitle() { return title; }

    public LocalDateTime getCreated_at() { return created_at; }

    public Integer getPurpose() {
        return purpose;
    }

    public Integer getScale() {
        return scale;
    }

    public Integer getAudience() {
        return audience;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public Integer getAtmosphere(Integer atmosphere) {
        return this.atmosphere;
    }

    //Setter

    public void setSpeech_board_id(Long id) {
        this.speech_board_id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setRecord(String filePath) {
        this.record = filePath;
    }

    public void setUser_id(Long userId) { this.user_id = userId; }

    public void setTitle(String title) { this.title = title; }

    public void setCreated_at(LocalDateTime uploadTime) { this.created_at = uploadTime; }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public void setDeadline(Integer limitTime) {
        this.deadline = limitTime;
    }

    public void setAtmosphere(Integer atmosphere) {
        this.atmosphere = atmosphere;
    }
}