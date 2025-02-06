package service;

import model.Expense;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService implements ExpenseRepository{
    private List<Expense> expenses;

    public ExpenseService() {
        this.expenses = new ArrayList<>();
    }

    @Override
    public Expense createExpense(String description, String amount) {
        Expense expense = new Expense();
        expense.setId(expenses.size() + 1);
        expense.setDate(LocalDateTime.now());
        expense.setDescription(description);
        expense.setAmount(amount);
        addExpense(expense);
        return expense;
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public void updateExpense(Expense expense) {

    }

    @Override
    public void deleteExpense(Expense expense) {

    }

    @Override
    public List<Expense> listAllExpenses() {
        return expenses;
    }
}
