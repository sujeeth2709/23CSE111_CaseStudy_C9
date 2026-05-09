package Gym_management_system;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GymManager implements GymOperations {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private double totalRevenue;

    public GymManager() {
        this.members = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.totalRevenue = 0.0;
    }

    @Override
    public boolean registerMember(Member m) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(m.getId())) {
                System.out.println("Member with this ID already exists.");
                return false;
            }
        }
        members.add(m);
        totalRevenue += m.getPlan().getPrice();
        System.out.println("Successfully registered: " + m.getId());
        return true;
    }

    public Trainer getOrCreateTrainer(String id, String name, String spec, double salary) {
        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).id.equals(id)) {
                return trainers.get(i);
            }
        }
        Trainer t = new Trainer(id, name, "9000000000", id + "@gym.com", spec, salary);
        trainers.add(t);
        return t;
    }

    @Override
    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (int i = 0; i < members.size(); i++) {
            members.get(i).displayProfile();
        }
    }
    public void showAllTrainers() {
        if (trainers.isEmpty()) {
            System.out.println("No trainers registered in the system.");
            return;
        }

        System.out.println("\n===== GYM STAFF LIST =====");

        for (int i = 0; i < trainers.size(); i++) {
            Trainer t = trainers.get(i);
            t.displayProfile();
            t.getSchedule();
            System.out.println("--------------------------");
        }
    }
    public Trainer findTrainerById(String id) {
        // CO3: Advanced logic to handle case-insensitivity
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < trainers.size(); i++) {
            if (trainers.get(i).id.equalsIgnoreCase(id.trim())) {
                return trainers.get(i);
            }
        }
        return null;
    }

    public void removeExpiredMembers() {
        Date today = new Date();
        int removedCount = 0;


        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getExpiryDate().before(today)) {
                Member expired = members.get(i);
                System.out.println("Removing expired member: " + expired.getId());


                for (int j = 0; j < trainers.size(); j++) {
                    trainers.get(j).removeMember(expired);
                }

                members.remove(i);
                i--;
                removedCount++;
            }
        }
        System.out.println("Cleanup finished. Total removed: " + removedCount);
    }

    @Override
    public void generateRevenueReport() {
        System.out.println("--- Gym Revenue Report ---");
        System.out.println("Total Members: " + members.size());
        System.out.println("Total Revenue: Rs" + String.format("%.2f", totalRevenue));
    }

    @Override
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < members.size(); i++) {
                Member m = members.get(i);
                writer.println(m.getId() + "," + m.name + "," + m.getPlan().getPlanName());
            }
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Loaded: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}