package tv.twitch;

import java.awt.*;

public class MousePoint {
    //doğru kordinat için driveri fullscreen yap ve f11 yapıp burayı çalıştır.
    public static void main(String[] args) throws InterruptedException {

        while(true){
            Thread.sleep(1000);
            System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x +
                    ", " +
                    MouseInfo.getPointerInfo().getLocation().y + ")");
        }
    }
}