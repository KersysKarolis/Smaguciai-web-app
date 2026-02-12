package org.smaguciai.entities;

import jakarta.persistence.*;

@Entity
@Table(name="home_images",
        uniqueConstraints = @UniqueConstraint(columnNames = {"section", "position"})
)
public class HomeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String section; // header, about, etc..
    private String fileName;
    private int position; // eiliskumas 0-5

    protected HomeImage(){}

    public HomeImage(String section, String fileName, int position) {
        this.section = section;
        this.fileName = fileName;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
