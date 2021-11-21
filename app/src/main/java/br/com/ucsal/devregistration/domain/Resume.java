package br.com.ucsal.devregistration.domain;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "resume")
public class Resume implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resume_id")
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String education;
    private String professionalExperiences;
    private String goal;
    private String knowledgeAndSkills;

    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfessionalExperiences() {
        return professionalExperiences;
    }

    public void setProfessionalExperiences(String professionalExperiences) {
        this.professionalExperiences = professionalExperiences;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getKnowledgeAndSkills() {
        return knowledgeAndSkills;
    }

    public void setKnowledgeAndSkills(String knowledgeAndSkills) {
        this.knowledgeAndSkills = knowledgeAndSkills;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
