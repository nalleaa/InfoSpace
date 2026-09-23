package com.example.xipplgb_infospace.model;

public class Task {
    private String id;
    private String title;          // Nama tugas
    private String subject;        // Mata pelajaran
    private String deadline;       // Tanggal deadline
    private String difficulty;     // Mudah / Sedang / Sulit
    private String authorName;     // Nama pembuat
    private int authorAbsent;      // Absen pembuat
    private String createdAt;      // Tanggal dibuat

    public Task(String id, String title, String subject, String deadline, String difficulty, String authorName, int authorAbsent, String createdAt) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.difficulty = difficulty;
        this.authorName = authorName;
        this.authorAbsent = authorAbsent;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getAuthorAbsent() {
        return authorAbsent;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}