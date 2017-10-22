package ava.players;

import java.util.Random;

/**
 * RandomAgent - класс, представляющий наивного агента.
 */
public class RandomAgent extends Agent {
    private static Random rand;

    static {
        rand = new Random();
    }

    public RandomAgent(int id, int coins) {
        super(id, Strategy.RANDOM, coins);
    }

    /**
     * Метод getAns представляет ставку агента.
     *
     * @return если у рандомного агента есть монеты, то он возвращает константу BET_FOR_0_COIN или BET_FOR_1_COIN,
     * когда же монет 0, тогда он начинает возвращать константу BET_FOR_0_COIN.
     */
    @Override
    public int getAns() {
        if (this.coins > 0) {
            return rand.nextInt(2);
        }
        return BET_FOR_0_COIN;
    }
}
