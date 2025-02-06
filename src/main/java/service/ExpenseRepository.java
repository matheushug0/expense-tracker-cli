package service;

import model.Expense;

import java.util.List;

public interface ExpenseRepository {
    public Expense createExpense(String description, String amount);
    public void addExpense(Expense expense);
    public void updateExpense(Expense expense);
    public void deleteExpense(Expense expense);
    public List<Expense> listAllExpenses();
}
