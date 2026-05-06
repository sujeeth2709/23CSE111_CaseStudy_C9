package Gym_management_system;

public class Trainer extends Person {
    private String specialization;
    private Member[] assignedMembers;
    private int memberCount;
    private double salary;

    public Trainer(String id, String name, String phoneNumber, String email, String specialization, double salary) {
        super(id, name, phoneNumber, email);
        this.specialization = specialization;
        this.salary = salary;
        this.assignedMembers = new Member[10];
        this.memberCount = 0;
    }

    public void assignMember(Member m) {
        if (memberCount < assignedMembers.length) {
            assignedMembers[memberCount++] = m;
            System.out.println(m.name + " assigned to trainer " + this.name);
        }
    }


    public void removeMember(Member m) {
        for (int i = 0; i < memberCount; i++) {
            if (assignedMembers[i] != null && assignedMembers[i].getId().equals(m.getId())) {
                System.out.println("Removed " + assignedMembers[i].name + " from trainer " + this.name);
                assignedMembers[i] = assignedMembers[memberCount - 1]; // Fill gap
                assignedMembers[memberCount - 1] = null;
                memberCount--;
                return;
            }
        }
    }


    public void getSchedule() {
        System.out.println("--- Schedule for " + name + " ---");
        System.out.println("Specialization: " + specialization);
        System.out.println("Currently training " + memberCount + " members.");
    }

    @Override
    public void displayProfile() {
        System.out.println("Trainer [ID: " + id + ", Name: " + name + ", Specialization: " + specialization + "]");
    }
}