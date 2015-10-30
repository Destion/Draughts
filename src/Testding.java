import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

/**
 * Created by destion on 29-10-15.
 */
public class Testding   {
    public static void main(String[] args) {
        GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalMultipurpose pin = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_01, PinMode.DIGITAL_OUTPUT);
        gpio.high(pin);
    }
}
