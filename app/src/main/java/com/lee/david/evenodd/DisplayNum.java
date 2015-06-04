package com.lee.david.evenodd;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by David on 6/4/2015.
 */
public class DisplayNum implements Runnable {

    TextView even;
    TextView odd;
    FileInputStream openEven = null;
    FileInputStream openOdd = null;

    DisplayNum(TextView evT, FileInputStream evF, TextView odT, FileInputStream odF) {
        even = evT;
        odd = odT;
        openEven = evF;
        openOdd = odF;
    }


    @Override
    public void run() {

        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        // Declarations



        even.setText("Starting" + "\n");
        odd.setText("Starting" + "\n");

        try {

            BufferedReader oddRead = new BufferedReader(new InputStreamReader(openOdd));
            BufferedReader evenRead = new BufferedReader(new InputStreamReader(openEven));

            String lineOdd = oddRead.readLine();
            String lineEven = evenRead.readLine();

            while (lineOdd != null || lineEven != null) {

                even.append((lineEven + "\n"));
                odd.append(lineOdd + "\n");
                lineEven = evenRead.readLine();
                lineOdd = oddRead.readLine();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        // Closes the files once done.
        finally {
            try {
                openEven.close();
                openOdd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

