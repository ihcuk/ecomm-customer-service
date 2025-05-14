package com.example.customerservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerRequest {

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Primary email is mandatory")
    @NotEmpty(message = "Primary email cannot be empty")
    @Email(message = "Primary email must be a valid email address")
    private String primaryEmail;

    private String secondaryEmail;

    @NotNull(message = "Primary phone is mandatory")
    @NotEmpty(message = "Primary phone cannot be empty")
    private String primaryPhone;

    private String secondaryPhone;

    @NotNull(message = "State is mandatory")
    @NotEmpty(message = "State cannot be empty")
    private String state;

    @NotNull(message = "Country is mandatory")
    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotNull(message = "Pin code is mandatory")
    @NotEmpty(message = "Pin code cannot be empty")
    private String pinCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
