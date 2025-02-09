package service.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Expense;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private static final String EXPENSE_JSON = "expenses.json";
    private static final String BUDGET_JSON = "budget.json";
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

    public static void saveExpenses(List<Expense> expenses) {
        try (FileWriter fileWriter = new FileWriter(EXPENSE_JSON)) {
            GSON.toJson(expenses, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveBudget(List<BigDecimal> budget) {
        try(FileWriter fileWriter = new FileWriter(BUDGET_JSON)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(budget, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Expense> loadExpenses() {
        File file = new File(EXPENSE_JSON);
        if (!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fileReader = new FileReader(EXPENSE_JSON);
            Type listType = new TypeToken<List<Expense>>() {}.getType();
            List<Expense> expenses = GSON.fromJson(fileReader, listType);
            return (expenses != null) ? expenses : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<BigDecimal> loadBudget(){
        File file = new File(BUDGET_JSON);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(BUDGET_JSON);
            Type listType = new TypeToken<List<BigDecimal>>() {}.getType();
            List<BigDecimal> budget = gson.fromJson(fileReader, listType);
            return (budget != null) ? budget : new ArrayList<>();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
