package service;

import model.Expense;

import java.util.List;

public interface ExpenseRepository {
    public Expense createExpense(String description, String amount);
    public void addExpense(Expense expense);
    public void updateExpense(Integer id, String description, String amount);
    public void deleteExpense(Integer id);
    public List<Expense> listAllExpenses();
}
