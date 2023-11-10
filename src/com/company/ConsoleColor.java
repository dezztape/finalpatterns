package com.company;

interface ConsoleColor {
    String ANSI_RESET = "\033[0m";
    String ANSI_RED = "\033[31m";
    String ANSI_GREEN = "\033[32m";
    String ANSI_YELLOW = "\033[33m";
    String ANSI_BLUE = "\033[34m";
    String ANSI_MAGENTA = "\033[35m";
    String ANSI_CYAN = "\033[36m";

    void setColor(String color);
    void resetColor();
}