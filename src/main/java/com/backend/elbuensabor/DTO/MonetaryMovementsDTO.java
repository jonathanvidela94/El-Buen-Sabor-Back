package com.backend.elbuensabor.DTO;

public class MonetaryMovementsDTO {
    private Double income;
    private Double expenses;
    private Double profits;

    public MonetaryMovementsDTO(Double income, Double expenses, Double profits) {
        this.income = income;
        this.expenses = expenses;
        this.profits = profits;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }

}
