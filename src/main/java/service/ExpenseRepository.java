package service;

import model.Expense;

import java.util.List;

public interface ExpenseRepository {
    public void createExpense(String description, Integer category, String amount);
    public void addExpense(Expense expense);
    public void updateExpense(Integer id, String description,Integer category, String amount);
    public void deleteExpense(Integer id);
    public List<Expense> listAllExpenses();
}
