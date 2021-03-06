package com.example.entity;

import javax.persistence.*;

/**
 * Created by ronnen on 18-Jan-2021
 */

@Entity(name = "Student")
@Table(name = "STUDENT")
public class Student {

    @Id // 'GenerationType.IDENTITY' is the preferred way using MySql
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "ID")
    private Long Id;

    @Column(name = "FNAME", nullable = false)
    private String firstName;

    @Column(name = "LNAME", unique = true, length = 25)
    private String lastName;

    @Column(name = "CONTACT_NO", length = 15)
    private String contactNo;

    public Student() {
    }

    public Student(String firstName, String lastName, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long studentId) {
        this.Id = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}