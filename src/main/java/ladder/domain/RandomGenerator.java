package ladder.domain;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator implements Generator {

    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public boolean generate() {
        int generated = threadLocalRandom.nextInt(10);
        return generated > 4;
    }
}
