package com.shadowfox.helpdesk;

import java.time.LocalDateTime;

public class Ticket {
    private final int ticketId;
    private String title;
    private String description;
    private TicketCategory category;
    private Priority priority;
    private TicketStatus status;
    private final User createdBy;
    private final LocalDateTime createdAt;

    public Ticket(int ticketId, String title, String description,
                  TicketCategory category, Priority priority, User createdBy) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void updateDetails(String title, String description,
                              TicketCategory category, Priority priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void displayDetails() {
        System.out.println("\n========== TICKET DETAILS ==========");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Category    : " + category);
        System.out.println("Priority    : " + priority);
        System.out.println("Status      : " + status);
        System.out.println("Created By  : " + createdBy.getName());
        System.out.println("User Email  : " + createdBy.getEmail());
        System.out.println("Created At  : " + createdAt);
        System.out.println("====================================");
    }
}