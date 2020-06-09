package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;

public class Screens{
    private Game game;
    private Main main;
    int wid = 1080, hei = 720; //Переменные, отвечающие за размеры окна

    public void MainScreen() {
        int bx = 180, by = 35;
        int wid = 540, hei =360; //Переменные, отвечающие за размеры окна
        JFrame Window = new JFrame("Game"); //создание окна с названием "Game"
        Window.setPreferredSize(new Dimension(wid, hei)); //Размеры окна
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрытие окна - прекращение работы программы

        JPanel Panel = new JPanel(null); //задаём панель, на котором можно размещать другие элементы
        // Установка абсолютного позиционирования
        Window.setVisible(true); // видимость фигуры
        Panel.setBackground(Color.pink);
        Window.add(Panel); //добавляем панель к фрейму
        JButton button1 = new JButton("Начать игру");
        button1.setBounds(bx, by, 150, 35);
        Panel.add(button1);
        JButton button2 = new JButton("Загрузить игру");
        button2.setBounds(bx, by + 50, 150, 35);
        Panel.add(button2);
        JButton button3 = new JButton("Доска почёта");
        button3.setBounds(bx, by + 100, 150, 35);
        Panel.add(button3);
        JButton button4 = new JButton("Справка");
        button4.setBounds(bx, by + 150, 150, 35);
        Panel.add(button4);
        JButton button5 = new JButton("Выход");
        button5.setBounds(bx, by + 200, 150, 35);
        Panel.add(button5);
        Panel.setVisible(true);
        Window.pack(); //объединяем в пакет

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Window.setVisible(false);
                    main.Start_Game();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.Load_Game();
					DialogSaveName();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.Hall_of_Fame();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.Save_Game();
					AddSaveNames();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Действие
                Window.setVisible(false);
                System.exit(0);
            }
        });

    }
    public static String DialogName(){
        return showInputDialog("Имя игрока:");
            }
    public static String DialogDoor(){
       String[] door  = {"Первая дверь", "Вторая дверь"};
        Object result=showInputDialog(null, "Сделай правильный выбор, герой!","Двери",JOptionPane.PLAIN_MESSAGE,null,door,null);
        return String.valueOf(result);
    }
    public static String  DialogMonster(String PlayerInfo, String MonsterName){
         String[] punch  = {"Ударить по голове", "Ранить тело", "Сломать колени", "Укусить за руку", "Выписть исцеляющее зелье"};
        Object result=showInputDialog(null,"За этой дверью был "+MonsterName+"! Настало время битвы!\nЧто будешь делать?\nСтатус героя: "+PlayerInfo,"Бой",JOptionPane.PLAIN_MESSAGE,null,punch,null);
         return String.valueOf(result);
    }

  public static void  DialogLoot(String Message, int Score){
      showMessageDialog(null,Message+ "\nЧисло очков: "+ Score);
  }

    public static String  DialogUpgrade(){
        String[] Choose  = {"Здоровье HP", "Сила"};
        Object result=showInputDialog(null,"Ты выжил! Выбери, что желаешь усилить!","Передышка",JOptionPane.PLAIN_MESSAGE,null,Choose,null);
        return String.valueOf(result);
    }

    public static void  DialogLose(String name, int score){
        showInputDialog(null,"Игра окончена, герой "+name+"!\nСчёт:"+score+" \nПоследние слова:");
    }

    public static void  DialogWin(String name, int score){
        showInputDialog(null,"Ты победил, герой "+name+"!\nСчёт:"+score+"\nСкажи пару слов на прощанье:\n");
    }
	
	public void DialogSaveName() throws FileNotFoundException {
        Scanner NewScan = new Scanner(new File("SaveName.txt"));
        ArrayList<String> Name = new ArrayList<>();
        int NumberNames = 0;
        while (NewScan.hasNextLine()) {
            Name.add(NewScan.nextLine());
            NumberNames++;
        }
        String[] Names = new String[256];
        String[] result = new String[256];
        for (int i = 0; i < NumberNames; i++) {
            result[i] = Names[i] + "\n";
        }
        showMessageDialog(null, result);
    }
	
	public void AddSaveNames() throws IOException{
        FileWriter writer = new FileWriter("SaveNames.txt", true);
        String text = tom.name+'\n';
        writer.write(text);
        writer.flush();
    }
}


