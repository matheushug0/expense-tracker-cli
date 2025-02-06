package view;

import model.Expense;
import service.ExpenseService;
import service.utils.ExpenseHandlers;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService();

        if (args.length < 1) {
            System.out.println("Usage: java expense-tracker [command]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                ExpenseHandlers.handleAddExpense(args, expenseService);
                break;
            case "list":
                ExpenseHandlers.handleListAllExpenses(expenseService);
                break;
            case "update", "delete", "summary":
                break;
            default:
                System.out.println("Unknown option");
        }

    }
}
