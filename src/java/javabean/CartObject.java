/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabean;

/**
 *
 * @author Kamtso
 */
public class CartObject {

    private Book book;
    private int quantity;
    private int cashPay;
    private int LPPay = 0;

    public CartObject(Book _book, int _quan) {
        book = _book;
        quantity = _quan;
        cashPay = _book.getPrice() * _quan;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int _quan) {
        quantity += _quan;
        int amount = cashPay - book.getPrice() * _quan;
        if (amount < 0) {
            cashPay = 0;
            LPPay -= amount;
        } else {
            cashPay += book.getPrice() * _quan;
        }
    }

    public void setQuantity(int _quan) {
        quantity = _quan;
        cashPay = book.getPrice() * _quan;
        LPPay=0;
    }

    public void setCashPay(int money) {
        LPPay -= money - cashPay;
        cashPay = money;
    }

    public int getCashPay() {
        return cashPay;
    }

    public void setLPPay(int LP) {
        cashPay -= LP - LPPay;
        LPPay = LP;
    }

    public int getLPPay() {
        return LPPay;
    }
}
