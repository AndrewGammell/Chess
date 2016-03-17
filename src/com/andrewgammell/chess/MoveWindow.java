package com.andrewgammell.chess;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MoveWindow extends JFrame {
	
	private String from;
	private String to;
	private JPanel contentPane;
	private JTextField moveFromField;
	private JTextField moveToField;
	

	/**
	 * Launch the application.
	 */
	public void displayMoveWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoveWindow frame = new MoveWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MoveWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		moveFromField = new JTextField();
		moveFromField.setBounds(10, 26, 86, 20);
		contentPane.add(moveFromField);
		moveFromField.setColumns(10);

		moveToField = new JTextField();
		moveToField.setBounds(127, 26, 86, 20);
		contentPane.add(moveToField);
		moveToField.setColumns(10);

		JButton MoveBtn = new JButton("Move");
		MoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				from = moveFromField.getText();
				int i = Integer.parseInt(String.valueOf(from.charAt(0)));
				int j = Integer.parseInt(String.valueOf(from.charAt(1))); 
				to = moveToField.getText();
				int k = Integer.parseInt(String.valueOf(to.charAt(0)));
				int l = Integer.parseInt(String.valueOf(to.charAt(1)));
				
					MainApp.board.movePiece(i,j,k,l);
			}
		});
		MoveBtn.setBounds(300, 25, 89, 23);
		contentPane.add(MoveBtn);
	}

}
