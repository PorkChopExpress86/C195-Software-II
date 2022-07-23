package model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateAndTime, LocalDateTime endDateAndTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }



    public LocalDate getStartDate() {
//        return this.startDateAndTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        Using a date picker now so the return needs to be a LocalDate type
        return this.startDateAndTime.atZone(ZoneId.systemDefault()).toLocalDate();
//        return this.startDateAndTime.toLocalDateTime().toLocalDate();
    }

    public String getStartHour() {
        return this.startDateAndTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    public String getStartMinute() {
        return this.startDateAndTime.format(DateTimeFormatter.ofPattern("mm"));
    }

    public String getEndHour() {
        return this.endDateAndTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    public String getEndMinute() {
        return this.endDateAndTime.format(DateTimeFormatter.ofPattern("mm"));
    }

    public LocalDate getEndDate() {
//        Using a date picker now so the return needs to be a LocalDate type
        return this.endDateAndTime.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getEndTime() {
        return this.endDateAndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(LocalDateTime startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public LocalDateTime getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(LocalDateTime endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
