package service.utils;

import model.Expense;
import service.ExpenseService;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

public class ExpenseHandlers {
    public static ListIterator<String> getArgsIterator(String[] args) {
        List<String> argList = Arrays.asList(args).subList(1, args.length);
        return argList.listIterator();
    }

    public static void handleCreateExpense(String[] args, ExpenseService expenseService) {
        if (args.length < 4) {
            System.out.println("Usage: java expense-tracker add --description <description> --amount <amount>");
            return;
        }

        String description = null, amount = null;
        ListIterator<String> argsIterator = getArgsIterator(args);

        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--description") && argsIterator.hasNext()) {
                description = argsIterator.next();
            } else if (arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next().replace(",", ".");
            }
        }
        if (description == null || amount == null || !amount.matches("\\d+[,.]\\d{2}")) {
            System.out.println("Usage: java expense-tracker add --description Description --amount 9,99");
            return;
        }
        expenseService.createExpense(description, amount);
    }

    public static void handleUpdateExpense(String[] args, ExpenseService expenseService) {
        if (args.length < 7) {
            System.out.println("Usage: java expense-tracker update --id <ID> --description <description> --amount <amount>");
            return;
        }
        Integer id = null;
        String description, amount;
        description = amount = null;
        ListIterator<String> argsIterator = getArgsIterator(args);
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--id") && argsIterator.hasNext()) {
                id = Integer.parseInt(argsIterator.next());
            } else if (arg.equals("--description") && argsIterator.hasNext()) {
                description = argsIterator.next();
            } else if (arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next().replace(",", ".");
            }
        }

        if (id == null || description == null || amount == null || !amount.matches("\\d+[,.]\\d{2}")) {
            System.out.println("Usage: java expense-tracker update --id 1 --description Description --amount 9,99");
            return;
        }
        expenseService.updateExpense(id, description, amount);

    }

    public static void handleDeleteExpense(String[] args, ExpenseService expenseService) {
        if (args.length < 3) {
            System.out.println("Usage: java expense-tracker delete --id <ID>");
            return;
        }
        Integer id = null;
        Iterator<String> argsIterator = getArgsIterator(args);
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--id") && argsIterator.hasNext()) {
                id = Integer.parseInt(argsIterator.next());
            }
        }
        if (id == null) {
            System.out.println("Usage: java expense-tracker delete --id 1");
        }
        expenseService.deleteExpense(id);
    }

    public static void handleListAllExpenses(ExpenseService expenseService) {
        if (expenseService.listAllExpenses().isEmpty()) {
            System.out.println("Expense list is empty");
            return;
        }
        System.out.println("ID   Date         Description     Amount");
        for (Expense e : expenseService.listAllExpenses()) {
            System.out.println(e);
        }
    }

    public static void handleSummaryExpense(String[] args, ExpenseService expenseService) {
        ListIterator<String> argsIterator = getArgsIterator(args);
        Integer month = null;
        BigDecimal summary = BigDecimal.valueOf(0);

        if (args.length < 2) {
            for (Expense e : expenseService.listAllExpenses()) {
                summary = summary.add(e.getAmount());
            }
            System.out.println("Total expenses: $" + summary);
            return;
        }

        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--month") && argsIterator.hasNext()) {
                month = Integer.valueOf(argsIterator.next());
            }
        }
        if (month == null) {
            System.out.println("Usage: java expense-tracker sumary --month <month>");
            return;
        }
        if (month < 1 || month > 12) {
            System.out.println("Usage: java expense-tracker summary --month 8");
            return;
        }
        for (Expense e : expenseService.listAllExpenses()) {
            if (e.getDate().getMonthValue() == month) {
                summary = summary.add(e.getAmount());
            }
        }
        System.out.println("Total expenses for " + Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ": $" + summary);

    }
}
