package org.smaguciai.entities;

import jakarta.persistence.*;

@Entity
@Table(name="home_images",
        uniqueConstraints = @UniqueConstraint(columnNames = {"section", "contentKey"})
)
public class HomeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String section; // header, about, etc..
    private String fileName;
    private String contentKey; // eiliskumas 0-5

    protected HomeImage(){}

    public HomeImage(String section, String fileName, String contentKey) {
        this.section = section;
        this.fileName = fileName;
        this.contentKey = contentKey;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }
}
