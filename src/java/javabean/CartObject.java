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
    
    public CartObject(Book _book,int _quan){
        book = _book;
        quantity = _quan;
    }
    
    public Book getBook(){
        return book;
    }
    
    public int getQuantity(){
        return quantity;
    }
}
