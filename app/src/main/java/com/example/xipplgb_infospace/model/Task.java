package com.example.xipplgb_infospace.model;

public class Task {
    private String id;
    private String title;          // Nama tugas
    private String subject;        // Mata pelajaran
    private String deadline;       // Tanggal deadline
    private String difficulty;     // Biasanya "-" atau "Biasa"
    private String authorName;     // Nama pembuat
    private int authorAbsent;      // Absen pembuat
    private String createdAt;      // Tanggal dibuat
    private boolean isCompleted;   // Status tugas (Selesai / Belum)
    private String description;   // Detail deskripsi tugas

    public Task(String id, String title, String subject, String deadline, String difficulty, String authorName, int authorAbsent, String createdAt, boolean isCompleted, String description) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.difficulty = difficulty;
        this.authorName = authorName;
        this.authorAbsent = authorAbsent;
        this.createdAt = createdAt;
        this.isCompleted = isCompleted;
        this.description = (description != null && !description.trim().isEmpty()) ? description : "Belum ada deskripsi tugas.";
    }

    public Task(String id, String title, String subject, String deadline, String difficulty, String authorName, int authorAbsent, String createdAt, boolean isCompleted) {
        this(id, title, subject, deadline, difficulty, authorName, authorAbsent, createdAt, isCompleted, "Belum ada deskripsi tugas.");
    }

    public Task(String id, String title, String subject, String deadline, String difficulty, String authorName, int authorAbsent, String createdAt) {
        this(id, title, subject, deadline, difficulty, authorName, authorAbsent, createdAt, false, "Belum ada deskripsi tugas.");
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return (description != null && !description.trim().isEmpty()) ? description : "Belum ada deskripsi tugas.";
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
