package Gym_management_system;

public interface GymOperations {
    boolean registerMember(Member m);
    void showAllMembers();
    void generateRevenueReport();
    void saveToFile(String filename);
    void loadFromFile(String filename);
}