# <img src="https://roadmap.sh/images/gifs/rocket.gif" width="25px"> Expense Tracker CLI

A command-line interface (CLI) application for managing personal expenses.  
<br>  
Project suggested by [Roadmap.sh - **Backend Developer**](https://roadmap.sh/projects/expense-tracker).

## Features
- [x] **Add Expense:** Adds a new expense with a description and amount.
  - [x] Registering the new expense and displaying a return message in the terminal.
  - [x] Storing and reading the `Collection` in a JSON file.
  - [x] Error and exception handling.
  - [x] Adding character restrictions to the `amount` input.
- [x] **Update Expense:** Updates the description and amount of an expense based on its ID.
- [x] **Delete Expense:** Removes an expense by its ID.
- [x] **Clear Expenses:** Clear all expenses from the list.
- [x] **List Expenses:** Lists all expenses.
- [x] **Total Expense Summary:** Returns the total amount of expenses.
- [x] **Monthly Expense Summary:** Returns the total amount of expenses for a specific month.
- [x] **List Expenses by Category:** Lists expenses registered under a specific category.
  - [x] Create an `enum` and register expense categories.
  - [x] Add the `category` attribute of type `enum` to the `Expense` entity.
  - [x] Update the expense registration logic to allow the user to assign a category to new expenses.
  - [x] Implement a function to filter the list of expenses and print them according to the desired category.
- [x] **Set Monthly Budget:** Allows the user to set a spending limit and receive an alert when the total expenses exceed the limit.
- [x] **Export Expenses to CSV:** Allows the user to export the list of expenses to a CSV file.
---
## Installation
1. **Clone the Repository:**
```bash
git clone https://github.com/matheushug0/expense-tracker-cli.git
```
2. **Build the Maven Project (minimum recommended version: 3.6.3):**
```bash
cd expense-tracker-cli/
mvn clean package install
```
3. **Run the Application:**
```bash
cd target/
java expense-tracker-cli-1.0 [command]
```
---
## Usage
```bash
- Add new expense
java expense-tracker-cli-1.0.jar add --description Dinner --amount 19,99 --category 0
# You didn't set a budget to your expenses
# Expense added successfully (ID: 1)

- Update expense
java expense-tracker-cli-1.0.jar update --id 1 --description Dinner --amount 19,99 --category 1
# You didn't set a budget to your expenses
# Expense updated successfully (ID: 1)

- Check budget
java expense-tracker-cli-1.0.jar budget
# No budget has been set yet

- Set a budget
java expense-tracker-cli-1.0.jar budget --amount 1000,00
# Budget set successfully to $1000.00

- List all expenses
java expense-tracker-cli-1.0.jar list
# ID   Date         Description     Category        Amount
# 1    09-02-2025   Dinner          Food            $29,99

- List expenses by month
java expense-tracker-cli-1.0.jar list --month 2
# ID   Date         Description     Category        Amount
# 1    09-02-2025   Dinner          Food            $29,99

- List expenses by category
java expense-tracker-cli-1.0.jar list --category 1
# ID   Date         Description     Category        Amount
# 1    09-02-2025   Dinner          Food            $29,99

- Summary
java expense-tracker-cli-1.0.jar summary
# Total expenses: $29.99

- Summary by month
java expense-tracker-cli-1.0.jar summary --month 2
# Total expenses for February: $29.99

- Export to CSV
java expense-tracker-cli-1.0.jar csv
# CSV File exported with success to: expenses.csv

- Delete Expense
java expense-tracker-cli-1.0.jar delete --id 1
# Expense deleted successfully (ID: 1)

- Clear Expenses
java expense-tracker-cli-1.0.jar clear
# Are you sure? [Y/n]: y
# Expense list has been cleared
```
---
## Project Structure
Below is the organization of the project's directories and files:

```
github-activity
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── model/
│   │           └── Expense.java
│   │           └── Category.java
│   │       └── service/
│   │           └── utils/
│   │               └── ExpenseHandlers.java
│   │               └── JsonManager.java
│   │               └── LocalDateTimeAdapter.java
│   │           └── ExpenseRepository.java
│   │           └── ExpenseService.java
│   │       └── view/
│   │           └── ExpenseTracker.java
├── target/   # Maven build output (ignored by .gitignore)
├── pom.xml   # Maven configuration
└── README.md # This file
```
---
## Technologies Used
- **Gson**
---
## Contact

- [**LinkedIn**](https://www.linkedin.com/in/matheus-hugo/)
---
## References
- [Gson](https://github.com/google/gson)
