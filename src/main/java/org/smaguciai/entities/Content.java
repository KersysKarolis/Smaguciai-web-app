package org.smaguciai.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="home-content")
public class Content {


    @Column(columnDefinition = "TEXT")
    private String allContent;
    @Id
    private String sectionKey;

    public Content(String sectionKey, String newContent) {
        this.allContent = newContent;
        this.sectionKey = sectionKey;
    }
    protected Content (){}


    public String getAllContent() {
        return allContent;
    }

    public void setAllContent(String allContent) {
        this.allContent = allContent;
    }

    public String getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(String sectionKey) {
        this.sectionKey = sectionKey;
    }
}
