package com.company;

interface TargetConsoleColor {
    String ANSI_MAGENTA = "\033[35m";

    void setForegroundColor(String color);
    void resetForegroundColor();
}