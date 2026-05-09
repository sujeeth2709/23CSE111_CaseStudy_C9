package Gym_management_system;

import java.util.ArrayList;

public class Trainer extends Person {
    private String specialization;
    private ArrayList<Member> assignedMembers; // CO4: ArrayList for dynamic membership
    private double salary;

    public Trainer(String id, String name, String phone, String email, String spec, double salary) {
        super(id, name, phone, email);
        this.specialization = spec;
        this.salary = salary;
        this.assignedMembers = new ArrayList<>();
    }

    public void assignMember(Member m) {
        assignedMembers.add(m);
        System.out.println(m.name + " assigned to trainer " + this.name);
    }

    public void removeMember(Member m) {
        for (int i = 0; i < assignedMembers.size(); i++) {
            if (assignedMembers.get(i).getId().equals(m.getId())) {
                assignedMembers.remove(i);
                System.out.println("Removed " + m.name + " from " + name);
                return;
            }
        }
    }
    public void getSchedule() {
        System.out.println("\n--- Schedule for " + name + " ---");
        System.out.println("Specialization: " + specialization);
        if (assignedMembers.isEmpty()) {
            System.out.println("Status: No members currently assigned.");
        } else {
            System.out.println("Assigned Members:");
            for (int i = 0; i < assignedMembers.size(); i++) {
                Member m = assignedMembers.get(i);
                System.out.println(" - " + m.name + " (ID: " + m.getId() + ")");
            }
        }
    }
    @Override
    public void displayProfile() {
        System.out.println("Trainer [ID: " + id + ", Name: " + name + ", Specialty: " + specialization + "]");
    }
}