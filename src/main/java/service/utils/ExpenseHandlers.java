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
            System.out.println("Usage: java expense-tracker add --description <description> --amount <amount> --category <category>");
            return;
        }

        String description = null, amount = null;
        Integer category = 0;
        ListIterator<String> argsIterator = getArgsIterator(args);

        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--description") && argsIterator.hasNext()) {
                description = argsIterator.next();
            } else if (arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next().replace(",", ".");
            } else if (arg.equals("--category") && argsIterator.hasNext()) {
                category = Integer.parseInt(argsIterator.next());
            }
        }
        if (description == null || amount == null || !amount.matches("\\d+[,.]\\d{2}") || (category < 0 || category > 6)) {
            System.out.println("Usage: java expense-tracker add --description Lunch --amount 9,99 --category 1");
            System.out.println("Categories:\n" +
                    "0 - Other\n" +
                    "1 - Food\n" +
                    "2 - Transport\n" +
                    "3 - Education\n" +
                    "4 - Entertainment\n" +
                    "5 - Investiment");
            return;
        }
        expenseService.createExpense(description, category, amount);
    }

    public static void handleUpdateExpense(String[] args, ExpenseService expenseService) {
        if (args.length < 7) {
            System.out.println("Usage: java expense-tracker update --id <ID> --description <description> --amount <amount> --category <category>");
            return;
        }
        Integer id = null;
        int category = 0;
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
            } else if (arg.equals("--category") && argsIterator.hasNext()) {
                category = Integer.parseInt(argsIterator.next());
            }
        }
        if (id == null || description == null || amount == null || !amount.matches("\\d+[,.]\\d{2}") || (category < 0 || category > 6)) {
            System.out.println("Usage: java expense-tracker update --id 1 --description Dinner --amount 9,99 --category 1");
            System.out.println("Categories:\n" +
                    "0 - Other\n" +
                    "1 - Food\n" +
                    "2 - Transport\n" +
                    "3 - Education\n" +
                    "4 - Entertainment\n" +
                    "5 - Investiment");
            return;
        }
        expenseService.updateExpense(id, description, category, amount);
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

    public static void handleListAllExpenses(String[] args, ExpenseService expenseService) {
        if (expenseService.listAllExpenses().isEmpty()) {
            System.out.println("Expense list is empty");
            return;
        }
        Iterator<String> argsIterator = getArgsIterator(args);
        Integer category = null;
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--category") && argsIterator.hasNext()) {
                category = Integer.parseInt(argsIterator.next());
            }
        }

        if (category != null) {
            System.out.println("ID   Date         Description     Category        Amount");
            Integer finalCategory = category;
            expenseService.listAllExpenses().stream().filter(e -> e.getCategory().getId() == finalCategory).forEach(System.out::println);
            return;
        }

        System.out.println("ID   Date         Description     Category        Amount");
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

    public static void handleSaveBugdget(String[] args, ExpenseService expenseService) {
        if (args.length < 2) {
            try {
                BigDecimal budget = expenseService.getBudget().getFirst();
                System.out.println(budget);
            }catch (Exception e) {
                System.out.println("No budget has been set yet");
            }
            return;
        }

        if (args.length < 3) {
            System.out.println("Usage: java expense-tracker budget --amount <amount>");
            return;
        }
        Iterator<String> argsIterator = getArgsIterator(args);
        String amount = null;
        while (argsIterator.hasNext()) {
            String arg = argsIterator.next();
            if (arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next().replace(",", ".");
            }
        }
        if (amount == null || !amount.matches("\\d+[,.]\\d{2}")) {
            System.out.println("Usage: java expense-tracker budget --amount 1000,00");
            return;
        }
        expenseService.setBudget(amount);
    }
}
