package Gym_management_system;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GymManager manager = new GymManager();

        int choice;

        do {
            System.out.println("\n--- GYM MANAGEMENT SYSTEM ---");
            System.out.println("1. Register Member");
            System.out.println("2. Show All Members");
            System.out.println("3. Show All Trainers");
            System.out.println("4. View Specific Trainer Schedule");
            System.out.println("5. Remove Expired Members");
            System.out.println("6. Revenue Report");
            System.out.println("7. Save/Load Data");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Select Plan: ");
                    MembershipPlan monthly = new MembershipPlan("Monthly", 1, 500);
                    MembershipPlan quarterly = new MembershipPlan("Quarterly", 3, 1200);
                    MembershipPlan yearly = new MembershipPlan("Yearly", 12, 5000);

                    System.out.print("1. "); monthly.getPlanDetails();
                    System.out.print("2. "); quarterly.getPlanDetails();
                    System.out.print("3. "); yearly.getPlanDetails();

                    System.out.print("Select Plan: ");
                    int p = sc.nextInt(); sc.nextLine();

                    MembershipPlan selectedPlan = (p == 1) ? monthly : (p == 2) ? quarterly : yearly;

                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();

                    Member member = new Member(id, name, "000", "email@gym.com", selectedPlan);

                    if (manager.registerMember(member)) {
                        System.out.println("Assign Trainer? 1.FatLoss 2.Strength 3.No");
                        int tChoice = sc.nextInt(); sc.nextLine();
                        if (tChoice != 3) {
                            String spec = (tChoice == 1) ? "FatLoss" : "Strength";
                            Trainer trainer = manager.getOrCreateTrainer("Tr-" + spec, spec + " Coach", spec, 3000.0);
                            trainer.assignMember(member);
                        }
                    }
                    break;

                case 2:
                    manager.showAllMembers();
                    break;

                case 3:
                    manager.showAllTrainers();
                    break;

                case 4:
                    try {
                        System.out.println("Enter Trainer ID (e.g., Tr-FatLoss): ");
                        String tid = sc.nextLine();

                        Trainer foundTrainer = manager.findTrainerById(tid);

                        if (foundTrainer != null) {

                            foundTrainer.getSchedule();
                        } else {
                            System.out.println(">>> Error: No trainer found with ID '" + tid + "'. (Search is case-insensitive)");
                        }
                    } catch (Exception e) {

                        System.out.println("An error occurred during search: " + e.getMessage());
                    }
                    break;

                case 5:
                    manager.removeExpiredMembers();
                    break;

                case 6:
                    manager.generateRevenueReport();
                    break;

                case 7:
                    manager.saveToFile("gym_data.txt");
                    manager.loadFromFile("gym_data.txt");
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}