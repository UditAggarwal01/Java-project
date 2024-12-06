import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagementSystem extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInputField;

    public TaskManagementSystem() {
        setTitle("Task Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setBackground(Color.LIGHT_GRAY);
        taskList.setSelectionBackground(Color.BLUE);
        taskList.setSelectionForeground(Color.WHITE);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));

        taskInputField = new JTextField();
        taskInputField.setBackground(Color.WHITE);
        taskInputField.setForeground(Color.BLACK);
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        taskInputField.setBorder(BorderFactory.createTitledBorder("Enter Task"));

        JButton addButton = createButton("Add Task");
        JButton removeButton = createButton("Remove Task");
        JButton clearButton = createButton("Clear Tasks");
        JButton completeButton = createButton("Mark as Completed");
        JButton viewAllButton = createButton("View All Tasks");

        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        clearButton.addActionListener(e -> taskListModel.clear());
        completeButton.addActionListener(e -> markAsCompleted());
        viewAllButton.addActionListener(e -> viewAllTasks());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10)); 
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(viewAllButton);

        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(taskInputField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.CYAN);
        button.setPreferredSize(new Dimension(150, 50)); 
        return button;
    }

    private void addTask() {
        String task = taskInputField.getText();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInputField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a task.");
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a task to remove.");
        }
    }

    private void markAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = taskListModel.get(selectedIndex);
            taskListModel.set(selectedIndex, task + " (Completed)");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a task to mark as completed.");
        }
    }

    private void viewAllTasks() {
        StringBuilder allTasks = new StringBuilder("All Tasks:\n");
        for (int i = 0; i < taskListModel.size(); i++) {
            allTasks.append(taskListModel.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, allTasks.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManagementSystem taskManagementSystem = new TaskManagementSystem();
            taskManagementSystem.setVisible(true);
        });
    }
}