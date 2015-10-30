package controllers;

import com.pi4j.io.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

import java.util.ArrayList;

/**
 * Created by destion on 30-10-15.
 */
public class CommunicationController {

    GpioPinDigitalOutput pin1;
    GpioController gpio;


    public void sendBytes(ArrayList<Integer> bytes){

        for (int i=0; i<10; i++){
            for (int j=0; j<15; j+=3){
                int temp = bytes.get(0);

                int bit1 = temp / 100;
                int bit2 = temp % 100 / 10;
                int bit3 = temp % 10;

                Gpio.digitalWrite(j, bit1);
                Gpio.digitalWrite(j+1, bit2);
                Gpio.digitalWrite(j+2, bit3);
            }
            //TODO test if this isn't too fast for the fpga
            Gpio.digitalWrite(15, 1);
            Gpio.digitalWrite(15, 0);
        }
    }

    public void initOut(){
        gpio = GpioFactory.getInstance();
        //pin1 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_01)
    }
}
