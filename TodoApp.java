package task6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TodoApp {

	private JFrame frame;
	private JTextField taskField;
	private JButton addButton;
	private JButton deleteButton;
	private JList<String> taskList;
	private DefaultListModel<String> listModel;
	
	public TodoApp() {
		frame=new JFrame("To-Do App");
		frame.setBounds(100,100,400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		taskField = new JTextField();
		frame.getContentPane().add(taskField,BorderLayout.NORTH);
		taskField.setColumns(10);
		
		listModel = new DefaultListModel<>();
		taskList = new JList<>(listModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(taskList);
		frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel,BorderLayout.SOUTH);
		
		addButton=new JButton("Add Task");
		panel.add(addButton);
		
		deleteButton = new JButton("Delete Task");
		panel.add(deleteButton);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String task=taskField.getText().trim();
				if(!task.isEmpty()) {
					listModel.addElement(task);
					taskField.setText("");
				}else {
					JOptionPane.showMessageDialog(frame, "Please enter a task","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = taskList.getSelectedIndex();
				if(selectedIndex != -1) {
					listModel.remove(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a task to delete","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				TodoApp window = new TodoApp();
				window.frame.setVisible(true);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
	}
}
