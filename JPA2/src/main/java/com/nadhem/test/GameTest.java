package com.nadhem.test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import com.nadhem.dao.GameDao;
import com.nadhem.entities.Game;
import com.nadhem.util.JPAutil;

public class GameTest {

    public static void main(String[] args) {
        int opt = 99;
        String name, type;
        Scanner scanner = new Scanner(System.in);

        while (opt != 0) {
            System.out.println("_____________________________________________________");
            System.out.println("1: Add a game\n2: Show all games\n3: Show games by name\n4: Modify a game\n5: Delete a game\n0: Exit");
            opt = scanner.nextInt();

            GameDao cltDao = new GameDao();

            switch (opt) {
                case 1:
                	scanner = new Scanner(System.in);                    System.out.println("Name:");
                    name = scanner.nextLine();
                    System.out.println("Type:");
                    scanner = new Scanner(System.in);
                    type = scanner.nextLine();
                    // Create a new game object
                    Game c = new Game();
                    c.setName(name);
                    c.setType(type);
                    // Add the game to the database
                    cltDao.ajouter(c);
                    break;
                case 2:
                    List<Game> games = cltDao.listerTous();
                    if (games.isEmpty()) {
                        System.out.println("No games found.");
                    } else {
                        for (Game cl : games) {
                            System.out.println(cl.getId() + " " + cl.getName()+" "+cl.getType());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Name:");
                    scanner = new Scanner(System.in);
                    name = scanner.nextLine();
                    games = cltDao.listerParName(name);
                    if (games.isEmpty()) {
                        System.out.println("No games found with the name " + name);
                    } else {
                        for (Game cl : games) {
                            System.out.println(cl.getId() + " " + cl.getName());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter the ID of the game to modify:");
                    scanner = new Scanner(System.in);
                    int id = scanner.nextInt();
                    Game game = cltDao.consulter(new Game(), id);
                    if (game == null) {
                        System.out.println("Client with ID " + id + " not found.");
                    } else {
                        System.out.println("Current information:");
                        System.out.println("Name: " + game.getName());
                        System.out.println("Type: " + game.getType());
                        System.out.println("Enter new information or leave blank to keep unchanged:");

                        System.out.print("Name: ");
                        scanner = new Scanner(System.in);
                        name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            game.setName(name);
                        }

                        System.out.print("Type: ");
                        scanner = new Scanner(System.in);
                        type = scanner.nextLine();
                        if (!type.isEmpty()) {
                            game.setType(type);
                        }

                        cltDao.modifier(game);
                        System.out.println("Client modified !");
                    }
                    break;
                case 5:
                    System.out.println("Enter id");
                    scanner = new Scanner(System.in);
                    id = scanner.nextInt();
                    game = cltDao.consulter(new Game(), id);
                    if (game == null) {
                        System.out.println("Client id not found");
                    } else {
                        System.out.println("_____________________________");

                        System.out.println("game information:");
                        System.out.println("name: " + game.getName());
                        System.out.println("type: " + game.getType());
                        try {
                          cltDao.supprimer(game);
                          System.out.println("Client deleted");


                        }catch(Exception e){
                        	System.out.println("erorr !!!!!!");
                        }
                        
                    }
            }
        }
    }
}
