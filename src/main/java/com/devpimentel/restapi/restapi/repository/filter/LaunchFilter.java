package com.devpimentel.restapi.restapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LaunchFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateOf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateTo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDateOf() {
        return expirationDateOf;
    }

    public void setExpirationDateOf(LocalDate expirationDateOf) {
        this.expirationDateOf = expirationDateOf;
    }

    public LocalDate getExpirationDateTo() {
        return expirationDateTo;
    }

    public void setExpirationDateTo(LocalDate expirationDateTo) {
        this.expirationDateTo = expirationDateTo;
    }
}
