package com.abhibarkade.course.profile;

public class UserDetails {

    String name, mail, enrolledIn;

    public UserDetails() {
    }

    public UserDetails(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public UserDetails(String name, String mail, String enrolledIn) {
        this.name = name;
        this.mail = mail;
        this.enrolledIn = enrolledIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEnrolledIn() {
        return enrolledIn;
    }

    public void setEnrolledIn(String enrolledIn) {
        this.enrolledIn = enrolledIn;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", enrolledIn='" + enrolledIn + '\'' +
                '}';
    }
}
