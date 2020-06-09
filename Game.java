package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player tom;
    private Monster[][] monsters;
    private Weapon[] weapons;
    private int score;

    Game() {
        Scanner text = new Scanner(System.in);
        System.out.print("Input name: ");
        String charactername = text.nextLine();
        tom = new Player(charactername);
        monsters = new Monster[5][2];
        monsters[0][0] = new Monster("Воин-наемник орков", 10, 3, 1, 5, 4, 1, 2, 50);
        monsters[0][1] = new Monster("Жук-ассасин", 10, 3, 1, 6, 3, 2, 1, 50);
     monsters[1][0] = new Monster("Гарпия бездны", 30, 4, 2, 6, 8, 2, 4, 200);
        monsters[1][1] = new Monster("Ликантроп", 30, 4, 2, 4, 10, 3, 3, 200);
        monsters[2][0] = new Monster("Лорд Гоблинов", 50, 7, 3, 15, 5, 4, 6, 300);
        monsters[2][1] = new Monster("Ведьма Мойра", 50, 7, 3, 5, 10, 10, 5, 300);
        monsters[3][0] = new Monster("Заклинатель вампиров", 75, 9, 4, 3, 10, 10, 10, 400);
        monsters[3][1] = new Monster("Кровожадный палач", 75, 9, 4, 5, 10, 15, 5, 400);
        monsters[4][0] = new Monster("Минотавр", 100, 11, 5, 20, 15, 5, 10, 1000);
        monsters[4][1] = new Monster("Королева банши", 100, 11, 5, 10, 15, 20, 5, 1000);
        monsters[0][0].displayInfo();
        weapons = new Weapon[11];
        weapons[0] = new Weapon("Меч архангела", 3, 10, 40);
        weapons[1] = new Weapon("Меч волшебного тумана", 2, 12, 40);
        weapons[2] = new Weapon("Эльфийский меч", 1, 11, 60);
        weapons[3] = new Weapon("Катана", 4, 13, 50);
        weapons[4] = new Weapon("Меч кошмаров", 8, 13, 30);
        weapons[5] = new Weapon("Громовержец", 5, 14, 40);
        weapons[6] = new Weapon("Меч стихий", 6, 15, 70);
        weapons[7] = new Weapon("Драконий лук", 10, 18, 50);
        weapons[8] = new Weapon("Копье вечности", 10, 20, 60);
        weapons[9] = new Weapon("Клинок Убийца драконов", 15, 30, 50);
        weapons[10] = new Weapon("Экскалибур", 20, 35, 70);
        tom.weapon = this.weapons[0];
    }
    //methods
  public void Load_Game()throws Exception{
      String File_name = tom.name;
      try {
          DataInputStream dos = new DataInputStream(new FileInputStream(File_name + ".bin"));
          try {
              boolean life_status = dos.readBoolean();

              this.tom.lvl = dos.readInt();
              this.tom.hp = dos.readInt();
              this.tom.max_hp = dos.readInt();
              this.tom.strength = dos.readInt();
              String name = dos.readUTF();
              this.score = dos.readInt();
              this.tom.displayInfo();
          } catch (Throwable var11) {
              try {
                  dos.close();
              } catch (Throwable var10) {
                  var11.addSuppressed(var10);
              }

              throw var11;
          }

          dos.close();
      } catch (IOException var12) {
          System.out.println(var12.getMessage());
      }

  }

    public void Save_Game() throws Exception {
        String File_name = this.tom.name;
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(File_name + ".bin"));

            try {
                dos.writeBoolean(this.tom.life_status);
                dos.writeInt(2);
                dos.writeInt(10);
                dos.writeInt(20);
                dos.writeInt(2);
                dos.writeUTF(this.tom.name);
                dos.writeInt(this.score);
                System.out.println("File has been written");
            } catch (Throwable var6) {
                try {
                    dos.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            dos.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }

    }


    public void Play() throws Exception {
        while(this.tom.Player_LVL() < 5 && this.tom.life_status) {
            this.Open_Door();
        }

    }

    private void Open_Door() throws Exception {
        int door = (int)(Math.random() * 2.0D);
        System.out.println("Дверь выбрана");
        System.out.println(door);
        switch(door) {
            case 0:
                this.Fight_with_Monster();
                break;
            case 1:
                this.Loot();
                break;
        }

    }

    private int Set_Monster() {
        int monster_number = 0;
        System.out.println("Монстр выбран");
        return monster_number;
    }

    private void Upgrade() {
        System.out.println("Выберите, что желаете усилить: ");
        System.out.println("1. HP");
        System.out.println("2. Strength");
        Scanner num2 = new Scanner(System.in);
        int menu_num = num2.nextInt();
        switch(menu_num) {
            case 1:
                tom.Increase_HP();
                break;
            case 2:
                tom.Increase_Strength();
        }

    }

    private void Fight_with_Monster() throws Exception{
        System.out.println("Начало боя");
        int monster_number = Set_Monster();
        int monster_lvl = tom.Player_LVL();

        while(tom.Player_HP() > 0 && monsters[monster_lvl][monster_number].hp > 0) {
            System.out.println("Выбирай, куда бить: ");
            System.out.println("1. Голова");
            System.out.println("2. Тело");
            System.out.println("3. Колени");
            System.out.println("4. Руки");
            System.out.println("5. Выпить исцеляющее зелье");
            Scanner num1 = new Scanner(System.in);
            int menu_num = num1.nextInt();
            switch(menu_num) {
                case 1:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Head);
                    break;
                case 2:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + this.weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Body);
                    break;
                case 3:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + this.weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Legs);
                    break;
                case 4:
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + this.weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Arms);
                    break;
                case 5:
                    tom.Drink_Potion();
            }

            tom.Set_Player_HP(monsters[monster_lvl][monster_number].strength);
            monsters[monster_lvl][monster_number].displayInfo();
            tom.displayInfo();
        }

        if (tom.Player_HP() <= 0) {
            System.out.println("You lose");
            tom.Set_Player_Life_Status(false);
        }

        if (monsters[monster_lvl][monster_number].hp <= 0) {
            System.out.println("Вы победили монстра!");
            tom.Set_Player_Life_Status(true);
            score += monsters[monster_lvl][monster_number].score;
            Upgrade();
            tom.Set_Player_LVL();
            tom.displayInfo();
            Save_Game();
        }

    }

    private void Loot() {
        int max_score = 100;
        int min_score = 20;
        int i = (int)(Math.random() * 10.0D);
        Weapon new_weapon = weapons[i];
        if (new_weapon.max_damage > tom.weapon.max_damage) {
            tom.Change_Weapon(new_weapon);
            System.out.println("Вы сменили оружие на более сильное");
        } else {
            System.out.println("Ваше оружие лучше того, что вы нашли");
        }

        int diff = max_score - min_score;
        Random random = new Random();
        int new_score = random.nextInt(diff + 1);
        new_score += min_score;
        score += new_score;
        tom.displayInfo();
    }
}