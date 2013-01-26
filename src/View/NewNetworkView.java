package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import NeuralNetwork.Network;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewNetworkView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLearningRate;
	private JTextField textFieldNumberNode;
	private Network net;


	/**
	 * Create the frame.
	 */
	public NewNetworkView(Network net) {
		this.net = net;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblLearningRate = new JLabel("learning rate:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLearningRate, 21, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLearningRate, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblLearningRate);
		
		JLabel lblNumberOfHidden = new JLabel("number of hidden nodes:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfHidden, 16, SpringLayout.SOUTH, lblLearningRate);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNumberOfHidden, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNumberOfHidden);
		
		textFieldLearningRate = new JTextField();
		textFieldLearningRate.setText("0.02");
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldLearningRate, 0, SpringLayout.NORTH, lblLearningRate);
		sl_contentPane.putConstraint(SpringLayout.WEST, textFieldLearningRate, 94, SpringLayout.EAST, lblLearningRate);
		contentPane.add(textFieldLearningRate);
		textFieldLearningRate.setColumns(10);
		
		textFieldNumberNode = new JTextField();
		textFieldNumberNode.setText("20");
		sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldNumberNode, 0, SpringLayout.NORTH, lblNumberOfHidden);
		sl_contentPane.putConstraint(SpringLayout.EAST, textFieldNumberNode, 0, SpringLayout.EAST, textFieldLearningRate);
		contentPane.add(textFieldNumberNode);
		textFieldNumberNode.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				net = new Network(Float.parseFloat(textFieldLearningRate.getText()),Integer.parseInt(textFieldNumberNode.getText()));
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCreate, 28, SpringLayout.SOUTH, textFieldNumberNode);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCreate, 115, SpringLayout.WEST, contentPane);
		contentPane.add(btnCreate);
	}

}
