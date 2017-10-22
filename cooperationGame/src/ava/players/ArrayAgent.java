package ava.players;

/**
 * ArrayAgent - класс, инкапсулирующий массив агентов и предоставляющий работу с ним.
 */
public class ArrayAgent {
    private Agent[] agents;
    private int countActiveAgents; // количество не выбывших с игры агентов.
    private int initialNumberOfCoins; // начальное количество монет у каждого из агентов.

    public ArrayAgent() {
    }

    public ArrayAgent(Agent[] agents) {
        this.agents = agents;
        this.countActiveAgents = agents.length; // В начале игры все агенты активны.
        this.initialNumberOfCoins = agents[0].coins; // у всех агентов количество монет одинаково, поэтому такая запись валидна.
    }

    /**
     * Метод setAgents устанавливает агентов текущиму объекту класса.
     *
     * @param agents - массив агентов.
     */
    public void setAgents(Agent[] agents) {
        this.agents = agents;
        this.countActiveAgents = agents.length;
        this.initialNumberOfCoins = agents[0].coins; // у всех агентов количество монет одинаково, поэтому такая запись валидна.
    }

    /**
     * Метод getCountActiveAgents возвращает количество не выбывших с игры агентов.
     */
    public int getCountActiveAgents() {
        return countActiveAgents;
    }

    /**
     * Метод getInitialNumberOfCoins возвращает начальное количество монет, которое было у агентов.
     */
    public int getInitialNumberOfCoins() {
        return this.initialNumberOfCoins;
    }

    /**
     * Метод getArrayLength возвращает длинну массива, в котором находятся агенты.
     */
    public int getArrayLength() {
        if (agents != null) {
            return agents.length;
        }
        return 0;
    }

    /**
     * Метод getAgent возвращает агента по индексу.
     *
     * @param index - индекс агента в массиве.
     * @return возвращается агент.
     */
    public Agent getAgent(int index) {
        return agents[index];
    }

    /**
     * Метод deleteAgent удаляет агента по индексу и уменьшает счетчикколичества активных игроков.
     *
     * @param index - индекс агента в массиве.
     */
    public void deleteAgent(int index) {
        this.agents[index] = null;
        --this.countActiveAgents;
    }

    /**
     * Метод print  печатает массив агентов на консоль.
     */
    public String print() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < agents.length; i++) {
            if (agents[i] != null) {
                s.append(agents[i].toString()+"\n");
                System.out.println(agents[i]);
            }
        }
        return s.toString();
    }
}
