package com.example.xipplgb_infospace.model;

public class ChecklistItem {
    private final String id;
    private final String title;
    private boolean isChecked;

    public ChecklistItem(String id, String title, boolean isChecked) {
        this.id = id;
        this.title = title;
        this.isChecked = isChecked;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
