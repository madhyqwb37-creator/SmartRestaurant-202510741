import java.util.Scanner;

public class SmartRestaurantOrder {
    public static void main(String[] args) {

        // Constants
        final double DRINK_PRICE = 15.50;
        final double STUDENT_DISCOUNT_RATE = 0.10;
        final double SENIOR_DISCOUNT_RATE = 0.05;
        final double TIP_RATE = 0.10;
        final int SENIOR_AGE = 60;

        try (Scanner input = new Scanner(System.in)) {

            // Input
            System.out.print("Customer name: ");
            String customerName = input.nextLine();

            System.out.print("Customer age: ");
            int age = input.nextInt();
            if (age < 0) {
                System.out.println("Error: Age cannot be negative.");
                return;
            }

            System.out.print("Meal price: ");
            double mealPrice = input.nextDouble();
            if (mealPrice < 0) {
                System.out.println("Error: Meal price cannot be negative.");
                return;
            }

            System.out.print("Number of drinks: ");
            int numberOfDrinks = input.nextInt();
            if (numberOfDrinks < 0) {
                System.out.println("Error: Number of drinks cannot be negative.");
                return;
            }

            System.out.print("Is the customer a student? (true/false): ");
            boolean isStudent = input.nextBoolean();

            System.out.print("Service rating (1-5): ");
            int serviceRating = input.nextInt();

            // Validate rating first
            if (serviceRating < 1 || serviceRating > 5) {
                System.out.println("Error: Service rating must be between 1 and 5.");
                return;
            }

            // Calculations
            double drinksCost = numberOfDrinks * DRINK_PRICE;
            double subtotal = mealPrice + drinksCost;

            // Only one discount: student (10%) wins over senior (5%) if both apply
            double discountRate = 0.0;
            String discountType = "None";
            if (isStudent) {
                discountRate = STUDENT_DISCOUNT_RATE;
                discountType = "Student (10%)";
            } else if (age >= SENIOR_AGE) {
                discountRate = SENIOR_DISCOUNT_RATE;
                discountType = "Senior (5%)";
            }
            double discount = subtotal * discountRate;

            // Tip: 10% of subtotal when rating is 4 or 5
            double tip = (serviceRating >= 4) ? subtotal * TIP_RATE : 0.0;

            double finalBill = subtotal - discount + tip;

            // Cast to int
            int wholeAmount = (int) finalBill;

            // Receipt
            System.out.println("\n================================");
            System.out.println("        SMART RESTAURANT        ");
            System.out.println("================================");
            System.out.printf("Customer: %s%n", customerName);
            System.out.printf("Meal Price: $%.2f%n", mealPrice);
            System.out.printf("Drinks (%d): $%.2f%n", numberOfDrinks, drinksCost);
            System.out.println("--------------------------------");
            System.out.printf("Subtotal: $%.2f%n", subtotal);
            System.out.printf("Discount (%s): -$%.2f%n", discountType, discount);
            System.out.printf("Tip: +$%.2f%n", tip);
            System.out.println("--------------------------------");
            System.out.printf("Final Bill: $%.2f%n", finalBill);
            System.out.printf("Whole Amount to Pay: $%d%n", wholeAmount);
            System.out.println("================================");
        }
    }
}