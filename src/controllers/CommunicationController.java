package controllers;

import com.pi4j.io.*;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

import java.util.ArrayList;

/**
 * Created by destion on 30-10-15.
 */
public class CommunicationController {

    GpioController gpio;
    ArrayList<GpioPinDigitalMultipurpose> pins;

    public CommunicationController(){
        this.gpio = GpioFactory.getInstance();
        GpioPinDigitalMultipurpose pin0 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_00, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin1 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_01, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin2 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_02, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin3 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_03, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin4 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_04, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin5 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_05, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin6 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_06, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin7 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_07, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin8 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_08, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin9 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_09, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin10 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_10, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin11 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_11, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin12 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_12, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin13 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_13, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin14 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_14, PinMode.DIGITAL_OUTPUT);
        GpioPinDigitalMultipurpose pin15 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_15, PinMode.DIGITAL_OUTPUT);
        pins = new ArrayList<>();
        pins.add(pin0);
        pins.add(pin1);
        pins.add(pin2);
        pins.add(pin3);
        pins.add(pin4);
        pins.add(pin5);
        pins.add(pin6);
        pins.add(pin7);
        pins.add(pin8);
        pins.add(pin9);
        pins.add(pin10);
        pins.add(pin11);
        pins.add(pin12);
        pins.add(pin13);
        pins.add(pin14);
        pins.add(pin15);
    }

    public void sendBytes(ArrayList<Integer> bytes){

        for (GpioPinDigitalMultipurpose pin : pins){
            pin.setMode(PinMode.DIGITAL_OUTPUT);
        }

        for (int i=0; i<10; i++){
            for (int j=15; j>0; j-=3){
                int temp = bytes.get(0);

                int bit1 = temp / 100;
                int bit2 = (temp % 100) / 10;
                int bit3 = temp % 10;

                if (bit1 == 1){
                    gpio.high(pins.get(j));
                } else {
                    gpio.low(pins.get(j));
                }
                if (bit2 == 1){
                    gpio.high(pins.get(j+1));
                } else {
                    gpio.low(pins.get(j+1));
                }
                if (bit3 == 1){
                    gpio.high(pins.get(j+2));
                } else {
                    gpio.low(pins.get(j+2));
                }
            }
            gpio.high(pins.get(15));
            gpio.low(pins.get(15));
        }
    }
}
