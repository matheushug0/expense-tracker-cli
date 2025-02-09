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
- [x] **List Expenses:** Lists all expenses.
- [x] **Total Expense Summary:** Returns the total amount of expenses.
- [x] **Monthly Expense Summary:** Returns the total amount of expenses for a specific month.
- [x] **List Expenses by Category:** Lists expenses registered under a specific category.
  - [x] Create an `enum` and register expense categories.
  - [x] Add the `category` attribute of type `enum` to the `Expense` entity.
  - [x] Update the expense registration logic to allow the user to assign a category to new expenses.
  - [x] Implement a function to filter the list of expenses and print them according to the desired category.
- [x] **Set Monthly Budget:** Allows the user to set a spending limit and receive an alert when the total expenses exceed the limit.
- [ ] **Export Expenses to CSV:** Allows the user to export the list of expenses to a CSV file.

## Installation
1. **Clone the Repository:**
```bash
git clone https://github.com/matheushug0/expense-tracker-cli.git
```
2. **Build the Maven Project (minimum recommended version: 3.6.3):**
```bash
mvn clean package install
```
3. **Run the Application:**
```bash
java target/expense-tracker-cli [command]
```