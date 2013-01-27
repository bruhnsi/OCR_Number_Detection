package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import Data.DataProvider;
import Data.ImageData;
import NeuralNetwork.Network;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class View {

	private JFrame frame;
	private Network net;
	private final JSeparator separator_1 = new JSeparator();
	private JTextField txtLearningRate;
	private JTextField textField;
	private NewNetworkView addView;
	private JButton btnTrain;
	private JCheckBox chckbxCrossValidation;
	private JLabel lblErrorTrain;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, separator_1, 120, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		this.btnTrain = new JButton("learning");
		btnTrain.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, btnTrain, 10, SpringLayout.WEST, frame.getContentPane());
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnTrain.setEnabled(false);
				txtLearningRate.setEditable(false);
				spinner.setEnabled(false);
				chckbxCrossValidation.setEnabled(false);
				
				JFileChooser fc = new JFileChooser();
				int state = fc.showOpenDialog(null);
				if(state == JFileChooser.APPROVE_OPTION)
				{
					String path = fc.getSelectedFile().getAbsolutePath();
					DataProvider dataProvider = new DataProvider(path);
					ImageData[][] data = dataProvider.getData(7);
					int loopCount = (int)spinner.getValue();
					for(int count = 1; count <= loopCount; count++)
					{
						int sumTrue = 0;
						System.out.println((int) spinner.getValue());
						if(chckbxCrossValidation.isSelected())
						{
							//Crossvalidation
							
						}
						else
						{
							// learning on first 6/7 of data
							for(int i = 0; i < 6; i++)
							{
								for(int j = 0; j < data[i].length; j++)
									net.learn(data[i][j].getGrayValues(), data[i][j].getLabel());
							}
							// testing on last 1/7 of data
							
							for(int i = 0; i < data[6].length; i++ )
							{
								net.setInput(data[6][i]);
								net.passforward();
								if (net.getOutput() == data[6][i].getLabel())
									sumTrue++;
							}
						}
						// calc Error
						float error = 1.0f - (float)sumTrue / (float)data[6].length;
						spinner.setValue(loopCount - count);
						// show Error
						lblErrorTrain.setText("Error: " + error);
					}
					
				}
				btnTrain.setEnabled(false);
				txtLearningRate.setEditable(false);
				spinner.setEnabled(false);
				chckbxCrossValidation.setEnabled(false);
				
			}
		});
		frame.getContentPane().add(btnTrain);
		
		JButton btnTesting = new JButton("testing(against Data)");
		springLayout.putConstraint(SpringLayout.WEST, btnTesting, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnTesting);
		
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.SOUTH, separator_1, 0, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.NORTH, separator, 177, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, separator, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, separator, 179, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(separator);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		springLayout.putConstraint(SpringLayout.SOUTH, btnTrain, -17, SpringLayout.NORTH, separator_3);
		springLayout.putConstraint(SpringLayout.SOUTH, separator_3, -100, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator_3, 0, SpringLayout.EAST, separator);
		frame.getContentPane().add(separator_3);
		
		JLabel lblLearningSection = new JLabel("Learning Section");
		springLayout.putConstraint(SpringLayout.NORTH, lblLearningSection, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLearningSection, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, separator_3, 0, SpringLayout.WEST, lblLearningSection);
		frame.getContentPane().add(lblLearningSection);
		
		txtLearningRate = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, txtLearningRate, -51, SpringLayout.EAST, frame.getContentPane());
		txtLearningRate.setEditable(false);
		frame.getContentPane().add(txtLearningRate);
		txtLearningRate.setColumns(10);
		
		JLabel lblLearningRate = new JLabel("learning rate:");
		springLayout.putConstraint(SpringLayout.NORTH, txtLearningRate, -2, SpringLayout.NORTH, lblLearningRate);
		springLayout.putConstraint(SpringLayout.WEST, lblLearningRate, 29, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblLearningRate, 18, SpringLayout.SOUTH, lblLearningSection);
		frame.getContentPane().add(lblLearningRate);
		
		JLabel lblNumberOfHidden = new JLabel("number of hidden nodes:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfHidden, 20, SpringLayout.SOUTH, lblLearningRate);
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfHidden, 0, SpringLayout.WEST, lblLearningRate);
		frame.getContentPane().add(lblNumberOfHidden);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -2, SpringLayout.NORTH, lblNumberOfHidden);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, txtLearningRate);
		textField.setEditable(false);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		chckbxCrossValidation = new JCheckBox("cross validation");
		springLayout.putConstraint(SpringLayout.WEST, chckbxCrossValidation, 0, SpringLayout.WEST, lblLearningRate);
		frame.getContentPane().add(chckbxCrossValidation);
		
		JLabel lblTestSection = new JLabel("Test Section");
		springLayout.putConstraint(SpringLayout.NORTH, btnTesting, 6, SpringLayout.SOUTH, lblTestSection);
		springLayout.putConstraint(SpringLayout.NORTH, lblTestSection, 11, SpringLayout.SOUTH, separator_3);
		springLayout.putConstraint(SpringLayout.WEST, lblTestSection, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblTestSection);
		
		JButton btnClassifyJpegImage = new JButton("Classify Jpeg Image");
		springLayout.putConstraint(SpringLayout.EAST, btnTesting, -12, SpringLayout.WEST, btnClassifyJpegImage);
		springLayout.putConstraint(SpringLayout.NORTH, btnClassifyJpegImage, 0, SpringLayout.NORTH, btnTesting);
		springLayout.putConstraint(SpringLayout.EAST, btnClassifyJpegImage, 0, SpringLayout.EAST, separator);
		frame.getContentPane().add(btnClassifyJpegImage);
		
		lblErrorTrain = new JLabel("Error:");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorTrain, 5, SpringLayout.NORTH, btnTrain);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorTrain, 34, SpringLayout.EAST, separator_1);
		frame.getContentPane().add(lblErrorTrain);
		
		spinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.NORTH, spinner, 11, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, spinner, 0, SpringLayout.EAST, txtLearningRate);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		frame.getContentPane().add(spinner);
		
		JLabel lblMaxNumberOf = new JLabel("max number of iteration:");
		springLayout.putConstraint(SpringLayout.WEST, spinner, 54, SpringLayout.EAST, lblMaxNumberOf);
		springLayout.putConstraint(SpringLayout.NORTH, chckbxCrossValidation, 8, SpringLayout.SOUTH, lblMaxNumberOf);
		springLayout.putConstraint(SpringLayout.NORTH, lblMaxNumberOf, 16, SpringLayout.SOUTH, lblNumberOfHidden);
		springLayout.putConstraint(SpringLayout.WEST, lblMaxNumberOf, 0, SpringLayout.WEST, lblLearningRate);
		frame.getContentPane().add(lblMaxNumberOf);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		this.addView = new NewNetworkView();
		addView.addCreatListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				net = new Network(Float.parseFloat(addView.getTextFieldLearningRate().getText()),Integer.parseInt(addView.getTextFieldNumberNode().getText()));
				updated();
				addView.setVisible(false);
			}
		});
		
		JMenuItem mntmNewNetwork = new JMenuItem("New Network");
		mntmNewNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addView.setVisible(true);
			}
		});
		mnDatei.add(mntmNewNetwork);
		
		JMenuItem mntmLoadNetwork = new JMenuItem("Load Network");
		mntmLoadNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int state = fc.showOpenDialog(null);
				if(state == JFileChooser.APPROVE_OPTION)
				{
					net = new Network(fc.getSelectedFile().getAbsolutePath());
					updated();
				}
			}
		});
		mnDatei.add(mntmLoadNetwork);
		
		JMenuItem mntmSaveNetwork = new JMenuItem("Save Network");
		mntmSaveNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int state = fc.showSaveDialog(null);
				if(state == JFileChooser.APPROVE_OPTION)
				{
					net.saveToFile(fc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		mnDatei.add(mntmSaveNetwork);
	}
	
	public void updated()
	{
		if (this.net != null)
		{
			txtLearningRate.setText(Float.toString(net.getLearningRate()));
			textField.setText(Integer.toString(net.getNumberHiddenNodes()));
			txtLearningRate.setEditable(true);
			btnTrain.setEnabled(true);
		}
	}
}
