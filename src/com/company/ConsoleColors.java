package com.company;
class ConsoleColors implements ConsoleColor {
    @Override
    public void setColor(String color) {
        System.out.print(color);
    }

    @Override
    public void resetColor() {
        System.out.print(ANSI_RESET);
    }
}