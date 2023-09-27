package com.mjc.school.controller.command.implementation.authorCommands;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetAuthorById implements Command {
    @Autowired
    private AuthorController authorController;
    private final String name = "Get Author by ID";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ID:");
        Long id = Long.parseLong(in.nextLine());
        System.out.println(authorController.readById(id).toString());
    }
}
