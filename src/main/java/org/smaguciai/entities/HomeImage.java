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
    private String publicId;

    private String imageTitle;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    protected HomeImage(){}

    public HomeImage(String section, String fileName, String contentKey, String imageTitle) {
        this.section = section;
        this.fileName = fileName;
        this.contentKey = contentKey;
        this.imageTitle=imageTitle;
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
