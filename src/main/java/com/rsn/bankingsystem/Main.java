package com.rsn.bankingsystem;

public class Main {

    public static void main(String[] args) {
        AppState state = new AppState();
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
}
