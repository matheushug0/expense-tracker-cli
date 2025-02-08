package service;

import model.Expense;
import service.utils.JsonManager;

import java.time.LocalDateTime;
import java.util.List;

public class ExpenseService implements ExpenseRepository{
    private List<Expense> expenses;

    public ExpenseService() {
        this.expenses = JsonManager.loadExpenses();
    }

    @Override
    public Expense createExpense(String description, String amount) {
        Expense expense = new Expense();
        expense.setId(expenses.size() + 1);
        expense.setDate(LocalDateTime.now());
        expense.setDescription(description);
        expense.setAmount(amount);
        addExpense(expense);
        JsonManager.saveExpenses(expenses);
        System.out.println("Expense added successfully (ID: " + expense.getId() + ")");
        return expense;
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public void updateExpense(Integer id, String description, String amount) {
        Expense expense = expenses.get(id - 1);
        if(expense != null) {
            expense.setDescription(description);
            expense.setAmount(amount);
            JsonManager.saveExpenses(expenses);
            System.out.println("Expense updated successfully (ID: " + expense.getId() + ")");
        }else {
            System.out.println("Expense not found");
        }
    }

    @Override
    public void deleteExpense(Integer id) {
        if(expenses.isEmpty()){
            System.out.println("Expense List is empty");
            return;
        }

        if(expenses.get(id - 1) != null) {
            Expense expense = expenses.get(id - 1);
            expenses.remove(expense);
            System.out.println("Expense deleted successfully (ID: " + expense.getId() + ")");
            JsonManager.saveExpenses(expenses);
        }else {
            System.out.println("Expense not found");
        }
    }

    @Override
    public List<Expense> listAllExpenses() {
        return expenses;
    }
}
