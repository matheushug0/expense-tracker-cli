package service.utils;

import model.Expense;
import service.ExpenseService;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExpenseHandlers {
    public static ListIterator<String> getArgsIterator(String[] args) {
        List<String> argList = Arrays.asList(args).subList(1, args.length);
        return argList.listIterator();
    }
    public static void handleAddExpense(String[] args, ExpenseService expenseService) {
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
            }else if(arg.equals("--description") && argsIterator.hasNext()) {
                description = argsIterator.next();
            }else if(arg.equals("--amount") && argsIterator.hasNext()) {
                amount = argsIterator.next().replace(",", ".");
            }
        }

        if(id == null || description == null || amount == null || !amount.matches("\\d+[,.]\\d{2}")){
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
        if(id == null){
            System.out.println("Usage: java expense-tracker delete --id 1");
        }
        expenseService.deleteExpense(id);
    }
    public static void handleListAllExpenses(ExpenseService expenseService) {
        System.out.println("ID   Date         Description     Amount");
        for(Expense e: expenseService.listAllExpenses()){
            System.out.println(e);
        }
    }
}
