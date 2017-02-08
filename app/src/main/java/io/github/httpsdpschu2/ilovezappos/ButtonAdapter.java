package io.github.httpsdpschu2.ilovezappos;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Dean on 1/31/2017.
 */

public class ButtonAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Item> itemArray2 = new ArrayList<Item>();
    private ArrayList<Item> itemArray = new ArrayList<Item>();
    private static ArrayList<Item> itemArray3 = new ArrayList<Item>();
    private static double subTotal = 0.00;
    public static int itemCount = 0;
    private static final double tax = 0.08;
    private static double total = 0;

    public ButtonAdapter(Context c, ArrayList<Item> itemArray){
        this.mContext = c;
        this.itemArray = itemArray;
    }
    public void removeItems2(){
        itemArray2 = new ArrayList<Item>();
    }
    public static void startOver(){
        itemArray3 = new ArrayList<Item>();
        subTotal = 0.0;
        itemCount = 0;
        total = 0;
    }
    public void addItem2(Item i){
        itemArray2.add(i);
    }
    public void addItem(Item i){
        itemArray3.add(i);
        subTotal += i.getPrice();
        itemCount++;
    }
    public static String getItemCount(){
        return String.format("%d", itemCount);
    }
    public static String getTax(){
        return String.format("$%.2f", (subTotal * tax));
    }
    public static String getTotal(){
        total = subTotal + (subTotal * tax);
        return String.format("$%.2f", total);
    }
    public static String getSubTotal(){
        return String.format("%.2f", subTotal);
    }
    public static ArrayList<Item> getItemArray3(){
        return itemArray3;
    }
    public ArrayList<Item> getItemArray2(){
        return itemArray2;
    }
    @Override
    public int getCount(){
        return itemArray.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

//Displays buttons
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView button = new TextView(mContext);
        button.setTypeface(Typeface.MONOSPACE);
        button.setText(String.format("%-20s", itemArray.get(position).getName()) + "$" + String.format("%.2f", itemArray.get(position).getPrice()));
        button.setTextSize(20);
        button.setPadding(0, 25, 0, 25);
        return button;
    }

}
