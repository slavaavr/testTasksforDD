package ava.players;

/**
 * GreedyAgent - класс, представляющий жадного агента.
 */
public class GreedyAgent extends Agent {

    public GreedyAgent(int id, int coins) {
        super(id, Strategy.GREEDY, coins);
    }

    /**
     * Метод getAns представляет ставку агента.
     *
     * @return всегда возвращается констнта BET_FOR_0_COIN, т.к. агент жадный.
     */
    @Override
    public int getAns() {
        return BET_FOR_0_COIN;
    }
}
