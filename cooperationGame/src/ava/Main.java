package ava;

import ava.generateAgents.AgentGenerator;
import ava.generateAgents.AgentMaker;
import ava.logic.Game;
import ava.players.Agent;
import ava.players.ArrayAgent;
import ava.players.NaiveAgent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество агентов: ");
        int countOfAgent = sc.nextInt();
        System.out.print("Введите количество монет: ");
        int numOfCoins = sc.nextInt();
        ArrayAgent agents;
        AgentGenerator generator = new AgentGenerator(new AgentMaker());
        agents = generator.randomGeneration(numOfCoins, countOfAgent);
//        agents = generator.advancedGenerationForTest(3, 1,3,numOfCoins); // Эту строчку можно использовать для отладки.
        Game game = new Game();
        String s = agents.print();

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(s+"\n\n");
        fileWriter.flush();

        game.start(agents);
        System.out.println();
        if(agents.getCountActiveAgents() == 1){
            System.out.println("Выиграл");
            agents.print();
        } else{
            System.out.println("Оставшиеся");
            agents.print();
        }
    }

}
