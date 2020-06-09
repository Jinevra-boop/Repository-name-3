package com.company;
import java.util.Random;
public class Weapon {
    //attributes
     String name;
    int max_damage;
    private int min_damage;
    private int crit_rate;

    //constructor
    Weapon(String name, int min_damage,int max_damage, int crit_rate){
        this.name=name;
        this.max_damage=max_damage;
        this.min_damage=min_damage;
        this.crit_rate=crit_rate;
    }

    //methods
    public int Weapon_Damage() {
        int damage;
        int diff = this.max_damage - this.min_damage;
        Random random = new Random();
        damage = random.nextInt(diff + 1);
        damage =damage+this.min_damage;
        return (damage+Weapon_Crit_Rate());
    }

    public int Weapon_Crit_Rate() {
        int chance;
        int crit_rate_damage;
        int diff = 100-crit_rate;
        Random random = new Random();
        chance = random.nextInt(diff + 1);
        chance =chance+this.crit_rate;
        if(chance>=80){
            crit_rate_damage =2;
            System.out.println("Критический удар!");}
        else{crit_rate_damage=0;}
        return (crit_rate_damage);
    }

public int Damage_Weapon(){
        return (max_damage);
}
    public void displayInfo(){
        System.out.printf("Name: %s \tMax damage: %d\tMin damage: %d\tCrit rate: %d\n", name, max_damage,min_damage,crit_rate);
    }
}
