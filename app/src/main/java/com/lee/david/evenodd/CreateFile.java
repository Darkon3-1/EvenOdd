package com.lee.david.evenodd;

import android.content.Context;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by David on 6/4/2015.
 */
public class CreateFile implements Runnable {

    String num = null;
    FileOutputStream evenFile = null;
    FileOutputStream oddFile = null;
    TextView completeEven;
    TextView completeOdd;

    CreateFile(FileOutputStream ev, FileOutputStream od, TextView evv, TextView odv) {
        this.evenFile = ev;
        this.oddFile = od;
        this.completeEven = evv;
        this.completeOdd = odv;

    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        //Creates two files, Even.txt and Odd.txt
        try {


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
}
