package ava.players;

/**
 * NaiveAgent - класс, представляющий наивного агента.
 */
public class NaiveAgent extends Agent {

    public NaiveAgent(int id, int coins) {
        super(id, Strategy.NAIVE, coins);
    }

    /**
     * Метод getAns представляет ставку агента.
     *
     * @return если у наивного агента есть монеты, то он всегда возвращает константу BET_FOR_1_COIN,
     * когда же монет 0, тогда он начинает возвращать константу BET_FOR_0_COIN.
     */
    @Override
    public int getAns() {
        if (this.coins > 0) {
            return BET_FOR_1_COIN;
        }
        return BET_FOR_0_COIN;
    }
}
