package com.company;
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

    private Player tom;
    private Monster monsters[][];
    private Weapon weapons[];
    private int score;

    //constructor
           Game(){
            Scanner text = new Scanner(System.in);
            System.out.print("Input name: ");
            String charactername = text.nextLine();
            tom=new Player(charactername);

            //generate monsters
    monsters = new Monster [5][2];
    monsters[0][0]=new Monster("Воин-наемник орков",10,3,1,5,4,1,2,50);
    monsters[0][1]=new Monster("Жук-ассасин",10,3,1,6,3,2,1,50);
    monsters[1][0]=new Monster("Гарпия бездны",30,4,2,6,8,2,4,200);
    monsters[1][1]=new Monster("Ликантроп",30,4,2,4,10,3,3,200);
    monsters[2][0]=new Monster("Лорд Гоблинов",50,7,3,15,5,4,6,300);
    monsters[2][1]=new Monster("Ведьма Мойра",50,7,3,5,10,10,5,300);
    monsters[3][0]=new Monster("Заклинатель вампиров",75,9,4,3,10,10,10,400);
    monsters[3][1]=new Monster("Кровожадный палач",75,9,4,5,10,15,5,400);
    monsters[4][0]=new Monster("Минотавр",100,11,5,20,15,5,10,1000);
    monsters[4][1]=new Monster("Королева банши",100,11,5,10,15,20,5,1000);
    monsters[0][0].displayInfo();

    //generate weapons
     weapons = new Weapon [11];
     weapons[0]=new Weapon("Меч архангела",3,10,40);
     weapons[1]=new Weapon("Меч волшебного тумана",2,12,40);
     weapons[2]=new Weapon("Эльфийский меч",1,11,60);
     weapons[3]=new Weapon("Катана",4,13,50);
     weapons[4]=new Weapon("Меч кошмаров",8,13,30);
     weapons[5]=new Weapon("Громовержец",5,14,40);
     weapons[6]=new Weapon("Меч стихий",6,15,70);
     weapons[7]=new Weapon("Драконий лук",10,18,50);
     weapons[8]=new Weapon("Копье вечности",10,20,60);
     weapons[9]=new Weapon("Клинок Убийца драконов",15,30,50);
     weapons[10]=new Weapon("Экскалибур",20,35,70);
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

    public void File_Writer() throws Exception{
        String File_name=tom.name;
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(File_name+".bin")))
        {
            // записываем значения
            /*dos.writeBoolean(tom.life_status);
            dos.writeInt(tom.lvl);
            dos.writeInt(tom.hp);
            dos.writeInt(tom.max_hp);
            dos.writeInt(tom.strength);
            dos.writeUTF(tom.name);
            dos.writeInt(score);*/
            dos.writeBoolean(tom.life_status);
            dos.writeInt(2);
            dos.writeInt(10);
            dos.writeInt(20);
            dos.writeInt(2);
            dos.writeUTF(tom.name);
            dos.writeInt(score);
            System.out.println("File has been written");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public void File_Reader() throws Exception{
        String File_name=tom.name;
        try(DataInputStream dos = new DataInputStream(new FileInputStream(File_name+".bin")))
        {
            //записываем значения
            boolean life_status = dos.readBoolean();
            int lvl = dos.readInt();
            int hp = dos.readInt();
            int max_hp = dos.readInt();
            int strength = dos.readInt();
            String name = dos.readUTF();
            int new_score = dos.readInt();
            tom.lvl=lvl;
            tom.hp=hp;
            tom.max_hp=max_hp;
            tom.strength=strength;
            score=new_score;
            tom.displayInfo();
            System.out.printf("Alive: %b lvl: %d hp: %d max_hp: %d strenght: %d Name: %s score: %d",
                    life_status, lvl, hp,max_hp,strength,name,new_score);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void File_work()throws Exception {
        //File_Writer();
       File_Reader();
    }

public void Play()throws Exception{
        File_work();
        while(tom.Player_LVL()<5 && tom.life_status==true){
       Open_Door();}
    }

    private void Open_Door()throws Exception{
        int door =(int)(Math.random()*2);
        System.out.println("Дверь выбрана");
        System.out.println(door);
        switch (door) {
            case 0:  Fight_with_Monster();
                break;
            case 1:  Loot();
                break;
            case 2:  File_Writer();
                break;
        }
    }
    private int Set_Monster(){
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
            score=monsters[monster_lvl][monster_number].score+score;
            Upgrade();
            tom.Set_Player_LVL(); //повышение уровня
            tom.displayInfo();
        }
    }

    private void Loot(){
        int new_score;
        Weapon new_weapon;
        int max_score=100, min_score=20;
        int i =(int)(Math.random()*10);
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
