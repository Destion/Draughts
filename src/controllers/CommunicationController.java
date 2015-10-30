package controllers;

import com.pi4j.wiringpi.Gpio;

import java.util.ArrayList;

/**
 * Created by destion on 30-10-15.
 */
public class CommunicationController {



    public void sendBytes(ArrayList<Integer> bytes){
        for (int i=0; i<10; i++){
            for (int j=0; j<15; j++){
                int temp = bytes.get(0);

                int bit1 = temp / 100;
                int bit2 = temp % 100 / 10;
                int bit3 = temp % 10;

            }
        }
    }
}
