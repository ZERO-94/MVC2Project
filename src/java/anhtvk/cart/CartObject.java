/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.cart;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kiman
 */
public class CartObject implements Serializable {
    private Map<String, CartItem> items;
    private int totalPrice;

    public Map<String, CartItem> getItems() {
        return items;
    }
    
    public void addItemToCart(String id, String name, int price) {
        //1. Check available item
        if(this.items == null) {
            this.items = new HashMap<>();
        }
        
        //2. If items are available, check if item is available
        CartItem newItem = new CartItem(id, name, 1, price);
        
        if(this.items.containsKey(id)) {
            newItem.setAmount(items.get(id).getAmount() + 1);
        }
        
        this.items.put(id, newItem);
        calculateTotalPrice();
    }
    
    public boolean removeItemFromCart(String id) {
        
        //1. check available items
        if(this.items == null) {
            return false;
        }
        
        //2. Check if item is in that cart
        if(this.items.containsKey(id)) {
            this.items.remove(id);
            
            //If there is no item in cart -> throw cart away
            if(this.items.isEmpty()) {
                this.items = null;
            }
        } else {
            return false;
        }
        calculateTotalPrice();
        return true;
    }
    
    public void calculateTotalPrice() {
        
        int total = 0;
        
        Collection<CartItem> values = (Collection<CartItem>) items.values();
        for(CartItem item : values) {
            total += item.getAmount() * item.getPrice();
        }
        
        totalPrice = total;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
