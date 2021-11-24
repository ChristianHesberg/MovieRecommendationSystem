/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import movierecsys.be.Movie;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class UserDAO
{

    private static final String USER_SOURCE = "data/users.txt";
    
    /**
     * Gets a list of all known users.
     * @return List of users.
     */
    public List<User> getAllUsers()
    {
        List<User> allUsers = new ArrayList<>();
        File file = new File(USER_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNextLine())
            {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int userID = Integer.parseInt(lineSplit[0]);
                String name = lineSplit[1];

                User user = new User(userID, name);
                allUsers.add(user);
            }
            return allUsers;

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /**
     * Gets a single User by its ID.
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id)
    {
        try (FileReader reader = new FileReader(USER_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int ID = Integer.parseInt(lineSplit[0]);
                if(ID == id)
                {
                    String name = lineSplit[1];
                    User user = new User(ID, name);
                    return user;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    /**
     * Updates a user so the persistence storage reflects the given User object.
     * @param user The updated user.
     */
    public void updateUser(User user)
    {
        String newFileText = "";
        try (FileReader reader = new FileReader(USER_SOURCE);
             BufferedReader br = new BufferedReader(reader)) {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine().trim();
                String[] lineSplit = currentLine.split(",");
                int ID = Integer.parseInt(lineSplit[0].trim());
                if (ID != user.getId()) {
                    newFileText += currentLine + "\r\n";
                }
                if (ID == user.getId())
                {
                    newFileText += user.getId() + "," + user.getName() + "\r\n";
                }
            }
            try (FileWriter writer = new FileWriter(USER_SOURCE);
                 BufferedWriter bw = new BufferedWriter(writer))
            {
                bw.write(newFileText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
