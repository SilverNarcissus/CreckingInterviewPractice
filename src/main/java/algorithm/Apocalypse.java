package algorithm;

import java.util.Random;

public class Apocalypse {

    public static void main(String[] args) {
        Apocalypse apocalypse = new Apocalypse();
        double boy = 0;
        double girl = 100000000;
        for (int i = 0; i < girl; i++) {
            boy += apocalypse.birth();
        }

        System.out.println(boy / girl);
    }

    public int birth() {
        Random random = new Random();
        int boy_count = 0;
        while (random.nextBoolean()) {
            boy_count++;
        }

        return boy_count;
    }
}
