package Gym_management_system;

import java.io.*;
import java.util.Date;

public class GymManager {
    private Member[] members;
    private Trainer[] trainers;
    private int memberCount;
    private int trainerCount;
    private double totalRevenue;

    public GymManager(int memberCapacity, int trainerCapacity) {
        this.members = new Member[memberCapacity];
        this.trainers = new Trainer[trainerCapacity];
        this.memberCount = 0;
        this.trainerCount = 0;
        this.totalRevenue = 0.0;
    }

    public void registerMember(Member m) {
        if (memberCount < members.length) {
            members[memberCount++] = m;
            totalRevenue += m.getPlan().getPrice();
            System.out.println("Successfully registered: " + m.getId());
        } else {
            System.out.println("Member capacity reached.");
        }
    }


    public void generateRevenueReport() {
        System.out.println("--- Gym Revenue Report ---");
        System.out.println("Total Members: " + memberCount);
        System.out.println("Total Revenue Collected: $" + totalRevenue);
        System.out.println("--------------------------");
    }

    public void findMemberById(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i] != null && members[i].getId().equals(id)) {
                members[i].displayProfile();
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void removeExpiredMembers() {
        Date today = new Date();
        int originalCount = memberCount;
        for (int i = 0; i < memberCount; i++) {
            if (members[i] != null && members[i].getExpiryDate().before(today)) {
                System.out.println("Removing expired member: " + members[i].getId());
                members[i] = members[memberCount - 1]; // Fill the gap
                members[memberCount - 1] = null;
                memberCount--;
                i--;
            }
        }
        if(originalCount == memberCount) {
            System.out.println("No expired members found.");
        }
    }


    public void saveMembersToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < memberCount; i++) {
                Member m = members[i];
                writer.println(m.getId() + "," + m.name + "," + m.phoneNumber + "," + m.email + "," + m.getPlan().getPlanName());
            }
            System.out.println("Data successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadMembersFromFile(String filename) {
        System.out.println("\n--- Loading Members from File ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Loaded record: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
