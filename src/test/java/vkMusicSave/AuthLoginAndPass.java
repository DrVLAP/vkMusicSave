package vkMusicSave;

import java.util.Scanner;

/**
 * Created by First on 13.08.2016.
 */
public class AuthLoginAndPass {

    private String login = "dr.vlap@gmail.com";
    private String password = "fre23l11vlap4";

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public void setAuthLoginAndPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set account login: ");
        login = scanner.nextLine();
        System.out.println("Set account password: ");
        password = scanner.nextLine();
    }
}
