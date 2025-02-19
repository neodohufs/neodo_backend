package com.neodo.neodo_backend.speechboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "speech_boards")
public class SpeechBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //id 변수는 자동 증가하는 값을 가지게 됨, PK
    private Long speechboardId;

    @Column(name = "file_name")
    private String fileName;  // 난수 파일명 지정

    private String filePath;  // URL

    private Integer atmosphere;

    private Integer purpose;

    private Integer scale;

    private Integer audience;

    private Integer limitTime;

    private Long id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime uploadTime;



    //Constructor
    public SpeechBoardEntity() {
    }

    //Getter
    public Long getSpeechboardId() {
        return speechboardId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public LocalDateTime getUploadTime() { return uploadTime; }

    public Integer getPurpose() {
        return purpose;
    }

    public Integer getScale() {
        return scale;
    }

    public Integer getAudience() {
        return audience;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public Integer getAtmosphere(Integer atmosphere) {
        return this.atmosphere;
    }

    //Setter

    public void setSpeechboardId(Long id) {
        this.speechboardId = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setId(Long userId) { this.id = userId; }

    public void setTitle(String title) { this.title = title; }

    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public void setAtmosphere(Integer atmosphere) {
        this.atmosphere = atmosphere;
    }
}