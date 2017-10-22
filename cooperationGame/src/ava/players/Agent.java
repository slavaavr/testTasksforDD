package ava.players;

/**
 * Agent - абстрактный класс, представляющий агента.
 * Класс содержит поля, которые характеризуют агента:
 * id - идентификатор агента.
 * strategy - стратегия поведения.
 * coins - количество монет, находящихся в пользовании у агента.
 * BET_FOR_1_COIN - константа, представляющая ставку на 1 манету.
 * BET_FOR_0_COIN - константа, представляющая ставку на 0 манет.
 */
public abstract class Agent {
    protected int id;
    protected Strategy strategy;
    protected int coins;
    public static final int BET_FOR_1_COIN = 1;
    public static final int BET_FOR_0_COIN = 0;


    public Agent(int id, Strategy strategy, int coins) {
        this.id = id;
        this.strategy = strategy;
        this.coins = coins;
    }

    /**
     * Абстрактный метод getAns, представляющий ставку агента.
     *
     * @return констанда BET_FOR_1_COIN или BET_FOR_0_COIN.
     */
    public abstract int getAns();

    /**
     * Метод decrementCoins уменьшает количество монет на заданное число.
     *
     * @param inc - число, на сколько нужно уменьшить монет.
     * @return возвращет количество оставшихся монет.
     */
    public int decrementCoins(int inc) {
        return this.coins -= inc;
    }

    /**
     * Метод incrementCoins увеличивает количество монет на заданное число.
     *
     * @param inc - число, на сколько нужно увеличить монет.
     */
    public void incrementCoins(int inc) {
        this.coins += inc;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", strategy=" + strategy +
                ", coins=" + coins +
                '}';
    }
}
