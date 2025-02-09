package view;

import service.ExpenseService;
import service.utils.ExpenseHandlers;

public class ExpenseTracker {
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService();

        if (args.length < 1) {
            System.out.println("Usage: java expense-tracker-cli-1.0 [command]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                ExpenseHandlers.handleCreateExpense(args, expenseService);
                break;
            case "list":
                ExpenseHandlers.handleListAllExpenses(args, expenseService);
                break;
            case "update":
                ExpenseHandlers.handleUpdateExpense(args, expenseService);
                break;
            case "delete":
                ExpenseHandlers.handleDeleteExpense(args, expenseService);
                break;
            case "summary":
                ExpenseHandlers.handleSummaryExpense(args, expenseService);
                break;
            case "budget":
                ExpenseHandlers.handleSaveBugdget(args, expenseService);
                break;
            case "csv":
                ExpenseHandlers.handleExportCsv(expenseService);
                break;
            case "clear":
                ExpenseHandlers.handleClearExpenses(expenseService);
                break;
            default:
                System.out.println("Unknown option");
        }
    }
}
