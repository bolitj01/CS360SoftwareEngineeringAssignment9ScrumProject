package HealthScreener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreeningSelectionWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScreeningSelectionWindow(HealthScreenerGUI healthScreenerGUI, String[] fileNames){
		UIManager.put("Panel.background", HealthScreenerGUI.backColor);
		UIManager.put("List.background", Color.white);
//		UIManager.put("Label.background", HealthScreenerGUI.backColor);
		
		int width = 200;
		int height = 250;
		
		healthScreenerGUI.disableFields();
		
		setUndecorated(true);
		setSize(width, height);
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		
		JPanel titlePanel = new JPanel();
		
		JLabel titleLabel = new JLabel("Select a Past Screening");
		titleLabel.setPreferredSize(new Dimension(width, height / 5));
		
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		JList<String> fileList = new JList<String>(fileNames);
		fileList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		fileList.setPreferredSize(new Dimension(width, height * 3/5));
		
		mainPanel.add(fileList);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileList.getSelectedValue() == null){
					JOptionPane.showMessageDialog(null, "Please choose a health screening from the list.", "Load Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultsFileReader rfr = new ResultsFileReader();
				ScreenResults screenResults = rfr.readScreenResults(fileList.getSelectedValue() + ".ser");
				screenResults.generateReport();
				rfr.closeStreams();
				healthScreenerGUI.enableFields();
				dispose();
			}
		});
		
		mainPanel.add(loadButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				healthScreenerGUI.enableFields();
				dispose();
			}
		});
		
		mainPanel.add(cancelButton);
		
		add(mainPanel, BorderLayout.CENTER);
		
		revalidate();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
