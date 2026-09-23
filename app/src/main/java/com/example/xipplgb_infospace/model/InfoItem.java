package com.example.xipplgb_infospace.model;

public class InfoItem {
    private String id;
    private String title;          // Judul info
    private String content;        // Isi pengumuman
    private String authorName;     // Nama pembuat
    private int authorAbsent;      // Absen pembuat
    private String createdAt;      // Tanggal dibuat

    public InfoItem(String id, String title, String content, String authorName, int authorAbsent, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
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