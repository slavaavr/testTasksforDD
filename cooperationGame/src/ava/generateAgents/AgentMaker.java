package ava.generateAgents;

import ava.players.Strategy;
import ava.players.Agent;
import ava.players.GreedyAgent;
import ava.players.NaiveAgent;
import ava.players.RandomAgent;


/**
 * AgentMaker - класс, предназначенный для выдачи соответствующего агента, в зависимости от его стратегии поведения в игре.
 */
public class AgentMaker implements IAgentMaker {

    @Override
    public Agent getAgent(int id, Strategy strategy, int coins) {
        switch (strategy) {
            case NAIVE:
                return new NaiveAgent(id, coins);
            case GREEDY:
                return new GreedyAgent(id, coins);
            default:
                return new RandomAgent(id, coins);
        }
    }
}
