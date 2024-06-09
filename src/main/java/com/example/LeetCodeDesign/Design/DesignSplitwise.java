package com.example.LeetCodeDesign.Design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Create an expense sharing application.
An expense sharing application is where you can add your expenses and split it among different people.
The app keeps balances between people as in who owes how much to whom.

Example

You live with 3 other friends.
You: User1 (id: u1)
Flatmates: User2 (u2), User3 (u3), User4 (u4)

---

This month's electricity bill was Rs. 1000.
Now you can just go to the app and add that you paid 1000,
select all the 4 people and then select split equally.
Input: u1 1000 4 u1 u2 u3 u4 EQUAL
For this transaction, everyone owes 250 to User1.
The app should update the balances in each of the profiles accordingly. User2 owes User1: 250 (0+250)
User3 owes User1: 250 (0+250)
User4 owes User1: 250 (0+250)
---

Now, It is the BBD sale on Flipkart and there is an offer on your card.
You buy a few stuffs for User2 and User3 as they asked you to.
The total amount for each person is different.
Input: u1 1250 2 u2 u3 EXACT 370 880
For this transaction, User2 owes 370 to User1 and User3 owes 880 to User1.
The app should update the balances in each of the profiles accordingly.
User2 owes User1: 620 (250+370)
User3 owes User1: 1130 (250+880)
User4 owes User1: 250 (250+0)

---

Now, you go out with your flatmates and take your brother/sister along with you.
User4 pays and everyone splits equally. You owe for 2 people.
Input: u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
For this transaction, User1 owes 480 to User4, User2 owes 240 to User4 and User3 owes 240 to User4.
The app should update the balances in each of the profiles accordingly.
User1 owes User4: 230 (250-480)
User2 owes User1: 620 (620+0)

User2 owes User4: 240 (0+240)

User3 owes User1: 1130 (1130+0)

User3 owes User4: 240 (0+240)

Requirements

User: Each user should have a userId, name, email, mobile number.

Expense: Could either be EQUAL, EXACT or PERCENT

Users can add any amount, select any type of expense and split with any of the available users.

The percent and amount provided could have decimals upto two decimal places.

In case of percent, you need to verify if the total sum of percentage shares is 100 or not.

In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.

The application should have a capability to show expenses for a single user as well as balances for everyone.

When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.

The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.

Input

You can create a few users in your main method. No need to take it as input.

There will be 3 types of input:

Expense in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

Show balances for all: SHOW

Show balances for a single user: SHOW <user-id>

Output

When asked to show balance for a single user. Show all the balances that user is part of:

Format: <user-id-of-x> owes <user-id-of-y>: <amount>

If there are no balances for the input, print No balances

In cases where the user for which balance was asked for, owes money, they’ll be x. They’ll be y otherwise.
 */
public class DesignSplitwise {

    public static void main(String[] args) {

        ExpenseSharingApp app = new ExpenseSharingApp();

        // Adding users
        app.addUser(new User("u1", "User1", "user1@example.com", "1234567890"));
        app.addUser(new User("u2", "User2", "user2@example.com", "1234567891"));
        app.addUser(new User("u3", "User3", "user3@example.com", "1234567892"));
        app.addUser(new User("u4", "User4", "user4@example.com", "1234567893"));

        // Adding expenses
        app.addExpense(new Expense("u1", 1000, 4, Arrays.asList("u1", "u2", "u3", "u4"), ExpenseType.EQUAL, null));
        app.addExpense(new Expense("u1", 1250, 2, Arrays.asList("u2", "u3"), ExpenseType.EXACT, Arrays.asList(370.0, 880.0)));
        app.addExpense(new Expense("u4", 1200, 4, Arrays.asList("u1", "u2", "u3", "u4"), ExpenseType.PERCENT, Arrays.asList(40.0, 20.0, 20.0, 20.0)));

        // Showing balances
        app.showBalances();
        System.out.println("-------------");
        app.showBalancesForUser("u1");
    }
}

class ExpenseSharingApp {
    Map<String, User> users;
    Map<String, Double> balances;

    public ExpenseSharingApp() {
        users = new HashMap<>();
        balances = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.userId, user);
    }

    public void addExpense(Expense expense) {
        if (expense.expenseType.equals("EQUAL")) {
            double share = expense.totalAmount / expense.numOfUsers;
            for (String user : expense.usersInvolved) {
                if (!user.equals(expense.paidBy)) {
                    updateBalances(user, expense.paidBy, share);
                }
            }
        } else if (expense.expenseType.equals("EXACT")) {
            for (int i = 0; i < expense.numOfUsers; i++) {
                if (!expense.usersInvolved.get(i).equals(expense.paidBy)) {
                    updateBalances(expense.usersInvolved.get(i), expense.paidBy, expense.shares.get(i));
                }
            }
        } else if (expense.expenseType.equals("PERCENT")) {
            double totalPercent = 0;
            for (double percent : expense.shares) {
                totalPercent += percent;
            }
            if (totalPercent != 100) {
                System.out.println("Error: Total percentage shares must be 100.");
                return;
            }
            for (int i = 0; i < expense.numOfUsers; i++) {
                double share = (expense.totalAmount * expense.shares.get(i)) / 100;
                if (!expense.usersInvolved.get(i).equals(expense.paidBy)) {
                    updateBalances(expense.usersInvolved.get(i), expense.paidBy, share);
                }
            }
        }
    }

    public void updateBalances(String user1, String user2, double amount) {
        String key1 = user1 + "-" + user2; // For user1 owes user2
        String key2 = user2 + "-" + user1; // For user2 owes user1

        double balance1 = balances.getOrDefault(key1, 0.0);
        double balance2 = balances.getOrDefault(key2, 0.0);

        balances.put(key1, balance1 + amount);
        balances.put(key2, balance2 - amount);
    }

    public void showBalances() {
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            if (entry.getValue() != 0) {
                String[] usersInvolved = entry.getKey().split("-");
                String user1 = usersInvolved[0];
                String user2 = usersInvolved[1];
                double amount = Math.round(entry.getValue() * 100.0) / 100.0;
                if (amount > 0) {
                    System.out.println(user1 + " owes " + user2 + ": " + amount);
                } else {
                    System.out.println(user2 + " owes " + user1 + ": " + (-amount));
                }
            }
        }
    }

    public void showBalancesForUser(String userId) {
        boolean found = false;
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            String[] usersInvolved = entry.getKey().split("-");
            String user1 = usersInvolved[0];
            String user2 = usersInvolved[1];
            if (user1.equals(userId) || user2.equals(userId)) {
                double amount = Math.round(entry.getValue() * 100.0) / 100.0;
                found = true;
                if (user1.equals(userId)) {
                    System.out.println(user1 + " owes " + user2 + ": " + amount);
                } else {
                    System.out.println(user2 + " owes " + user1 + ": " + (-amount));
                }
            }
        }
        if (!found) {
            System.out.println("No balances");
        }
    }
}


class User {
    String userId;
    String name;
    String email;
    String mobileNumber;

    User(String userId, String name, String email, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}

class Expense {
    String paidBy;
    double totalAmount;
    int numOfUsers;
    List<String> usersInvolved;
    ExpenseType expenseType;
    List<Double> shares;

    Expense(String paidBy, double totalAmount, int numOfUsers, List<String> usersInvolved, ExpenseType expenseType, List<Double> shares) {
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.numOfUsers = numOfUsers;
        this.usersInvolved = usersInvolved;
        this.expenseType = expenseType;
        this.shares = shares;
    }
}

enum ExpenseType {

    EQUAL,
    EXACT,
    PERCENT

}