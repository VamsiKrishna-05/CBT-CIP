import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private static final String URL = "jdbc:mysql://localhost:3306/banky"; // Your DB URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "Vamsi@26"; // Your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static BankAccount createAccount(String name, String pin, double initialDeposit) throws SQLException {
        String sql = "INSERT INTO accounts (name, pin, balance) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, name);
            pst.setString(2, pin);
            pst.setDouble(3, initialDeposit);
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int accountNumber = rs.getInt(1);
                return new BankAccount(accountNumber, name, pin, initialDeposit);
            }
        }
        throw new SQLException("Failed to create account.");
    }

    public static void deposit(int accountNumber, double amount) throws SQLException {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit must be positive.");
        updateBalance(accountNumber, amount);
    }

    public static void withdraw(int accountNumber, double amount) throws SQLException {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal must be positive.");
        double balance = getBalance(accountNumber);
        if (balance < amount)
            throw new IllegalArgumentException("Insufficient balance.");
        updateBalance(accountNumber, -amount);
    }

    public static void transfer(int fromAccount, int toAccount, double amount) throws SQLException {
        if (amount <= 0)
            throw new IllegalArgumentException("Transfer must be positive.");
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            con.setAutoCommit(false);
            double fromBalance = getBalance(fromAccount, con);
            if (fromBalance < amount) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            updateBalance(fromAccount, -amount, con);
            updateBalance(toAccount, amount, con);
            con.commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public static double getBalance(int accountNumber) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return getBalance(accountNumber, con);
        }
    }

    private static double getBalance(int accountNumber, Connection con) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, accountNumber);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                throw new IllegalArgumentException("Account not found.");
            }
        }
    }

    private static void updateBalance(int accountNumber, double amount) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            updateBalance(accountNumber, amount, con);
        }
    }

    private static void updateBalance(int accountNumber, double amount, Connection con) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDouble(1, amount);
            pst.setInt(2, accountNumber);
            int rows = pst.executeUpdate();
            if (rows == 0)
                throw new IllegalArgumentException("Account not found.");
        }
    }

    public static List<BankAccount> getAllAccounts() {
        List<BankAccount> list = new ArrayList<>();
        String sql = "SELECT account_number, name, pin, balance FROM accounts";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                BankAccount acc = new BankAccount(
                        rs.getInt("account_number"),
                        rs.getString("name"),
                        rs.getString("pin"),
                        rs.getDouble("balance"));
                list.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
