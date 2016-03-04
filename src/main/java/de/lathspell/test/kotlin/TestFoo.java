package de.lathspell.test.kotlin;

import java.util.Random;


enum SEASONS {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

public class TestFoo {
    void foo() {
        "".replaceFirst("","")        ;
        "1234".charAt(3);
        SEASONS s = Enum.valueOf(SEASONS.class, "SPRING");
        int i = new Random().nextInt(3);
    }
}
