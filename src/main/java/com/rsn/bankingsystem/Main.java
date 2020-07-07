package com.rsn.bankingsystem;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> paramToValue = readArgs(args);

        DBConnection dbConnection = new DBConnection(paramToValue.get("fileName"));

        AppState state = new AppState(dbConnection);
        MainMenuHandler mainMenuHandler = new MainMenuHandler(state);
        LoginMenuHandler loginMenuHandler = new LoginMenuHandler(state);

        while (true) {
            if (state.isLoggedIn()) {
                loginMenuHandler.start();
            } else {
                mainMenuHandler.start();
            }
        }
    }

    static Map<String, String> readArgs(String... args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i].substring(1), args[i + 1]);
        }
        return map;
    }
}
