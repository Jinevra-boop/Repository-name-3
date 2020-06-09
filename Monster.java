package com.company;

public class Monster {
    //attributes
    String name;
    int hp;
    int lvl;
    Weapon weapons[];
    int monsterscore;
    int Resist_Head;
    int Resist_Body;
    int Resist_Legs;
    int Resist_Arms;
    int strength;

    //constructor
    Monster(String name, int hp, int strength, int monsterscore, int lvl, int resist_Head, int resist_Body, int resist_Arms, int resist_Legs){
        this.hp=hp;
        this.monsterscore=monsterscore;
        //this.weapon=2;
        this.name=name;
        this.strength=strength;
        this.Resist_Head=resist_Head;
        this.Resist_Body=resist_Body;
        this.Resist_Arms=resist_Arms;
        this.Resist_Legs=resist_Legs;
        System.out.println("Монстр создан");
    }

    //methods
    public void displayInfo(){
        System.out.printf("Name: %s \tHP: %d\n", name, hp);
    }

    public void Set_Monster_HP(int a){
        hp=hp-a;
    }
    public void showHP(){
        System.out.printf("Name: %s \tHP: %d\n", name, hp);
    }
}
