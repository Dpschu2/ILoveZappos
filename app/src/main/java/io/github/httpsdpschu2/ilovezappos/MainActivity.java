package io.github.httpsdpschu2.ilovezappos;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.databinding.DataBindingUtil;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import java.util.ArrayList;
import io.github.httpsdpschu2.ilovezappos.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity  {
    private Context mContext = this;
    private ArrayList<Item> itemArray = new ArrayList<Item>();
    private ArrayList<Item> itemArray2 = new ArrayList<Item>();
    private ArrayList<Item> itemArray3 = new ArrayList<Item>();
    private ButtonAdapter buttonAdapter;
    private EditText edit;
    private int count = 0;
    private String[] names = {"Adidas Kampung", "Ballet shoe", "Pointe shoe", "Bast shoe", "Blucher shoe", "Boat shoe",
            "Brogan (shoes)", "Brogue shoe", "Brothel creeper", "Bucks", "Calceology", "Cantabrian albarcas", "Chopine",
            "Climbing shoe", "Clog", "Court shoe", "Cross country shoe", "Derby shoe", "Diabetic shoe", "Dori shoes",
            "Dress shoe", "Driving moccasins", "Earth shoe", "Elevator shoes", "Espadrille", "Fashion boot", "Galesh", "Giveh",
            "High-heeled shoe", "Skate shoe"};

    private double[] prices = new double[30];
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentMainBinding binding = DataBindingUtil.setContentView(this, R.layout.content_main);
        setContentView(R.layout.activity_main);

        ImageButton search = binding.search;
        final TextView totalCharge = (TextView) findViewById(R.id.money);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit = (EditText) findViewById(R.id.editText);

        setSupportActionBar(toolbar);

//displays icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//initializing prices to random prices
        for (int i = 0; i < names.length; i++) {
            prices[i] = (double) random.nextInt(120) + 30;
        }
        //Initializing items
        for (int i = 0; i < names.length; i++) {
            itemArray.add(new Item(names[i], prices[i], false));
        }
//gridView
        GridView gridView = (GridView) findViewById(R.id.grid);
        buttonAdapter = new ButtonAdapter(this, itemArray);
        gridView.setAdapter(buttonAdapter);
//Button click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "Added " + itemArray.get(position).getName().toString() + " to cart",
                        Toast.LENGTH_SHORT).show();
                //Actions
                itemArray.get(position).setUsed(true);
                buttonAdapter.addItem(itemArray.get(position));
                itemArray2 = buttonAdapter.getItemArray2();
                itemArray3 = buttonAdapter.getItemArray3();
                count++;
                totalCharge.setText(buttonAdapter.getSubTotal());

                //gridView bottom sheet, itemArray3
                GridView gridB = (GridView) findViewById(R.id.gridBottom);
                ButtonAdapter buttonAdapterBottom = new ButtonAdapter(MainActivity.this, itemArray3);
                gridB.setAdapter(buttonAdapterBottom);

            }
        });


//FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


//Searches for item
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonAdapter.removeItems2();
                for (int i = 0; i < itemArray.size(); i++) {
                    if (itemArray.get(i).getName().toLowerCase().contains(edit.getText().toString().toLowerCase())) {
                        buttonAdapter.addItem2(itemArray.get(i));
                    }
                }
                GridView gridView = (GridView) findViewById(R.id.grid);
                ButtonAdapter buttonAdapterNew = new ButtonAdapter(MainActivity.this, buttonAdapter.getItemArray2());
                gridView.setAdapter(buttonAdapterNew);
                performSearch();
            }
        });


//keyboard manipulation
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    buttonAdapter.removeItems2();
                    for (int i = 0; i < itemArray.size(); i++) {
                        if (itemArray.get(i).getName().toLowerCase().contains(edit.getText().toString().toLowerCase())) {
                            buttonAdapter.addItem2(itemArray.get(i));
                        }
                    }
                    GridView gridViewOnKey = (GridView) findViewById(R.id.grid);
                    ButtonAdapter buttonAdapterOnKey = new ButtonAdapter(MainActivity.this, buttonAdapter.getItemArray2());
                    gridViewOnKey.setAdapter(buttonAdapterOnKey);
                    performSearch();

                }
                return true;
            }
        });

//open billing page
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ButtonActivity.class);
                mContext.startActivity(i);
            }
        });
//filters results on keypress
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonAdapter.removeItems2();
                for (int i = 0; i < itemArray.size(); i++) {
                    if (itemArray.get(i).getName().toLowerCase().contains(edit.getText().toString().toLowerCase())) {
                        buttonAdapter.addItem2(itemArray.get(i));
                    }
                }
                GridView gridViewOnKey = (GridView) findViewById(R.id.grid);
                ButtonAdapter buttonAdapterOnKey = new ButtonAdapter(MainActivity.this, buttonAdapter.getItemArray2());
                gridViewOnKey.setAdapter(buttonAdapterOnKey);

            }
        });

    }
//Hides keyboard
    private void performSearch() {
        edit.clearFocus();
        InputMethodManager in = (InputMethodManager)MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }

//toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}



