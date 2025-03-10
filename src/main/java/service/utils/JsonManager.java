package service.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Expense;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private static final String EXPENSE_JSON = "expenses.json";
    private static final String BUDGET_JSON = "budget.json";
    private static final String EXPENSES_CSV = "expenses.csv";
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

    public static void exportToCsv(List<Expense> expenses) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if(expenses.isEmpty()) {
            System.out.println("Expenses list is empty");
            return;
        }

        try(FileWriter fileWriter = new FileWriter(EXPENSES_CSV)){
            fileWriter.append("ID,Date,Description,Category,Amount\n");
            BigDecimal total = BigDecimal.ZERO;
            for(Expense e: expenses){
                total = total.add(e.getAmount());
                fileWriter.append(String.valueOf(e.getId())).append(",")
                        .append(String.valueOf(e.getDate().format(formatter))).append(",")
                        .append(e.getDescription()).append(",")
                        .append(e.getCategory().getName()).append(",")
                        .append(String.valueOf(e.getAmount()))
                        .append("\n");
            }
            fileWriter.append(",").append(",").append(",").append("TOTAL").append(",").append(total.toString());
            System.out.println("CSV File exported with success to: " + EXPENSES_CSV);
        } catch (IOException e) {
            System.out.println("Error while exporting expenses to CSV: " + e.getMessage());
        }
    }
}
