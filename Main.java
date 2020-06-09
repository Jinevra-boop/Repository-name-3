package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    private Game game;
    private Screens screen;

    public static void main(String[] args) throws Exception {

        Screens screen = new Screens();
        screen.MainScreen();

    }


    public static void Start_Game()throws Exception{
        Game game = new Game();
        System.out.println("Игра создана");
        game.Play();
        System.out.println("Игра прогналась");
    }
    public static void Save_Game(){ }

    public static void Load_Game(){ }

    public static void Hall_of_Fame(){ }

}


