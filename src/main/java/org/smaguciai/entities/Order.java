package org.smaguciai.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.smaguciai.enumerators.Characters;
import org.smaguciai.enumerators.Performer;
import org.smaguciai.enumerators.OrderGenre;
import org.smaguciai.enumerators.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String childName;
    private Integer age;
    private Integer amountOfChildren;
    @Enumerated(EnumType.STRING)
    private Characters character;
    private String location;
    private String phoneNumber;
    @NotBlank(message="El. paštas privalomas")
    @Email(message="Netinkamas el. pašto formatas")
    private String email;
    private String notes;
    @Enumerated(EnumType.STRING)
    private OrderGenre orderGenre;
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private Performer performer;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAmountOfChildren() {
        return amountOfChildren;
    }

    public void setAmountOfChildren(Integer amountOfChildren) {
        this.amountOfChildren = amountOfChildren;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderGenre getOrderGenre() {
        return orderGenre;
    }

    public void setOrderGenre(OrderGenre orderGenre) {
        this.orderGenre = orderGenre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}