package com.company;

public class Main {
    private Game game;

    public Main() {
    }

    public static void main(String[] args) throws Exception {
        //Start_Game();
       Load_the_Game();
    }

    public static void Start_Game() throws Exception {
        Game game = new Game();
        game.Play();
    }

    public static void Save_the_Game() throws Exception{
        Game game = new Game();
        game.Save_Game();
        game.Play();
    }

    public static void Load_the_Game()throws Exception {
        Game game = new Game();
        game.Load_Game();
        game.Play();
    }

    public static void Exit() {
    }

    public static void Hall_of_Fame() {
        System.out.println("Добро пожаловать в игру Подземелья! Каждый раунд на выбор тебе дается две двери: за одной из них находятся сокровища (золотые монеты или оружие), а за другой тебя ждет битва с монстром! Каждый монстр имеет свой уникальный запас здоровья, силу, и имеет разные уязвимые места. Для победы над монстром тебе понадобится применить смекалку и свои стратегические способности. Каждый ход монстра можно ударить по одной из четырех болевых точек. Или же можно выпить исцеляющее зелье, но это займет целый ход. При успешном поражении монстра тебе будет предложен выбор: увеличить свой запас здоровья или усилить мощность удара. Также за каждый выигранный бой повышается и твой уровень. Если же победа была одержана монстром, единственное, что тебе остается - не отчаиваться и оставить парочку слов напоследок для доски почета. Всего в игре 5 уровней, и, чтобы пройти игру, нужно одолеть всех монстров, повстречавшихся тебе на пути. Желаем удачи!  ");
    }
}
