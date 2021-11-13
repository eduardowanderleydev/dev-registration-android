package br.com.ucsal.devregistration.domain;

import java.io.Serializable;

public class Resume implements Serializable {

    private String name;

    private Adress adress;

    private String phoneNumber;

    private String email;

    private String education;

    private String professionalExperiences;

    private String goal;

    private String knowledgeAndSkills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
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
}
