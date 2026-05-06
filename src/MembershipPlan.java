package Gym_management_system;

public class MembershipPlan {
    private String planName;
    private int durationInMonths;
    private double price;

    public MembershipPlan(String planName, int durationInMonths, double price) {
        this.planName = planName;
        this.durationInMonths = durationInMonths;
        this.price = price;
    }

    public void getPlanDetails() {
        System.out.println("Plan: " + planName + " | Duration: " + durationInMonths + " months | Price: Rs" + price);
    }

    public void getDiscount() {
        if (durationInMonths >= 12) {
            System.out.println("Eligible for a 10% annual discount!");
        } else {
            System.out.println("No discount available for this plan.");
        }
    }

    public int getDuration() { return durationInMonths; }
    public String getPlanName() { return planName; }
    public double getPrice() { return price; }
}