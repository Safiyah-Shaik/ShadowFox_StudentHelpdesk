package com.shadowfox.helpdesk;

import java.util.ArrayList;
import java.util.List;

public class HelpdeskService {

    private final List<Ticket> tickets = new ArrayList<>();
    private int nextTicketId = 1001;

    public Ticket createTicket(String title, String description,
                               TicketCategory category, Priority priority,
                               User user) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket title cannot be empty.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is required.");
        }

        Ticket ticket = new Ticket(
                nextTicketId++,
                title.trim(),
                description.trim(),
                category,
                priority,
                user
        );

        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public Ticket searchTicket(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }
        public boolean updateTicket(int ticketId, String title, String description,
                                TicketCategory category, Priority priority) {

        Ticket ticket = searchTicket(ticketId);

        if (ticket == null) {
            return false;
        }

        if (ticket.getStatus() == TicketStatus.RESOLVED
                || ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException(
                    "Resolved or closed tickets cannot be updated.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        ticket.updateDetails(
                title.trim(),
                description.trim(),
                category,
                priority
        );

        return true;
    }

    public boolean updateTicketStatus(int ticketId, TicketStatus newStatus) {
        Ticket ticket = searchTicket(ticketId);

        if (ticket == null) {
            return false;
        }

        TicketStatus currentStatus = ticket.getStatus();

        if (currentStatus == TicketStatus.CLOSED
                || currentStatus == TicketStatus.RESOLVED) {
            throw new IllegalStateException(
                    "Resolved or closed tickets cannot change status.");
        }

        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }

        if (currentStatus == TicketStatus.OPEN
                && newStatus != TicketStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "An open ticket must move to IN_PROGRESS first.");
        }

        if (currentStatus == TicketStatus.IN_PROGRESS
                && newStatus != TicketStatus.RESOLVED) {
            throw new IllegalStateException(
                    "An in-progress ticket can only move to RESOLVED.");
        }

        ticket.setStatus(newStatus);
        return true;
    }

    public boolean closeTicket(int ticketId) {
        Ticket ticket = searchTicket(ticketId);

        if (ticket == null) {
            return false;
        }

        if (ticket.getStatus() != TicketStatus.RESOLVED) {
            throw new IllegalStateException(
                    "Only resolved tickets can be closed.");
        }

        ticket.setStatus(TicketStatus.CLOSED);
        return true;
    }
}