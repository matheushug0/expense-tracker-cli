package view;

import model.Expense;
import service.ExpenseService;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService();

        if (args.length < 2) {
            System.out.println("Usage: java expense-tracker [command]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                handleAddExpense(args, expenseService);
                break;
            default:
                System.out.println("Unknown option");
        }

        System.out.println("ID   Date         Description     Amount");
        for (Expense e : expenseService.listAllExpenses()) {
            System.out.println(e);
        }
    }

    public static void handleAddExpense(String[] args, ExpenseService expenseService) {

        if (args.length < 4) {
            System.out.println("Usage: java expense-tracker [command] --description <description> --amount <amount>");
            return;
        }

        String description = null, amount = null;
        ListIterator<String> argsIterator = getArgsIterator(args);

        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--description") && argsIterator.hasNext()) {
                description = argsIterator.next();
            } else if (arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next();
            }
        }
        if (description == null || amount == null) {
            System.out.println("Usage: java expense-tracker add --description <description> --amount <amount>");
            return;
        }

        System.out.println(description + " " + amount);

        expenseService.createExpense(description, amount);
    }

    public static ListIterator<String> getArgsIterator(String[] args) {
        List<String> argList = Arrays.asList(args).subList(1, args.length);
        return argList.listIterator();
    }
}
