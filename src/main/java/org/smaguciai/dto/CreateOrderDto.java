package org.smaguciai.dto;

import jakarta.validation.constraints.*;
import org.smaguciai.enumerators.Characters;
import org.smaguciai.enumerators.OrderGenre;

import java.time.LocalDateTime;

public class CreateOrderDto {
    @NotBlank(message = "Vaiko vardas privalomas")
    @Size(max=100)
    private String childName;
    @Min(1)
    @Max(18)
    @NotNull(message="Įveskite amžių")
    private Integer age;
    @Min(1)
    @Max(50)
    @NotNull(message="Įveskite vaikų kiekį")
    private Integer amountOfChildren;
    @NotNull(message = "Pasirinkite personažą")
    private Characters character;
    @NotBlank
    @Size(max = 255)
    @Pattern(
            regexp = "^[A-Za-zĄČĘĖĮŠŲŪŽąćęėįšųūž\\s]+,\\s*[A-Za-zĄČĘĖĮŠŲŪŽąćęėįšųūž\\s]+\\s+\\d+.*$",
            message = "Adresas turi būti: Miestas, Gatvė, pastato nr."
    )
    private String location;
    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Telefono numeris netinkamo formato")
    private String phoneNumber;
    @NotBlank(message="El. paštas privalomas")
    @Email(message="Netinkamas el. pašto formatas")
    private String email;
    @Size(max= 1000)
    private String notes;
    @NotNull(message ="Pasirinkite šventės žanrą")
    private OrderGenre orderGenre;
    @Size(max=150)
    private String title;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

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

    public OrderGenre getOrderGenre() {
        return orderGenre;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setOrderGenre(OrderGenre orderGenre) {
        this.orderGenre = orderGenre;
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
