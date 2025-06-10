import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BankYGUI extends JFrame {

    private JTextArea displayArea;

    public BankYGUI() {
        setTitle("BankY - Simple Banking GUI");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Background panel
        JLabel background = new JLabel(new ImageIcon("background.png"));
        setContentPane(background);
        setLayout(new BorderLayout());

        // Semi-transparent panel
        JPanel glassPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 90));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        glassPanel.setOpaque(false);
        add(glassPanel);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton createBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton balanceBtn = new JButton("Check Balance");
        JButton listBtn = new JButton("List Accounts");

        createBtn.addActionListener(this::handleCreate);
        depositBtn.addActionListener(this::handleDeposit);
        withdrawBtn.addActionListener(this::handleWithdraw);
        transferBtn.addActionListener(this::handleTransfer);
        balanceBtn.addActionListener(this::handleBalance);
        listBtn.addActionListener(e -> listAccounts());

        for (JButton btn : new JButton[] { createBtn, depositBtn, withdrawBtn, transferBtn, balanceBtn, listBtn }) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        buttonPanel.add(createBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(listBtn);

        glassPanel.add(buttonPanel, BorderLayout.NORTH);
        glassPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void handleCreate(ActionEvent e) {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter Name:");
            if (name == null || name.trim().isEmpty())
                return;

            String pin = JOptionPane.showInputDialog(this, "Enter PIN:");
            if (pin == null || pin.trim().isEmpty())
                return;

            String depositStr = JOptionPane.showInputDialog(this, "Initial Deposit:");
            if (depositStr == null)
                return;
            double deposit = Double.parseDouble(depositStr);

            BankAccount acc = BankService.createAccount(name, pin, deposit);
            displayArea.append("✅ Account created! Account Number: " + acc.getAccountNumber() + "\n");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void handleDeposit(ActionEvent e) {
        try {
            int accNum = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Deposit Amount:"));
            BankService.deposit(accNum, amount);
            displayArea.append("💰 Deposit successful.\n");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void handleWithdraw(ActionEvent e) {
        try {
            int accNum = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Withdraw Amount:"));
            BankService.withdraw(accNum, amount);
            displayArea.append("💸 Withdrawal successful.\n");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void handleTransfer(ActionEvent e) {
        try {
            int fromAcc = Integer.parseInt(JOptionPane.showInputDialog(this, "From Account Number:"));
            int toAcc = Integer.parseInt(JOptionPane.showInputDialog(this, "To Account Number:"));
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Amount to transfer:"));
            BankService.transfer(fromAcc, toAcc, amount);
            displayArea.append("🔁 Transfer successful.\n");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void handleBalance(ActionEvent e) {
        try {
            int accNum = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Account Number:"));
            double balance = BankService.getBalance(accNum);
            displayArea.append("📊 Balance: ₹" + balance + "\n");
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void listAccounts() {
        List<BankAccount> accounts = BankService.getAllAccounts();
        displayArea.append("\n=== All Accounts ===\n");
        displayArea.append("Account No. | Name               | Balance\n");
        displayArea.append("------------------------------------------\n");
        for (BankAccount acc : accounts) {
            displayArea.append(String.format("%11d | %-18s | ₹%.2f%n",
                    acc.getAccountNumber(), acc.getName(), acc.getBalance()));
        }
        displayArea.append("\n");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankYGUI gui = new BankYGUI();
            gui.setVisible(true);
        });
    }
}
