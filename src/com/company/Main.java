package com.company;
import Decorator.PopcornDecorator;
import Decorator.ThreeDDecorator;
import FactoryMethod.StandardTicketFactory;
import FactoryMethod.TicketFactory;
import FactoryMethod.VipTicketFactory;
import Singleton.DatabaseConnectionManager;
import Strategy.BankTransferPaymentStrategy;
import Strategy.CreditCardPaymentStrategy;
import Strategy.CryptoPaymentStrategy;
import Strategy.PaymentStrategy;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Alisher_2003";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private DatabaseConnectionManager connectionManager; // Singleton
    private static TicketFactory ticketFactory; // Factory

    public Main() throws SQLException {
        connectionManager = DatabaseConnectionManager.getInstance(); // Получаем экземпляр Singleton
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main(); // Создаем экземпляр класса Main
        main.run(); // Вызываем метод run для выполнения функциональности
    }

    public void run() throws Exception {
        Connection connection = connectionManager.getConnection();
        ConsoleColor console = new ConsoleColors();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        ConsoleColor consoleColor = new ConsoleColors(); // замените на вашу реализацию ConsoleColor
        TargetConsoleColor targetConsoleColor = new ConsoleColorAdapter(consoleColor);

        // Теперь вы можете использовать targetConsoleColor в вашем коде
        targetConsoleColor.setForegroundColor(TargetConsoleColor.ANSI_MAGENTA);
        System.out.println("Hello, you have the following available functions:");
        targetConsoleColor.resetForegroundColor();
        while (choice != 9) {
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("1) Add a new movie");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("2) Add a new user");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("3) Show all available movies");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("4) Show all available users");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("5) Buy a ticket");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("6) Ticket refund");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("7) Add a new session");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("8) Upgrade ticket");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_CYAN);
            System.out.println("9) Exit from app");
            console.resetColor();
            console.setColor(ConsoleColors.ANSI_MAGENTA);
            System.out.print("Enter a number to select a function: ");
            console.resetColor();

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the movie name: ");
                    console.resetColor();
                    String movieName = scanner.next();
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the movie genre: ");
                    console.resetColor();
                    String movieGenre = scanner.next();
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the age restriction for the movie: ");
                    console.resetColor();
                    int ageRestriction = scanner.nextInt();
                    Movie movie1 = new Movie(movieName, movieGenre, ageRestriction);
                    String insertSQL = "INSERT INTO movies (name, genre, ageRestriction) VALUES (?, ?, ?)";
                    PreparedStatement PreparedStatementMovie = connection.prepareStatement(insertSQL);
                    PreparedStatementMovie.setString(1, movie1.getName());
                    PreparedStatementMovie.setString(2, movie1.getGenre());
                    PreparedStatementMovie.setInt(3, movie1.getAgeRestriction());
                    PreparedStatementMovie.executeUpdate();
                    console.setColor(ConsoleColors.ANSI_GREEN);
                    System.out.println("Movie added successfully.");
                    console.resetColor();
                    break;
                case 2:
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the user name: ");
                    console.resetColor();
                    String userName = scanner.next();
                    int userAge = 0;
                    while (true) {
                        console.setColor(ConsoleColors.ANSI_MAGENTA);
                        System.out.print("Enter the user age: ");
                        console.resetColor();
                        userAge = scanner.nextInt();
                        break;
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the user balance: ");
                    console.resetColor();
                    double userBalance = scanner.nextDouble();
                    User user = new User(userName, userAge, userBalance);
                    insertSQL = "INSERT INTO users (name, age, balance) VALUES (?, ?, ?)";
                    PreparedStatement PreparedStatementTicket = connection.prepareStatement(insertSQL);
                    PreparedStatementTicket.setString(1, user.getName());
                    PreparedStatementTicket.setInt(2, user.getAge());
                    PreparedStatementTicket.setDouble(3, user.getBalance());
                    PreparedStatementTicket.executeUpdate();
                    break;
                case 3:
                    Statement statement = connection.createStatement();
                    String selectSQL = "select * from movies";
                    ResultSet MovieResultSet = statement.executeQuery(selectSQL);
                    while(MovieResultSet.next()){
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(MovieResultSet.getInt("MovieIndex") + ") " + "Movie name: " + MovieResultSet.getString("Name") + " "
                                + "Genre: " + MovieResultSet.getString("Genre") + " " + "Age Restriction: " + MovieResultSet.getInt("AgeRestriction"));
                        console.resetColor();
                    }
                    break;
                case 4:
                    statement = connection.createStatement();
                    selectSQL = "select * from users";
                    ResultSet UserResultSet = statement.executeQuery(selectSQL);
                    while(UserResultSet.next()) {
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(UserResultSet.getInt("UserIndex") + ") " + "User name: "+UserResultSet.getString("Name") + " "
                                + "User Age: " + UserResultSet.getInt("Age") + " " + "User balance: " +UserResultSet.getFloat("Balance"));
                        console.resetColor();
                    }
                    break;
                case 5:
                    statement = connection.createStatement();
                    selectSQL = "select * from sessions";
                    ResultSet SessionResultSet = statement.executeQuery(selectSQL);
                    while(SessionResultSet.next()){
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(SessionResultSet.getInt("id") + ") " + "Movie name: " + SessionResultSet.getString("movie") + " "
                                + "session date: " + SessionResultSet.getString("sessionDate") + " " + "session time: " + SessionResultSet.getString("sessionTime")+ " "
                                + "price: " + SessionResultSet.getFloat("ticketPrice") + " " + "age restriction: " + SessionResultSet.getInt("ageRestriction") + " "
                                + "amount of tickets:" + SessionResultSet.getInt("amountTickets"));
                        console.resetColor();
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the number of the session you want to buy a ticket for: ");
                    console.resetColor();
                    int selectedSessionIndex = scanner.nextInt();
                    selectSQL = "SELECT * FROM sessions WHERE id =" + selectedSessionIndex;
                    ResultSet sessionInfoResultSet = statement.executeQuery(selectSQL);
                    int id = 0;
                    String selectedMovie = "";
                    int selectedAgeRestriction = 0;
                    float selectedTicketPrice = 0;
                    String sessionDate = "";
                    String sessionTime = "";
                    int amountTickets = 0;
                    while (sessionInfoResultSet.next()) {
                        selectedMovie = sessionInfoResultSet.getString("movie");
                        selectedAgeRestriction = sessionInfoResultSet.getInt("ageRestriction");
                        selectedTicketPrice = sessionInfoResultSet.getFloat("ticketPrice");
                        sessionDate = sessionInfoResultSet.getString("sessionDate");
                        sessionTime = sessionInfoResultSet.getString("sessionTime");
                        amountTickets = sessionInfoResultSet.getInt("amountTickets");
                    }
                    if(amountTickets == 0){
                        console.setColor(ConsoleColors.ANSI_RED);
                        System.out.println("Sorry, but the tickets are out. Try choosing another session");
                        console.resetColor();
                        break;
                    }
                    statement = connection.createStatement();
                    selectSQL = "select * from users";
                    UserResultSet = statement.executeQuery(selectSQL);
                    while(UserResultSet.next()) {
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(UserResultSet.getInt("UserIndex") + ") " + "User name: "+UserResultSet.getString("Name") + " "
                                + "User Age: " + UserResultSet.getInt("Age") + " " + "User balance: " +UserResultSet.getFloat("Balance"));
                        console.resetColor();
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the number of the user who is purchasing the ticket: ");
                    console.resetColor();
                    int selectedUserIndex = scanner.nextInt();
                    selectSQL = "select * from users where UserIndex =" + selectedUserIndex;
                    UserResultSet = statement.executeQuery(selectSQL);
                    userName = "";
                    userBalance = 0.0;
                    userAge = 0;
                    while (UserResultSet.next()) {
                        userName = UserResultSet.getString("name");
                        userBalance = UserResultSet.getInt("Balance");
                        userAge = UserResultSet.getInt("Age");
                    }

                    LocalDate ticketLocalDate = LocalDate.parse(sessionDate);
                    LocalTime ticketLocalTime = LocalTime.parse(sessionTime);

                    if (ticketLocalDate.isBefore(LocalDate.now())){
                        console.setColor(ConsoleColors.ANSI_RED);
                        System.out.println("Ticket cannot be bought. Showtime has already passed");
                        console.resetColor();
                    } else {
                        if (ticketLocalTime.isBefore(LocalTime.now())) {
                            console.setColor(ConsoleColors.ANSI_RED);
                            System.out.println("Ticket cannot be bought. Showtime has already passed");
                            console.resetColor();
                        } else {
                            if (userAge >= selectedAgeRestriction && userBalance >= selectedTicketPrice) {
                                String sql = "update users set balance = ? where UserIndex =" + selectedUserIndex;
                                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                preparedStatement.setDouble(1, userBalance - selectedTicketPrice);
                                preparedStatement.executeUpdate();
                                String sqlsession = "update sessions set amountTickets = ? where id =" + selectedSessionIndex;
                                preparedStatement = connection.prepareStatement(sqlsession);
                                preparedStatement.setInt(1, amountTickets-1);
                                preparedStatement.executeUpdate();

                                String ticketSQL = "INSERT INTO Tickets (username, movie, date, time, ticketPrice, status) VALUES (?, ?, ?, ?, ?, ?)";
                                PreparedStatementTicket = connection.prepareStatement(ticketSQL);
                                PreparedStatementTicket.setString(1, userName);
                                PreparedStatementTicket.setString(2, selectedMovie);
                                PreparedStatementTicket.setString(3, sessionDate);
                                PreparedStatementTicket.setString(4, sessionTime);
                                PreparedStatementTicket.setDouble(5, selectedTicketPrice);
                                PreparedStatementTicket.setString(6, "confirmed");
                                PreparedStatementTicket.executeUpdate();
                            } else if (selectedTicketPrice > userBalance) {
                                console.setColor(ConsoleColors.ANSI_RED);
                                System.out.println("You don’t have enough money");
                                console.resetColor();
                            } else if (selectedAgeRestriction > userAge){
                                console.setColor(ConsoleColors.ANSI_RED);
                                System.out.println("We can’t sell you tickets because of age restrictions.");
                                console.resetColor();
                            }
                        }
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the type of ticket (1 for Standard, 2 for VIP): ");
                    console.resetColor();
                    int ticketType = scanner.nextInt();

                    TicketFactory ticketFactory;

                    switch (ticketType) {
                        case 1:
                            ticketFactory = new StandardTicketFactory();
                            break;
                        case 2:
                            ticketFactory = new VipTicketFactory();
                            break;
                        default:
                            console.setColor(ConsoleColors.ANSI_RED);
                            System.out.println("Invalid ticket type selected.");
                            console.resetColor();
                            return;
                    }
                    Ticket ticket = ticketFactory.createTicket(id, selectedMovie, sessionDate, sessionTime, selectedTicketPrice);

                    console.setColor(ConsoleColors.ANSI_MAGENTA); // Strategy
                    System.out.println("Choose payment method:");
                    System.out.println("1) Credit Card");
                    System.out.println("2) Bank Transfer");
                    System.out.println("3) Cryptocurrency");
                    console.resetColor();

                    int paymentMethod = scanner.nextInt();
                    PaymentStrategy paymentStrategy = null;

                    switch (paymentMethod) {
                        case 1:
                            paymentStrategy = new CreditCardPaymentStrategy();
                            break;
                        case 2:
                            paymentStrategy = new BankTransferPaymentStrategy();
                            break;
                        case 3:
                            paymentStrategy = new CryptoPaymentStrategy();
                            break;
                        default:
                            console.setColor(ConsoleColors.ANSI_RED);
                            System.out.println("Invalid payment method selected.");
                            console.resetColor();
                            break;
                    }
                    ticket = new Ticket(id, selectedMovie, sessionDate, sessionTime, selectedTicketPrice);

                    if (paymentStrategy != null) {
                        ticket.setPaymentStrategy(paymentStrategy);
                        ticket.payForTicket();
                    }

                    console.setColor(ConsoleColors.ANSI_GREEN);
                    System.out.println("Ticket successful sold");
                    console.resetColor();

                    break;
                case 6:
                    statement = connection.createStatement();
                    selectSQL = "select * from tickets where status = 'confirmed'";
                    ResultSet ticketsResultSet = statement.executeQuery(selectSQL);
                    while (ticketsResultSet.next()) {
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(ticketsResultSet.getInt("id") + ") " + "User name: " + ticketsResultSet.getString("username") +
                                "Movie name: " + ticketsResultSet.getString("movie") + "Date: " + ticketsResultSet.getString("date") +
                                "Time: " + ticketsResultSet.getString("Time") + "Ticket price: " + ticketsResultSet.getFloat("ticketprice"));
                        console.resetColor();
                    }

                    System.out.print("Enter the ID of the ticket you want to cancel: ");
                    int ticketId = scanner.nextInt();

                    String selectTicketSQL = "select * from tickets where id=?";
                    PreparedStatement selectTicketStatement = connection.prepareStatement(selectTicketSQL);
                    selectTicketStatement.setInt(1, ticketId);
                    ResultSet ticketResultSet = selectTicketStatement.executeQuery();
                    double ticketPrice = 0.0;
                    String username = "";
                    String movie = "";
                    sessionDate = "";
                    sessionTime = "";
                    if (ticketResultSet.next()) {
                        ticketPrice = ticketResultSet.getFloat("ticketprice");
                        username = ticketResultSet.getString("username");
                        movie = ticketResultSet.getString("movie");
                        sessionDate = ticketResultSet.getString("date");
                        sessionTime = ticketResultSet.getString("time");
                    }
                    String updateSQL = "update tickets set status='cancel' where id=?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
                    updateStatement.setInt(1, ticketId);
                    updateStatement.executeUpdate();

                    String selectUserSQL = "select * from users where name=?";
                    PreparedStatement selectUserStatement = connection.prepareStatement(selectUserSQL);
                    selectUserStatement.setString(1, username);
                    ResultSet userResultSet = selectUserStatement.executeQuery();
                    double balance = 0.0;
                    if(userResultSet.next()){
                        balance = userResultSet.getDouble("balance");
                    }

                    String updateUserSQL = "update users set balance = ? where name=?";
                    PreparedStatement updateUserStatement = connection.prepareStatement(updateUserSQL);
                    updateUserStatement.setDouble(1, balance + ticketPrice);
                    updateUserStatement.setString(2, username);
                    updateUserStatement.executeUpdate();

                    String selectSessionSQL = "select * from sessions where movie=? and sessiondate=? and sessiontime=? and ticketprice=?";
                    PreparedStatement selectSessionStatement = connection.prepareStatement(selectSessionSQL);
                    selectSessionStatement.setString(1, movie);
                    selectSessionStatement.setString(2, sessionDate);
                    selectSessionStatement.setString(3, sessionTime);
                    selectSessionStatement.setDouble(4, ticketPrice);
                    ResultSet sessionResultSet = selectSessionStatement.executeQuery();
                    amountTickets = 0;
                    if(sessionResultSet.next()){
                        amountTickets = sessionResultSet.getInt("amountTickets");
                    }

                    String updateSessionSQL = "update sessions set amountTickets = ? where movie=? and sessiondate=? and sessiontime=? and ticketprice=?";
                    PreparedStatement updateSessionStatement = connection.prepareStatement(updateSessionSQL);
                    updateSessionStatement.setInt(1, amountTickets + 1);
                    updateSessionStatement.setString(2, movie);
                    updateSessionStatement.setString(3, sessionDate);
                    updateSessionStatement.setString(4, sessionTime);
                    updateSessionStatement.setDouble(5, ticketPrice);
                    updateSessionStatement.executeUpdate();

                    System.out.println("OK!");

                    break;
                case 7:
                    statement = connection.createStatement();
                    selectSQL = "select * from movies";
                    MovieResultSet = statement.executeQuery(selectSQL);
                    while(MovieResultSet.next()){
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(MovieResultSet.getInt("MovieIndex") + ") " + "Movie name: " + MovieResultSet.getString("Name") + " "
                                + "Genre: " + MovieResultSet.getString("Genre") + " " + "Age Restriction: " + MovieResultSet.getInt("AgeRestriction"));
                        console.resetColor();
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the movie index for which you want to add a new session: ");
                    console.resetColor();
                    int movieIndex = scanner.nextInt();
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the session date (YYYY-MM-DD): ");
                    console.resetColor();
                    String dateString = scanner.next().substring(0, 10);
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the session time (HH:MM:SS): ");
                    console.resetColor();
                    String timeString = scanner.next().substring(0, 8);
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the ticket price for the session: ");
                    console.resetColor();
                    ticketPrice = scanner.nextDouble();
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.println("Enter amount of tickets: ");
                    console.resetColor();
                    amountTickets = scanner.nextInt();
                    String selectMovieSQL = "SELECT * FROM movies WHERE movieIndex = ?";
                    PreparedStatement selectMovieStatement = connection.prepareStatement(selectMovieSQL);
                    selectMovieStatement.setInt(1, movieIndex);
                    ResultSet movieResultSet = selectMovieStatement.executeQuery();
                    if (movieResultSet.next()) {
                        movieName = movieResultSet.getString("name");
                        ageRestriction = movieResultSet.getInt("ageRestriction");
                        String insertSessionSQL = "INSERT INTO sessions (movie, sessionDate, sessionTime, ticketPrice, ageRestriction, amountTickets) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement insertSessionStatement = connection.prepareStatement(insertSessionSQL);
                        insertSessionStatement.setString(1, movieName);
                        insertSessionStatement.setString(2, dateString);
                        insertSessionStatement.setString(3, timeString);
                        insertSessionStatement.setDouble(4, ticketPrice);
                        insertSessionStatement.setInt(5, ageRestriction);
                        insertSessionStatement.setInt(6, amountTickets);
                        insertSessionStatement.executeUpdate();
                        console.setColor(ConsoleColors.ANSI_GREEN);
                        System.out.println("New session added successfully.");
                        console.resetColor();
                    } else {
                        console.setColor(ConsoleColors.ANSI_RED);
                        System.out.println("Movie with index " + movieIndex + " not found.");
                        console.resetColor();
                    }
                    break;
                case 8: // Decorator
                    statement = connection.createStatement();
                    selectSQL = "select * from tickets where status = 'confirmed'";
                    ticketsResultSet = statement.executeQuery(selectSQL);
                    while (ticketsResultSet.next()) {
                        console.setColor(ConsoleColors.ANSI_BLUE);
                        System.out.println(ticketsResultSet.getInt("id") + ") " + "User name: " + ticketsResultSet.getString("username") +
                                "Movie name: " + ticketsResultSet.getString("movie") + "Date: " + ticketsResultSet.getString("date") +
                                "Time: " + ticketsResultSet.getString("Time") + "Ticket price: " + ticketsResultSet.getFloat("ticketprice"));
                        console.resetColor();
                    }
                    console.setColor(ConsoleColors.ANSI_MAGENTA);
                    System.out.print("Enter the ID of the ticket you want to upgrade: ");
                    console.resetColor();
                    int ticketToUpgradeId = scanner.nextInt();

                    selectTicketSQL = "select * from tickets where id=?";
                    selectTicketStatement = connection.prepareStatement(selectTicketSQL);
                    selectTicketStatement.setInt(1, ticketToUpgradeId);
                    ticketResultSet = selectTicketStatement.executeQuery();

                    if (ticketResultSet.next()) {
                        double ticketToUpgradePrice = ticketResultSet.getDouble("ticketPrice");
                        Ticket existingTicket = new Ticket(0, "", "", "", ticketToUpgradePrice);


                        console.setColor(ConsoleColors.ANSI_MAGENTA);
                        System.out.print("Choose the option to upgrade (1 for Popcorn, 2 for 3D): ");
                        console.resetColor();
                        int selectedUpgradeOption = scanner.nextInt();

                        switch (selectedUpgradeOption) {
                            case 1:
                                // Добавляем опцию "Popcorn" к существующему билету
                                PopcornDecorator popcornUpgradedTicket = new PopcornDecorator(existingTicket);
                                popcornUpgradedTicket.addOption();  // Выведет: Added Popcorn to the ticket.
                                break;
                            case 2:
                                // Добавляем опцию "3D" к существующему билету
                                ThreeDDecorator upgradedTicket = new ThreeDDecorator(existingTicket);
                                upgradedTicket.addOption();  // Выведет: Upgraded to 3D.
                                break;
                            default:
                                console.setColor(ConsoleColors.ANSI_RED);
                                System.out.println("Invalid upgrade option selected.");
                                console.resetColor();
                                break;
                        }
                    } else {
                        console.setColor(ConsoleColors.ANSI_RED);
                        System.out.println("Ticket with ID " + ticketToUpgradeId + " not found.");
                        console.resetColor();
                    }
                    ticket = new Ticket(0, "", "", "", 0);
                    Observer ticketObserver = new TicketChangeObserver(ticket);
                    ticket.addObserver(ticketObserver);
                    ticket.somethingChanged();
                    break;
                default:
                    console.setColor(ConsoleColors.ANSI_RED);
                    System.out.println("Invalid selection.");
                    console.resetColor();
                    break;
                case 9:
                    console.setColor(ConsoleColors.ANSI_YELLOW);
                    System.out.println("Goodbye, see you later!");
                    console.resetColor();
                    System.exit(0);
                    break;
            }
        }
    }
}
