package ava.players;

/**
 * Strategy - перечисление, представляющее стратегию поведения в игре.
 * NAIVE - наивная стратегия(всегда ставит одну монету).
 * GREEDY - жадная стратегия(никогда не ставит монет).
 * RANDOM - случайная стратегия(случайным образом ставит нуль или одну монету).
 */
public enum Strategy {
    NAIVE, GREEDY, RANDOM
}
