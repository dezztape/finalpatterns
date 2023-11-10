package com.company;

class ConsoleColorAdapter implements TargetConsoleColor {
    private final ConsoleColor consoleColor;

    public ConsoleColorAdapter(ConsoleColor consoleColor) {
        this.consoleColor = consoleColor;
    }

    @Override
    public void setForegroundColor(String color) {
        consoleColor.setColor(color);
    }

    @Override
    public void resetForegroundColor() {
        consoleColor.resetColor();
    }
}