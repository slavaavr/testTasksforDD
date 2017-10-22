package ava.logic;

import ava.players.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Game - класс, отвечающий за логику работы игры.
 * В классе имеется два поля:
 * arrAgents - переменная, хранящая массив агентов.
 * numberOfMoves - количество ходов агента в раунде игры.
 * <p>
 * Описание игры
 * По условию задачи каждый агент играет с каждым, исходя из этого и строится логика.
 * Все игроки расположены в массиве. Выбирается первый игрок, ему присваивается метка EXTERN_AGENT, противником ему ставится второй игрок с меткой INNER_AGENT.
 * В случае победы первого игрока, первый игрок вступает в игру со вторым, и т.д. Если же первый проиграл,
 * то выбирается следующий игрок, что расположен за первым и продолжает играть с игроками, следующими за ним.
 * Метка EXTERN_AGENT соответствует текущему игроку, который играет со всеми до своего поражения, а метка INNER_AGENT соответствует промежуточному игроку с которым играет EXTERN_AGENT.
 * Игра состоит из раундов. В раунде определенное количество ходов, которое соответствует начальному количеству монет у агентов.
 * Игра продолжается до тех пор, пока не останется один агент или не будет выявлено, что конец игры недостижим.
 */

public class Game {

    private ArrayAgent arrAgents;
    private int numberOfMoves;

    /**
     * WhomToRemove - перечисление, предназначенное для метки удаляемого агента.
     * EXTERN_AGENT - текущий игрок.
     * INNER_AGENT - промежуточный игрок, с которым играет EXTERN_AGENT.
     * BOTH_AGENT - метка, обозначающая обоих игроков.
     * NOTHING - метка, которая обозначает никакого игрока.
     */
    private enum WhomToRemove {
        EXTERN_AGENT, INNER_AGENT, BOTH_AGENT, NOTHING
    }

    /**
     * Метод start запускает игру.
     *
     * @param arrAgents - переменная, хранящая массив агентов.
     */
    public void start(ArrayAgent arrAgents) throws IOException {
        if (arrAgents != null && arrAgents.getCountActiveAgents() != 0) { // Проверка, что в arrAgents есть агенты.
            this.arrAgents = arrAgents;
            this.numberOfMoves = arrAgents.getInitialNumberOfCoins();
            WhomToRemove wtr;

            FileWriter f = new FileWriter("output.txt", true);

            while (this.arrAgents.getCountActiveAgents() > 1) { // Условие остановки игры.
                for (int i = 0; i < this.arrAgents.getArrayLength(); i++) { // Выбирается EXTERN_AGENT.
                    for (int j = i + 1; j < this.arrAgents.getArrayLength(); j++) { // Выбирается INNER_AGENT.
                        if (this.arrAgents.getAgent(i) == null) // Проверка, что EXTERN_AGENT не в игре.
                            break;
                        if (this.arrAgents.getAgent(j) == null) // Проверка, что INNER_AGENT не в игре.
                            continue;

                        System.out.println("Всего: " + this.arrAgents.getCountActiveAgents());
                        System.out.println("До:\t\t" + this.arrAgents.getAgent(i) + " " + this.arrAgents.getAgent(j));
                        f.write("Всего: " + this.arrAgents.getCountActiveAgents() + "\n");
                        f.write("До:\t\t" + this.arrAgents.getAgent(i) + " " + this.arrAgents.getAgent(j) + "\n");

                        wtr = round(this.arrAgents.getAgent(i), this.arrAgents.getAgent(j)); // Проведение раунда.

                        System.out.println("После:\t" + this.arrAgents.getAgent(i) + " " + this.arrAgents.getAgent(j));
                        System.out.println();
                        f.write("После:\t" + this.arrAgents.getAgent(i) + " " + this.arrAgents.getAgent(j) + "\n\n");
                        f.flush();

                        if (wtr == WhomToRemove.EXTERN_AGENT) {

                            System.out.println("Удален:\t" + this.arrAgents.getAgent(i));
                            System.out.println();
                            f.write("Удален:\t" + this.arrAgents.getAgent(i) + "\n\n");
                            f.flush();

                            this.arrAgents.deleteAgent(i); // Удаление EXTERN_AGENT.
                            break;
                        } else if (wtr == WhomToRemove.INNER_AGENT) {

                            System.out.println("Удален:\t" + this.arrAgents.getAgent(j));
                            System.out.println();
                            f.write("Удален:\t" + this.arrAgents.getAgent(j) + "\n\n");
                            f.flush();

                            this.arrAgents.deleteAgent(j); // Удаление INNER_AGENT.
                        } else if (wtr == WhomToRemove.BOTH_AGENT) {

                            System.out.println("Удален:\t" + this.arrAgents.getAgent(i));
                            System.out.println("Удален:\t" + this.arrAgents.getAgent(j));
                            System.out.println();
                            f.write("Удален:\t" + this.arrAgents.getAgent(i) + "\n" + "Удален:\t" + this.arrAgents.getAgent(j) + "\n\n");

                            this.arrAgents.deleteAgent(i); // Удаление EXTERN_AGENT.
                            this.arrAgents.deleteAgent(j); // Удаление INNER_AGENT.

                            break;
                        }
                    }
                }
                if (isGameEndless()) { // Проверка конца игры на недостижимость.
                    System.out.println("---Игра бесконечна!---");
                    break;
                }
            }
        }
    }

    /**
     * Метод WhomToRemove представляет собой раунд игры.
     *
     * @param a - EXTERN_AGENT.
     * @param b - INNER_AGENT.
     * @return возвращается метка того, кого нужно удалить.
     */
    private WhomToRemove round(Agent a, Agent b) {
        int ansA, ansB; // Переменные хранят количество положенных агентом монет (0 или 1).
        int ctrlCoinsA, ctrlCoinsB; // Переменные предназначены для контроля отрицательного количества монет.
        for (int i = 0; i < this.numberOfMoves; i++) {
            ansA = a.getAns();
            ansB = b.getAns();
            if (ansA == Agent.BET_FOR_1_COIN && ansB == Agent.BET_FOR_1_COIN) { // Если оба положили по монете, то оба получают по 2.
                a.incrementCoins(2);
                b.incrementCoins(2);
            } else if (ansA == Agent.BET_FOR_0_COIN && ansB == Agent.BET_FOR_0_COIN) { // Если оба не положили - оба штрафуются на 1 монету.
                ctrlCoinsA = a.decrementCoins(1);
                ctrlCoinsB = b.decrementCoins(1);
                if (ctrlCoinsA < 0 && ctrlCoinsB < 0) { // Проверка на количество монет после декремента.
                    return WhomToRemove.BOTH_AGENT;
                } else if (ctrlCoinsA < 0) {
                    return WhomToRemove.EXTERN_AGENT;
                } else if (ctrlCoinsB < 0) {
                    return WhomToRemove.INNER_AGENT;
                }
            } else if (ansA == Agent.BET_FOR_0_COIN && ansB == Agent.BET_FOR_1_COIN) { // Если один положил монету, а другой нет, то первому штраф 1 монета, а второй получает 3.
                a.incrementCoins(3);
                ctrlCoinsB = b.decrementCoins(1);
                if (ctrlCoinsB < 0) {
                    return WhomToRemove.INNER_AGENT;
                }
            } else if (ansA == Agent.BET_FOR_1_COIN && ansB == Agent.BET_FOR_0_COIN) { // Если один положил монету, а другой нет, то первому штраф 1 монета, а второй получает 3.
                ctrlCoinsA = a.decrementCoins(1);
                b.incrementCoins(3);
                if (ctrlCoinsA < 0) {
                    return WhomToRemove.EXTERN_AGENT;
                }
            }
        }
        return WhomToRemove.NOTHING; // Если у агентов положительное кол-во монет, то никто не удаляется.
    }

    private boolean isGameEndless() {
        if (this.arrAgents.getCountActiveAgents() < 2) { // Если число агентов, играющих в игру меньше 2, значит игра достежима.
            return false;
        }
        int countNaive = 0, countGreedy = 0, counntRandom = 0; // Счетчики количеста наивных, жадных и рандомных агентов.
        for (int i = 0; i < this.arrAgents.getArrayLength(); i++) {
            if (this.arrAgents.getAgent(i) != null) { // Если агент ещё играет
                if (this.arrAgents.getAgent(i).getClass() == NaiveAgent.class)
                    countNaive++;
                else if (this.arrAgents.getAgent(i).getClass() == GreedyAgent.class)
                    countGreedy++;
                else if (this.arrAgents.getAgent(i).getClass() == RandomAgent.class)
                    counntRandom++;
            }
        }
        if (countGreedy == 0) // В игре только наивные и рандомные агенты.
            return true;
        else if (counntRandom == 0) // В игре все агенты наивные.
            return true;
        else if (countNaive == 0) // В игре все агенты рандомные.
            return true;

        boolean checkGreedyAgent = false; // Опытным путем было обнаружено, что жадный игрок долен присутствовать в первое трети массива игроков,
                                            // чтобы игра не была бесконечной.
        for (int i = 0; i < this.arrAgents.getArrayLength()/3; i++) { //
            if(this.arrAgents.getAgent(i)!= null){
                if(this.arrAgents.getAgent(i).getClass() == GreedyAgent.class){
                    checkGreedyAgent = true;
                    break;
                }
            }
        }
        if ((countNaive + counntRandom - countGreedy) > 1 && !checkGreedyAgent ){ // Также было установлено, что при наивные+рандомные-жадные>1 начинаются проблемы с недостежимостью конца игры.
            return true;
        }
        return false;
    }

}
