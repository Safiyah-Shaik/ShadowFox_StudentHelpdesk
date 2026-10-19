package com.shadowfox.helpdesk;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final HelpdeskService service = new HelpdeskService();

    private static final User defaultUser =
            new User(1, "Student", "student@example.com");

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   STUDENT HELPDESK SUPPORT SYSTEM");
        System.out.println("======================================");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    createTicket();
                    break;
                case 2:
                    viewAllTickets();
                    break;
                case 3:
                    searchTicket();
                    break;
                case 4:
                    updateTicket();
                    break;
                case 5:
                    updateStatus();
                    break;
                case 6:
                    closeTicket();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Student Helpdesk!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 to 7.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n-------------- MAIN MENU --------------");
        System.out.println("1. Create Ticket");
        System.out.println("2. View All Tickets");
        System.out.println("3. Search Ticket");
        System.out.println("4. Update Ticket");
        System.out.println("5. Update Ticket Status");
        System.out.println("6. Close Resolved Ticket");
        System.out.println("7. Exit");
        System.out.println("----------------------------------------");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
        private static void createTicket() {
        System.out.println("\n========== CREATE TICKET ==========");

        System.out.print("Enter ticket title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.println("\nSelect Category:");
        System.out.println("1. TECHNICAL");
        System.out.println("2. ACADEMIC");
        System.out.println("3. ACCOUNT");
        System.out.println("4. OTHER");

        int categoryChoice = readInt("Enter category choice: ");

        TicketCategory category;

        switch (categoryChoice) {
            case 1:
                category = TicketCategory.TECHNICAL;
                break;
            case 2:
                category = TicketCategory.ACADEMIC;
                break;
            case 3:
                category = TicketCategory.ACCOUNT;
                break;
            case 4:
                category = TicketCategory.OTHER;
                break;
            default:
                System.out.println("Invalid category choice.");
                return;
        }

        System.out.println("\nSelect Priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");

        int priorityChoice = readInt("Enter priority choice: ");

        Priority priority;

        switch (priorityChoice) {
            case 1:
                priority = Priority.LOW;
                break;
            case 2:
                priority = Priority.MEDIUM;
                break;
            case 3:
                priority = Priority.HIGH;
                break;
            default:
                System.out.println("Invalid priority choice.");
                return;
        }

        try {
            Ticket ticket = service.createTicket(
                    title, description, category, priority, defaultUser
            );

            System.out.println("\nTicket created successfully!");
            System.out.println("Your Ticket ID: " + ticket.getTicketId());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        private static void viewAllTickets() {
        System.out.println("\n========== ALL TICKETS ==========");

        List<Ticket> tickets = service.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        for (Ticket ticket : tickets) {
            ticket.displayDetails();
        }

        System.out.println("Total tickets: " + tickets.size());
    }
        private static void searchTicket() {
        System.out.println("\n========== SEARCH TICKET ==========");

        int ticketId = readInt("Enter Ticket ID: ");

        Ticket ticket = service.searchTicket(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found with ID: " + ticketId);
        } else {
            ticket.displayDetails();
        }
    }
        private static void updateTicket() {
        System.out.println("\n========== UPDATE TICKET ==========");

        int ticketId = readInt("Enter Ticket ID: ");

        Ticket existingTicket = service.searchTicket(ticketId);

        if (existingTicket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        System.out.println("Current ticket details:");
        existingTicket.displayDetails();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        System.out.println("\nSelect New Category:");
        System.out.println("1. TECHNICAL");
        System.out.println("2. ACADEMIC");
        System.out.println("3. ACCOUNT");
        System.out.println("4. OTHER");

        int categoryChoice = readInt("Enter category choice: ");

        if (categoryChoice < 1 || categoryChoice > 4) {
            System.out.println("Invalid category choice.");
            return;
        }

        TicketCategory category =
                TicketCategory.values()[categoryChoice - 1];

        System.out.println("\nSelect New Priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");

        int priorityChoice = readInt("Enter priority choice: ");

        if (priorityChoice < 1 || priorityChoice > 3) {
            System.out.println("Invalid priority choice.");
            return;
        }

        Priority priority = Priority.values()[priorityChoice - 1];

        try {
            boolean updated = service.updateTicket(
                    ticketId, title, description, category, priority
            );

            if (updated) {
                System.out.println("Ticket updated successfully!");
            } else {
                System.out.println("Ticket not found.");
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        private static void updateStatus() {
        System.out.println("\n========== UPDATE TICKET STATUS ==========");

        int ticketId = readInt("Enter Ticket ID: ");

        Ticket ticket = service.searchTicket(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        System.out.println("Current Status: " + ticket.getStatus());

        System.out.println("\nSelect New Status:");
        System.out.println("1. IN_PROGRESS");
        System.out.println("2. RESOLVED");

        int choice = readInt("Enter your choice: ");

        TicketStatus newStatus;

        switch (choice) {
            case 1:
                newStatus = TicketStatus.IN_PROGRESS;
                break;
            case 2:
                newStatus = TicketStatus.RESOLVED;
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        try {
            boolean updated = service.updateTicketStatus(ticketId, newStatus);

            if (updated) {
                System.out.println("Ticket status updated to " + newStatus);
            } else {
                System.out.println("Ticket not found.");
            }

        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        private static void closeTicket() {
        System.out.println("\n========== CLOSE TICKET ==========");

        int ticketId = readInt("Enter Ticket ID: ");

        Ticket ticket = service.searchTicket(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        System.out.println("Current Status: " + ticket.getStatus());

        try {
            boolean closed = service.closeTicket(ticketId);

            if (closed) {
                System.out.println("Ticket closed successfully!");
            } else {
                System.out.println("Ticket not found.");
            }

        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}