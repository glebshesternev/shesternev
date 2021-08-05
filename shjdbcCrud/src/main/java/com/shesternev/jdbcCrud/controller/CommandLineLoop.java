package com.shesternev.jdbcCrud.controller;

import com.shesternev.jdbcCrud.model.User;
import com.shesternev.jdbcCrud.service.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineLoop {

    @Autowired
    private UserService userService;

    public void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag) {
            System.out.print(">");
            String line = bufferedReader.readLine().trim().toLowerCase();
            String[] args = line.split(" ");
            switch (args[0]) {
                case ("get"):
                    if (args.length != 2) {
                        log.info("Unknown command");
                        break;
                    }
                    printUser(userService.get(Integer.parseInt(args[1])));
                    break;
                case ("getall"):
                    if (args.length != 1) {
                        log.info("Unknown command");
                        break;
                    }
                    List<User> users = userService.getAll();
                    for (User user : users) {
                        printUser(user);
                    }
                    break;
                case ("add"):
                    if (args.length != 5) {
                        log.info("Unknown command");
                        break;
                    }
                    userService.create(new User(Integer.parseInt(args[1]), args[2], args[3], args[4]));
                    break;
                case ("remove"):
                    if (args.length != 2) {
                        log.info("Unknown command");
                        break;
                    }
                    userService.delete(Integer.parseInt(args[1]));
                    break;
                case ("set"):
                    if (args.length != 5) {
                        log.info("Unknown command");
                        break;
                    }
                    userService.update(new User(Integer.parseInt(args[1]), args[2], args[3], args[4]));
                    break;
                case ("stop"):
                    flag = false;
                    break;
                default:
                    log.info("Unknown command");
            }
        }
    }

    private void printUser(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ");
        stringBuilder.append(user.getId());
        stringBuilder.append(" name: ");
        stringBuilder.append(user.getName());
        stringBuilder.append(" password: ");
        stringBuilder.append(user.getPassword());
        stringBuilder.append(" email: ");
        stringBuilder.append(user.getEmail());
        log.info(stringBuilder.toString());
    }

}
