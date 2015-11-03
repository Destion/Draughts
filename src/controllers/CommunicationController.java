package controllers;


import com.pi4j.io.gpio.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CommunicationController {

    GpioController gpio;
    ArrayList<GpioPinDigitalMultipurpose> pins;

    public CommunicationController() {
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
        GpioPinDigitalMultipurpose pin16 = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_16, PinMode.DIGITAL_OUTPUT);
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
        pins.add(pin16);
    }

    public int sendBytes(ArrayList<Integer> bytes) {

        long time = System.currentTimeMillis();
        System.out.println(time);

        for (GpioPinDigitalMultipurpose pin : pins) {
            pin.setMode(PinMode.DIGITAL_OUTPUT);
            gpio.low(pin);
        }

        String temp2 = "";

        for (Integer x : bytes){
            if (x == 0){
                temp2 += "000";
            } else {
                temp2 += x;
            }
        }

        System.out.println(temp2);
        int count = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 14; j >= 0; j -= 3) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int temp = bytes.get(0);
                bytes.remove(0);

                int bit1 = temp / 100;
                int bit2 = (temp % 100) / 10;
                int bit3 = temp % 10;

                if (temp == 0){
                    bit1 = 0;
                    bit2 = 0;
                    bit3 = 0;
                }


                if (bit1 == 1) {
                    gpio.high(pins.get(j));
                } else {
                    gpio.low(pins.get(j));
                }
                if (bit2 == 1) {
                    gpio.high(pins.get(j - 1));
                } else {
                    gpio.low(pins.get(j - 1));
                }
                if (bit3 == 1) {
                    gpio.high(pins.get(j - 2));
                } else {
                    gpio.low(pins.get(j - 2));
                }
                count++;
                System.out.println(bit1 + " " + bit2 + " " + bit3);
                System.out.println(count);
            }
            gpio.high(pins.get(15));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gpio.low(pins.get(15));
        }

        //TODO verwerk input
        gpio.high(pins.get(16));
        System.out.println(System.currentTimeMillis() - time);
        return this.getInput();
    }

    public int getInput() {

        for (int x=0; x<=15; x++){
            pins.get(x).setMode(PinMode.DIGITAL_INPUT);
            pins.get(x).setPullResistance(PinPullResistance.PULL_DOWN);
            System.out.println("Pin nummer: " + x + "met modus" + pins.get(x).getMode() + " met waarde: " + pins.get(x).isHigh());
        }

        String temp = "";

        for (int i=0; i<15; i++){
            if (pins.get(i).isHigh()){
                temp += "1";
            } else {
                temp += "0";
            }
        }

        System.out.println(temp);

        return (Integer.parseInt(temp));
    }
}

