package service;

import model.Category;
import model.Expense;
import service.utils.JsonManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService implements ExpenseRepository {
    private List<Expense> expenses;
    private List<BigDecimal> budget;

    public ExpenseService() {
        this.expenses = JsonManager.loadExpenses();
        this.budget = JsonManager.loadBudget();
    }

    @Override
    public void createExpense(String description, Integer category, String amount) {
        Expense expense = new Expense();
        expense.setId(expenses.size() + 1);
        expense.setDate(LocalDateTime.now());
        expense.setDescription(description);
        if (category == null) {
            expense.setCategory(Category.OTHER);
        } else {
            expense.setCategory(Category.fromId(category));
        }
        expense.setAmount(amount);
        addExpense(expense);
        JsonManager.saveExpenses(expenses);
        System.out.println("Expense added successfully (ID: " + expense.getId() + ")");
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
        updateBudget();
    }

    @Override
    public void updateExpense(Integer id, String description, Integer category, String amount) {
        if (expenses.isEmpty()) {
            System.out.println("Expense List is empty");
            return;
        }
        if (expenses.get(id - 1) != null) {
            Expense expense = expenses.get(id - 1);
            expense.setDescription(description);
            if (category != null && expense.getCategory().getId() != category) {
                expense.setCategory(Category.fromId(category));
            }
            expense.setAmount(amount);
            updateBudget();
            JsonManager.saveExpenses(expenses);
            System.out.println("Expense updated successfully (ID: " + expense.getId() + ")");
        } else {
            System.out.println("Expense not found");
        }
    }

    @Override
    public void deleteExpense(Integer id) {
        if (expenses.isEmpty()) {
            System.out.println("Expense List is empty");
            return;
        }

        if (expenses.get(id - 1) != null) {
            Expense expense = expenses.get(id - 1);
            expenses.remove(expense);
            System.out.println("Expense deleted successfully (ID: " + expense.getId() + ")");
            JsonManager.saveExpenses(expenses);
        } else {
            System.out.println("Expense not found");
        }
    }

    @Override
    public List<Expense> listAllExpenses() {
        return expenses;
    }

    public void setBudget(String budget){
        if(budget == null || budget.isEmpty()){
            this.budget.add(BigDecimal.ZERO);
        }else {
            this.budget.add(BigDecimal.valueOf(Double.parseDouble(budget)));
            System.out.println("Budget set successfully to $" + budget);
        }
        JsonManager.saveBudget(this.budget);
    }

    public void updateBudget(){
        BigDecimal total = expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        try {
            BigDecimal budget = this.budget.getFirst();
            if(budget.compareTo(total) < 0) {
                System.out.println("You have exceeded the monthly budget");
                System.out.println("Monthly Budget: " + budget + "\nTotal Expenses: " + total);
            }
        }catch (Exception e) {
            System.out.println("You didn't set a budget to your expenses");
        }
    }

    public List<BigDecimal> getBudget() {
        return budget;
    }

    public void clearExpenses() {
        expenses.clear();
        budget.clear();
        JsonManager.saveExpenses(expenses);
        JsonManager.saveBudget(budget);
    }
}
