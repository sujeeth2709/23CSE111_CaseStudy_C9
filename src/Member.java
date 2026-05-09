package Gym_management_system;

import java.util.Date;
import java.util.Calendar;

public class Member extends Person {
    private MembershipPlan plan;
    private Date joinDate;
    private Date expiryDate;
    private String paymentStatus;

    public Member(String id, String name, String phoneNumber, String email, MembershipPlan plan) {
        super(id, name, phoneNumber, email);
        this.plan = plan;
        this.joinDate = new Date();
        this.paymentStatus = "Paid";
        calculateExpiry();
    }

    public void calculateExpiry() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(joinDate);
        cal.add(Calendar.MONTH, plan.getDuration());
        this.expiryDate = cal.getTime();
    }

    public void renewMembership(MembershipPlan newPlan) {
        this.plan = newPlan;
        calculateExpiry();
        this.paymentStatus = "Paid";
        System.out.println("Membership renewed for " + name);
    }

    public void checkAccess() {
        Date today = new Date();
        if (expiryDate.after(today) && paymentStatus.equals("Paid")) {
            System.out.println("Access Granted for " + name);
        } else {
            System.out.println("Access Denied for " + name + ". Please renew.");
        }
    }
    @Override
    public void displayProfile() {
        System.out.println("------------------------------------------");
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        plan.getPlanDetails();
        plan.getDiscount();

        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("------------------------------------------");
    }


    public String getId() { return id; }
    public Date getExpiryDate() { return expiryDate; }
    public MembershipPlan getPlan() { return plan; }
}