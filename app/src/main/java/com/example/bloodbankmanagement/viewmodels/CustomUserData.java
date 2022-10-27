package com.example.bloodbankmanagement.viewmodels;

import java.io.Serializable;


public class CustomUserData implements Serializable {
   private String Address,Amount,BloodGroup,Contact,Date,DateTime,Division,HospitalName,Message,
           Name,Time,Uid;

   public CustomUserData() {

    }

    public CustomUserData(String address, String amount, String bloodGroup, String contact, String date,
                          String dateTime, String division, String hospitalName, String message, String name,
                          String time, String uid) {
        Address = address;
        Amount = amount;
        BloodGroup = bloodGroup;
        Contact = contact;
        Date = date;
        DateTime = dateTime;
        Division = division;
        HospitalName = hospitalName;
        Message = message;
        Name = name;
        Time = time;
        Uid = uid;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
