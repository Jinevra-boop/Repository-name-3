package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;


public class Game {

    //attributes
    File myFile = new File("C://Users//Rita//Desktop//MyGame.txt");
    File myFile1 = new File("C://Users//Rita//Desktop", "MyGame.txt");
private Screens screen;
    private Player tom;
    private Monster monsters[][];
    private Weapon weapons[];
    private int score;

    //constructor
    Game(){

    /*Scanner text = new Scanner(System.in);
    System.out.print("Input name: ");
    String charactername = text.nextLine();*/
    tom=new Player(screen.DialogName());


    //generate monsters
    monsters = new Monster [5][2];
    monsters[0][0]=new Monster("Minotaur",10,3,50,1,5,4,1,2);
    monsters[1][0]=new Monster("Minotaur",10,3,50,1,5,4,1,2);
    monsters[2][0]=new Monster("Minotaur",10,3,50,1,5,4,1,2);
    monsters[3][0]=new Monster("Minotaur",10,3,50,1,5,4,1,2);
    monsters[4][0]=new Monster("Minotaur",10,3,50,1,5,4,1,2);
    monsters[0][0].displayInfo();

    //generate weapons
     weapons = new Weapon [10];
     weapons[0]=new Weapon("Archangel_Sword",3,10,40);
     weapons[1]=new Weapon("Sword of Magic Fog",2,12,40);
     tom.weapon=weapons[0];
    }
    /*
    Game(Player tom, Monster monsters[], int score){
        this.tom=tom;
monstr
        score
        //generate weapons
        weapons = new Weapon [10];
        weapons[0]=new Weapon("Archangel_Sword",3,10,40);
        weapons[1]=new Weapon("Sword of Magic Fog",2,12,40);
        tom.weapon=weapons[0];
        //нужна запись в файл
    }
    */
    //methods

    public void File_Reader_and_Writer() throws Exception{
        FileWriter nFile = new FileWriter("File1.txt");
        for(int i = 0; i <= 4; i++) {
            nFile.write(i+"\n");
            nFile.write("ertertet\n");
            System.out.println("File writen");
        }

        FileReader fr= new FileReader("File1.txt");
        Scanner scan = new Scanner(fr);
        int i = 1;
        while (scan.hasNextLine()) {
            System.out.println(i + " : " + scan.nextLine());
            i++;
            System.out.println("File readen");
        }
        fr.close();
    }

    public void File_work()throws Exception {
        File_Reader_and_Writer();
    }


public void Play()throws Exception{
        //File_work();
    screen.ScreenDoors();
       while(tom.Player_LVL()<5 && tom.life_status==true){

       }
    }

    public  void Open_Door(){
        int door =(int)(Math.random()*2);
        System.out.println("Дверь выбрана");
        System.out.println(door);
        switch (door) {
            case 0:   Fight_with_Monster();
                break;
            case 1:   Loot();
                break;
        }
    }

 private  int Set_Monster(){
       // int monster_number =(int)(Math.random()*2);
        int monster_number=0;
        System.out.println("Монстр выбран");
        return monster_number;
    }

    private void Upgrade(){
        System.out.println("Выберите, что желаете усилить: ");
        System.out.println("1. HP");
        System.out.println("2. Strength");
        Scanner num2 = new Scanner(System.in);
        int menu_num = num2.nextInt();
        switch (menu_num) {
            case 1: {
                tom.Increase_HP();
            }
            break;
            case 2: {
                tom.Increase_Strength();
            }
            break;
        }
    }

    private void Fight_with_Monster(){
        System.out.println("Начало боя");
        //выбор монстра на рандоме
        int monster_number = Set_Monster();
        int monster_lvl = tom.Player_LVL();
        //пока кто-то не умрет
        while(tom.Player_HP()>0 && monsters[monster_lvl][monster_number].hp>0){
            //ход игрока
            System.out.println("Выбирай, куда бить: ");
            System.out.println("1. Голова");
            System.out.println("2. Тело");
            System.out.println("3. Колени");
            System.out.println("4. Руки");
            System.out.println("5. Выпить исцеляющее зелье");
            Scanner num1 = new Scanner(System.in);
            int menu_num = num1.nextInt();
            switch (menu_num) {
                case 1:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength+weapons[0].Weapon_Damage()/monsters[monster_lvl][monster_number].Resist_Head);
                    break;
                case 2:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength+weapons[0].Weapon_Damage()/monsters[monster_lvl][monster_number].Resist_Body);
                    break;
                case 3:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength+weapons[0].Weapon_Damage()/monsters[monster_lvl][monster_number].Resist_Legs);
                    break;
                case 4:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength+weapons[0].Weapon_Damage()/monsters[monster_lvl][monster_number].Resist_Arms);
                    break;
                case 5:
                    tom.Drink_Potion();
                    break;
            }
            //ход монстра
            tom.Set_Player_HP(monsters[monster_lvl][monster_number].strength);
            monsters[monster_lvl][monster_number].displayInfo();
            tom.displayInfo();
        }
        if(tom.Player_HP()<=0){
            System.out.println("You lose");
            tom.Set_Player_Life_Status(false);
            // ПРОПИСАТЬ ЭТОТ МОМЕНТ ПОДРОБНЕЕ
        }
        if(monsters[monster_lvl][monster_number].hp<=0) {
            System.out.println("Вы победили монстра!");
            tom.Set_Player_Life_Status(true);
            Upgrade();
            tom.Set_Player_LVL(); //повышение уровня
            tom.displayInfo();
        }
    }

    private void Loot(){
        int new_score;
        Weapon new_weapon;
        int max_score=100, min_score=20;
        int i =(int)(Math.random()*2);
        new_weapon=weapons[i];
        if(new_weapon.max_damage>tom.weapon.max_damage){
                tom.Change_Weapon(new_weapon);
                System.out.println("Вы сменили оружие на более сильное");
        }
        else{
        System.out.println("Ваше оружие лучше того, что вы нашли");}
        int diff = max_score-min_score;
        Random random = new Random();
        new_score = random.nextInt(diff + 1);
        new_score =new_score+min_score;
        score=score+new_score;
        tom.displayInfo();
    }

}
