package ava.generateAgents;

import ava.players.Strategy;
import ava.players.Agent;

/**
 * IAgentMaker - интерфейс, предназначенный для более гибкой работы с классами, реализовавшими его.
 * Содержит один метод - getAgent(int id, Strategy strategy, int coins), где id - идентификатор агента,
 * strategy - стратегия поведения в игре, coins - начальное количество монет.
 */
public interface IAgentMaker {
    Agent getAgent(int id, Strategy strategy, int coins);
}
