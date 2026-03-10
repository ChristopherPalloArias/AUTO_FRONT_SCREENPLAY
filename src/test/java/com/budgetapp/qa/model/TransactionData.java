package com.budgetapp.qa.model;

public class TransactionData {

    private final String type;
    private final String category;
    private final String description;
    private final String amount;

    public TransactionData(String type, String category, String description, String amount) {
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public static TransactionData income(String description, String amount) {
        return new TransactionData("Ingreso", "Salario", description, amount);
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }
}
