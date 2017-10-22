package ava.generateAgents;

import ava.players.Agent;
import ava.players.ArrayAgent;
import ava.players.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * AgentGenerator - класс, предназначенный для генерации агентов.
 */
public class AgentGenerator {
    private IAgentMaker agentMaker; // Отвечает за создание агентов.

    public AgentGenerator(IAgentMaker agentMaker) {
        this.agentMaker = agentMaker;
    }

    /**
     * Метод advancedGenerationForTest предназначен для тестирования, с его помощью можно генерировать заданное количество
     * наивных, жадных и рандомных агентов.
     *
     * @param countOfNaiveAgents  - количество наивных агентов.
     * @param countOfGreedyAgents - количество жадных агентов.
     * @param countOfRandomAgents - количетсво рандомных агентов.
     * @param numOfCoins          - количество монет, которое будет находиться у агентов.
     * @return класс ArrayAgent, содержащий внутри массив агентов.
     */
    public ArrayAgent advancedGenerationForTest(int countOfNaiveAgents, int countOfGreedyAgents, int countOfRandomAgents, int numOfCoins) {
        if (countOfNaiveAgents < 0 || countOfGreedyAgents < 0 || countOfRandomAgents < 0 || numOfCoins < 0) { // Проверка на корректные входные параметры.
            throw new RuntimeException("Error of initialization!");
        }
        List<Agent> tempAgents = new ArrayList<>(); // Временная коллекция агентов.
        int id = 1; // Идентификатор агента.
        for (int i = 0; i < countOfNaiveAgents; i++) { // Создание нужного количества наивных агентов.
            tempAgents.add(agentMaker.getAgent(id++, Strategy.NAIVE, numOfCoins));
        }
        for (int i = 0; i < countOfGreedyAgents; i++) { // Создание нужного количества жадных агентов.
            tempAgents.add(agentMaker.getAgent(id++, Strategy.GREEDY, numOfCoins));
        }
        for (int i = 0; i < countOfRandomAgents; i++) { // Создание нужного количества рандомных агентов.
            tempAgents.add(agentMaker.getAgent(id++, Strategy.RANDOM, numOfCoins));
        }
        Collections.shuffle(tempAgents); // Т.к. создание агентов было последовательным, для естественности игры коллекция мешается.
        Agent[] agents = new Agent[tempAgents.size()]; // Создание массива агентов.
        agents = tempAgents.toArray(agents); // Превращение коллекции в массив.
        return new ArrayAgent(agents); // Формирование ArrayAgent.
    }

    /**
     * Метод randomGeneration предназначен для непосредственного использования в программе,
     * он создает по заданному количеству агентов, которые имеют случайную стратегию поведения.
     *
     * @param numOfCoins    - количество монет, которое будет находиться у агентов.
     * @param countOfAgents - количество агентов, которое необходимо сформировать.
     * @return класс ArrayAgent, содержащий внутри массив агентов.
     */
    public ArrayAgent randomGeneration(int numOfCoins, int countOfAgents) {
        int id = 1; // Идентификатор агента.
        List<Agent> tempAgents = new ArrayList<>(); // Временная коллекция агентов.
        Strategy[] strategies = Strategy.values(); // Массив элементов перечисления.
        Random rand = new Random();
        for (int i = 0; i < countOfAgents; i++) { // // Создание нужного количества агентов, имеющих случайную стратегию поведения.
            tempAgents.add(agentMaker.getAgent(id++, strategies[rand.nextInt(strategies.length)], numOfCoins));
        }
        Agent[] agents = new Agent[tempAgents.size()]; // Создание массива агентов.
        agents = tempAgents.toArray(agents); // Превращение коллекции в массив.
        return new ArrayAgent(agents); // Формирование ArrayAgent.
    }
}
