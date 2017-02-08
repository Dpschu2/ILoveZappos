package io.github.httpsdpschu2.ilovezappos;

import android.os.Bundle;

/**
 * Created by Dean on 1/30/2017.
 */

public class Item extends MainActivity{
    private String name;
    private double price = Double.NaN;
    private boolean used = false;
    public Item(String name, double price, boolean used){
        this.name = name;
        this.price = price;
        this.used = used;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setUsed(boolean bool){
        this.used = bool;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public boolean getUsed(){
        return used;
    }
}
