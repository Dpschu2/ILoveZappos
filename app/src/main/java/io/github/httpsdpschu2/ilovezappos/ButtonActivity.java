package io.github.httpsdpschu2.ilovezappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import io.github.httpsdpschu2.ilovezappos.databinding.BillMainBinding;

/**
 * Created by Dean on 1/28/2017.
 */

public class ButtonActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_main);
        Intent i = getIntent();
        BillMainBinding binding = DataBindingUtil.setContentView(this, R.layout.bill_main);
//toolbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//gridView Bill
        GridView gridBill = (GridView) findViewById(R.id.listBill);
        ButtonAdapter buttonAdapterBill = new ButtonAdapter(ButtonActivity.this, ButtonAdapter.getItemArray3());
        gridBill.setAdapter(buttonAdapterBill);

//Set billing info
        TextView quantity = binding.quantity;
        quantity.setText(ButtonAdapter.getItemCount());
        TextView tax = binding.tax;
        tax.setText(ButtonAdapter.getTax());
        TextView total = binding.total;
        total.setText(ButtonAdapter.getTotal());
    };

    //Toolbar
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
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        ButtonAdapter.startOver();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}