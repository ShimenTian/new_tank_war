package tank;

import tank.Tank;

import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(Tank.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = (String)props.get("initTankCount");
        System.out.println(str);
    }
}