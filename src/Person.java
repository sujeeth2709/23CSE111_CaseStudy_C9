package Gym_management_system;


public abstract class Person {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;

    public Person(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public abstract void displayProfile();
}