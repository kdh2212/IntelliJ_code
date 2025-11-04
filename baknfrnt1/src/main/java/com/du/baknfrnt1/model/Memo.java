package com.du.baknfrnt1.model;

public class Memo {
    private Long id;
    private String content;

    public Memo() {}

    public Memo(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

