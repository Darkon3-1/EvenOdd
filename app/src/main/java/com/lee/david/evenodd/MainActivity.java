package com.lee.david.evenodd;

import android.content.Context;
import android.os.*;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Method to create the two files
    public void createFile(View view) {

        String num = null;
        FileOutputStream evenFile = null;
        FileOutputStream oddFile = null;

        //Creates two files, Even.txt and Odd.txt
        try {
            evenFile = openFileOutput("Even.txt", Context.MODE_PRIVATE);
            oddFile = openFileOutput("Odd.txt", Context.MODE_PRIVATE);

            // Fills the file with odd and even numbers
            for(int i = 1; i < 21;i++) {

                if ((i%2)==0) {
                    num = num.valueOf(i)+"\n";
                    evenFile.write(num.getBytes());
                }
                else {
                    num = num.valueOf(i) + "\n";
                    oddFile.write(num.getBytes());
                }
            }
            // Lets the user know lists were created.
            TextView completeEven = (TextView) findViewById(R.id.textViewEven);
            TextView completeOdd = (TextView) findViewById(R.id.textViewOdd);
            completeEven.setText("Created list of even numbers");
            completeOdd.setText("Created list of odd numbers");

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Closes the files once done.
        finally {
            try {
                evenFile.close();
                oddFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to load the file
    public void loadFile (View v) {

        TextView even = (TextView) findViewById(R.id.textViewEven);
        TextView odd = (TextView) findViewById(R.id.textViewOdd);

        FileInputStream openEven = null;
        FileInputStream openOdd = null;

        try {
            openEven = openFileInput("Even.txt");
            openOdd = openFileInput("Odd.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DisplayNum dis = new DisplayNum(even, openEven, odd, openOdd);
        Thread test = new Thread(dis);
        test.start();

    }

    // Stops the files from being loaded
    public void stopLoad(View v) {

        Thread.interrupted();
        TextView test = (TextView) findViewById(R.id.textViewEven);


    }
}
