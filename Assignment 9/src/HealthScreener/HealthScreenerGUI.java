package HealthScreener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HealthScreenerGUI extends JFrame{

	public static final Color backColor = new Color(160, 200, 255);

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JTextField nameFirstTextField;
	private JTextField nameLastTextField;
	private JTextField ageTextField;
	private JTextField heightFeetTextField;
	private JTextField heightInchesTextField;
	private JTextField weightTextField;
	private JTextField cholesterolTextField;
	private JTextField bloodPressureSystolicTextField;
	private JTextField bloodPressureDiastolicTextField;


	private JLabel cholesterolClassificationLabel;
	private JLabel bmiClassificationLabel;
	private JLabel bloodPressureClassificationLabel;
	
	private JButton saveButton;
	private JButton printButton;
	private JButton loadButton;
	private JButton exitButton;

	private boolean heightFeetIsValid;
	//Since inches can be left blank, this must be true initially, 
	//in case user never enters the inches text field
	private boolean heightInchesIsValid = true; 
	private boolean weightIsValid;
	private boolean cholesterolIsValid;
	private boolean systolicIsValid;
	private boolean diastolicIsValid;

	private String name;
	private int age;
	private int height;
	private int heightFeet;
	private double heightInches;
	private int weight;
	private int cholesterol;
	private int systolic;
	private int diastolic;
	private double bmi;
	private String cholesterolClassification;
	private String bmiClassification;
	private String bloodPressureClassification;

	public HealthScreenerGUI(){
		
		HealthScreenerGUI thisGUI = this;
		
		UIManager.put("TextField.background", Color.white);
		
		setUndecorated(true);
		
		ArrayList<Patient> patients = new ArrayList<Patient>();

		Dimension d = new Dimension(400,380);
		setMinimumSize(d);
		//		setMaximumSize(d);

		FlowLayout flowLeft = new FlowLayout(FlowLayout.LEFT);

		setLayout(new GridLayout(9, 1));

		//Intro name section
		JPanel namePanel = new JPanel(flowLeft);
		namePanel.setBackground(backColor);

		JLabel nameHeader = new JLabel("Health Screening for: ");
		namePanel.add(nameHeader);

		nameFirstTextField = new JTextField("First Name", 7);
		namePanel.add(nameFirstTextField);
		
		nameLastTextField = new JTextField("Last Name", 7);
		namePanel.add(nameLastTextField);

		add(namePanel);


		//Date
		JPanel datePanel = new JPanel(flowLeft);
		datePanel.setBackground(backColor);

		Date currentDate = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		JLabel dateLabel = new JLabel("Date: " + df.format(currentDate));
		datePanel.add(dateLabel);

		add(datePanel);

		//Age
		JPanel agePanel = new JPanel(flowLeft);
		agePanel.setBackground(backColor);
		agePanel.setBorder(new EmptyBorder(0,0,0,10));

		JLabel ageHeader = new JLabel("Age: ");
		agePanel.add(ageHeader);

		ageTextField = new JTextField(2);
		agePanel.add(ageTextField);

		add(agePanel);

		//Height
		JPanel heightPanel = new JPanel(flowLeft);
		heightPanel.setBackground(backColor);

		JLabel heightHeader = new JLabel("Height: ");
		heightPanel.add(heightHeader);

		JPanel feetPanel = new JPanel(new BorderLayout());
		feetPanel.setBackground(backColor);
		feetPanel.setBorder(new EmptyBorder(0,0,0,5));

		heightFeetTextField = new JTextField(3);
		heightFeetTextField.setName("heightFeetTextField");
		feetPanel.add(heightFeetTextField, BorderLayout.WEST);

		JLabel feetSymbol = new JLabel("Feet");
		feetSymbol.setBorder(new EmptyBorder(0,4,0,0));
		feetPanel.add(feetSymbol, BorderLayout.EAST);

		heightPanel.add(feetPanel);

		JPanel inchesPanel = new JPanel(new BorderLayout());
		inchesPanel.setBackground(backColor);
		inchesPanel.setBorder(new EmptyBorder(0,0,0,10));

		heightInchesTextField = new JTextField(3);
		heightInchesTextField.setName("heightInchesTextField");
		inchesPanel.add(heightInchesTextField, BorderLayout.WEST);

		JLabel inchesSymbol = new JLabel("Inches");
		inchesSymbol.setBorder(new EmptyBorder(0,4,0,0));
		inchesPanel.add(inchesSymbol, BorderLayout.EAST);

		heightPanel.add(inchesPanel);

		JLabel confirmationLocationPointer = new JLabel(" ");
		heightPanel.add(confirmationLocationPointer);
		
		add(heightPanel);

		//Weight
		JPanel weightPanel = new JPanel(flowLeft);
		weightPanel.setBackground(backColor);

		JLabel weightHeader = new JLabel("Weight: ");
		weightPanel.add(weightHeader);

		weightTextField = new JTextField(3);
		weightTextField.setName("weightTextField");
		weightPanel.add(weightTextField);

		add(weightPanel);

		//Cholesterol
		JPanel cholesterolPanel = new JPanel(flowLeft);
		cholesterolPanel.setBackground(backColor);

		JLabel cholesterolHeader = new JLabel("Total Cholesterol: ");
		cholesterolPanel.add(cholesterolHeader);

		cholesterolTextField = new JTextField(3);
		cholesterolTextField.setName("cholesterolTextField");
		cholesterolPanel.add(cholesterolTextField);

		cholesterolClassificationLabel = new JLabel();
		cholesterolPanel.add(cholesterolClassificationLabel);

		add(cholesterolPanel);

		//BMI
		JPanel bmiPanel = new JPanel(flowLeft);
		bmiPanel.setBackground(backColor);

		JLabel bmiHeader = new JLabel("Body Mass Index: ");
		bmiPanel.add(bmiHeader);

		JLabel bmiLabel = new JLabel();
		bmiPanel.add(bmiLabel);

		bmiClassificationLabel = new JLabel();
		bmiPanel.add(bmiClassificationLabel);

		add(bmiPanel);

		//Blood Pressure
		JPanel bloodPressurePanel = new JPanel(flowLeft);
		bloodPressurePanel.setBackground(backColor);

		JLabel bloodPressureHeader = new JLabel("Blood Pressure: ");
		bloodPressurePanel.add(bloodPressureHeader);

		bloodPressureSystolicTextField = new JTextField(3);
		bloodPressureSystolicTextField.setName("bloodPressureSystolicTextField");
		bloodPressurePanel.add(bloodPressureSystolicTextField);

		JLabel backslash = new JLabel("/");
		bloodPressurePanel.add(backslash);

		bloodPressureDiastolicTextField = new JTextField(3);
		bloodPressureDiastolicTextField.setName("bloodPressureDiastolicTextField");
		bloodPressurePanel.add(bloodPressureDiastolicTextField);

		bloodPressureClassificationLabel = new JLabel();
		bloodPressurePanel.add(bloodPressureClassificationLabel);

		add(bloodPressurePanel);

		//Buttons
		JPanel controlPanel = new JPanel(flowLeft);
		controlPanel.setBackground(backColor);

		saveButton = new JButton("Save");
		controlPanel.add(saveButton);

		printButton = new JButton("Print");
		controlPanel.add(printButton);
		
		loadButton = new JButton("Load");
		controlPanel.add(loadButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (patients.size() != 0){
					LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int year = localDate.getYear();
					int month = localDate.getMonthValue();
					int day = localDate.getDayOfMonth();
					ResultsFileWriter rfw = new ResultsFileWriter(month + "-" + day + "-" + year + ".ser");
					ScreenResults screenResults = new ScreenResults(currentDate, patients);
					rfw.saveScreenResults(screenResults);
					rfw.closeStreams();
				}
				System.exit(0);
			}
		});
		controlPanel.add(exitButton);
		
		add(controlPanel);

		setVisible(true);
		setLocationRelativeTo(null);


		//Add text field and button functionality

		MouseAdapter clearTextFieldOnFirstClickListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField thisTextField = (JTextField)e.getComponent();
				thisTextField.setText("");
				thisTextField.removeMouseListener(this);
			}
		};
		nameFirstTextField.addMouseListener(clearTextFieldOnFirstClickListener);

		nameLastTextField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e){
				JTextField thisTextField = (JTextField)e.getComponent();
				thisTextField.setText("");
				thisTextField.removeFocusListener(this);
			}
		});
		
		ActionListener classificationListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				name = validateAndRetrieveName();
				if (name == null){
					return;
				}
				
				age = validateAndRetrieveAge();
				if (age == -1){
					return;
				}

				heightFeet = validateAndRetrieveHeightFeet();
				if (heightFeet == -1){
					return;
				}
				
				heightInches = validateAndRetrieveHeightInches();
				if (heightInches == -1){
					return;
				}
				
				weight = validateAndRetrieveWeight();
				if (weight == -1){
					return;
				}

				cholesterol = validateAndRetrieveCholesterol();
				if (cholesterol == -1){
					return;
				}	

				systolic = validateAndRetrieveSystolic();
				if (systolic == -1){
					return;
				}
				diastolic = validateAndRetrieveDiastolic();
				if (diastolic == -1){
					return;
				}
				
				boolean successBMI = classifyBMI();
				boolean successCholesterol = classifyCholesterol();
				boolean successBloodPressure = classifyBloodPressure();

				if (successBMI && successCholesterol && successBloodPressure){
					int confirmationResult =JOptionPane.showConfirmDialog(confirmationLocationPointer, "Is this information correct?", 
							"Save Patient Screening", JOptionPane.YES_NO_OPTION);
					if (confirmationResult == JOptionPane.YES_OPTION){
						Patient patient = new Patient(name, df.format(currentDate), age, heightFeet + "' " + heightInches + "\"", 
								weight, bmi, cholesterol, systolic, diastolic, cholesterolClassification, bmiClassification, 
								bloodPressureClassification);
						patients.add(patient);
						clearFields();
						nameFirstTextField.requestFocus();
					}
				}

			}
		};

		saveButton.addActionListener(classificationListener);
		
		ActionListener printListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (patients.size() == 0){
					JOptionPane.showMessageDialog(null,  "No Screenings have been saved today.", "Print Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ScreenResults results = new ScreenResults(currentDate,patients);
				results.generateReport();
			}
		};
		
		printButton.addActionListener(printListener);
		
		ActionListener loadListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultsFileReader rfr = new ResultsFileReader();
				String[] fileNames = rfr.getFileNames(".ser");
				if (fileNames == null || fileNames.length == 0){
					JOptionPane.showMessageDialog(null, "There are no past screenings.", "Load Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (fileNames.length != 0){
					new ScreeningSelectionWindow(thisGUI, fileNames);
				}
				rfr.closeStreams();
			}
		};
		
		loadButton.addActionListener(loadListener);
		
		
		
		
	}
	
	private String validateAndRetrieveName(){
		String firstName = nameFirstTextField.getText();
		if (firstName == null || firstName.trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please enter a first name.", "Name Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		String lastName = nameLastTextField.getText();
		if (firstName == null || firstName.trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please enter a last name.", "Name Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return firstName + " " + lastName;
	}
	
	private int validateAndRetrieveAge(){
		int age;
		try{
			String ageString = ageTextField.getText();
			if (ageString != null && !ageString.isEmpty()){
				ageString = ageString.trim();
			} else{
				throw new NumberFormatException();
			}

			age = Integer.parseInt(ageString);
			if (age < 1 || age > 110){
				throw new NumberFormatException();
			}

		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter age as an integer from 1 to 110.", "Age Error", JOptionPane.ERROR_MESSAGE);
			age = -1;
		}

		return age;
	}

	private int validateAndRetrieveCholesterol(){
		int cholesterol;
		try {
			String cholesterolString = cholesterolTextField.getText();
			if (cholesterolString != null && !cholesterolString.isEmpty()){
				cholesterolString = cholesterolString.trim();
			} else {
				throw new NumberFormatException();
			}
			cholesterol = Integer.parseInt(cholesterolString);

			if (cholesterol <= 0){
				throw new NumberFormatException();
			}
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter cholesterol as an integer greater than 0", "Cholesterol Error", JOptionPane.ERROR_MESSAGE);
			cholesterol = -1;
		}

		if (cholesterol != -1){
			cholesterolIsValid = true;
		} else{
			cholesterolIsValid = false;
		}
		return cholesterol;


	}

	public boolean classifyCholesterol(){
		if (cholesterolIsValid){
			HealthScreenUtility hsu = new HealthScreenUtility();
			cholesterolClassification = hsu.getCholesterolClassification(cholesterol);
			cholesterolClassificationLabel.setText(cholesterolClassification);
			return true;
		}
		return false;
	}


	public int validateAndRetrieveHeightFeet(){
		int feet;
		try {
			String feetString = heightFeetTextField.getText();
			if (feetString != null && !feetString.isEmpty()){
				feetString = feetString.trim();
			} else{
				throw new NumberFormatException();
			}

			feet = Integer.parseInt(feetString);

			if (feet <= 0){
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter feet as an integer greater than 0.", "Height Error", JOptionPane.ERROR_MESSAGE);
			feet = -1;
		}
		if (feet != -1){
			heightFeetIsValid = true;
		} else{
			heightFeetIsValid = false;
		}
		return feet;
	}

	public double validateAndRetrieveHeightInches(){
		double inches;
		try {
			String inchesString = heightInchesTextField.getText();
			if (inchesString != null && !inchesString.isEmpty()){
				inchesString = inchesString.trim();
				inches = Double.parseDouble(inchesString);

				if (!inchesString.matches("^[0-9]*\\.?[0-9]$") || (inches <= 0 || inches >= 12)){
					throw new NumberFormatException();
				}
			} else {
				inches = 0;
			}
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter inches between 0 and 12, with up to one decimal place.", "Height Error", JOptionPane.ERROR_MESSAGE);
			inches = -1;
		}
		if (inches != -1){
			heightInchesIsValid = true;
		} else{
			heightInchesIsValid = false;
		}

		return inches;
	}

	public int validateAndRetrieveWeight(){
		int weight;
		try {
			String weightString = weightTextField.getText();
			if (weightString != null && !weightString.isEmpty()){
				weightString = weightString.trim();
			} else{
				throw new NumberFormatException();
			}

			weight = Integer.parseInt(weightString);
			if (weight <= 0){
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter weight as an integer greater than 0.", "Weight Error", 
					JOptionPane.ERROR_MESSAGE);
			weight = -1;
		}

		if (weight != -1){
			weightIsValid = true;
		} else{
			weightIsValid = false;
		}

		return weight;
	}
	/**
	 * 
	 * @param textField
	 * @return
	 */
	public boolean classifyBMI(){
		if (heightFeetIsValid && heightInchesIsValid && weightIsValid){
			HealthScreenUtility hsu = new HealthScreenUtility();
			double heightInInches = heightFeet * 12 + heightInches;
			bmi = hsu.calculateBMI(weight, heightInInches, null);
			bmiClassification = hsu.getBMIClassification(weight, heightInInches, null);
			bmiClassificationLabel.setText(String.format("%.1f %s",bmi, bmiClassification));
			return true;
		}
		return false;
	}

	public int validateAndRetrieveSystolic(){
		int systolic;
		try{
			String systolicString = bloodPressureSystolicTextField.getText();
			if (systolicString != null && !systolicString.isEmpty()){
				systolicString = systolicString.trim();
			} else{
				throw new NumberFormatException();
			}
			systolic = Integer.parseInt(systolicString);

			if (systolic <= 0){
				throw new NumberFormatException();
			}

		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter systolic blood pressure as an integer greater than 0.", 
					"Blood Pressure Error", JOptionPane.ERROR_MESSAGE);
			systolic = -1;
		}

		if (systolic != -1){
			systolicIsValid = true;
		} else{
			systolicIsValid = false;
		}

		return systolic;
	}

	public int validateAndRetrieveDiastolic(){
		int diastolic;
		try{
			String diastolicString = bloodPressureDiastolicTextField.getText();
			if (diastolicString != null && !diastolicString.isEmpty()){
				diastolicString = diastolicString.trim();
			} else{
				throw new NumberFormatException();
			}
			diastolic = Integer.parseInt(diastolicString);

			if (diastolic <= 0){
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter diastolic blood pressure as an integer greater than 0.", 
					"Blood Pressure Error", JOptionPane.ERROR_MESSAGE);
			diastolic = -1;
		}

		if (diastolic != -1){
			diastolicIsValid = true;
		} else{
			diastolicIsValid = false;
		}

		return diastolic;
	}

	public boolean classifyBloodPressure(){
		if (systolicIsValid && diastolicIsValid){
			HealthScreenUtility hsu = new HealthScreenUtility();
			bloodPressureClassification = hsu.getBloodPressureClassification(systolic, diastolic);
			bloodPressureClassificationLabel.setText(bloodPressureClassification);
			return true;
		}
		return false;
	}
	
	public void clearFields(){
		nameFirstTextField.setText("");
		nameLastTextField.setText("");
		ageTextField.setText("");
		heightFeetTextField.setText("");
		heightInchesTextField.setText("");
		weightTextField.setText("");
		cholesterolTextField.setText("");
		bloodPressureSystolicTextField.setText("");
		bloodPressureDiastolicTextField.setText("");
		cholesterolClassificationLabel.setText("");
		bmiClassificationLabel.setText("");
		bloodPressureClassificationLabel.setText("");
	}
	
	public void disableFields(){
		nameFirstTextField.setEnabled(false);
		nameLastTextField.setEnabled(false);
		ageTextField.setEnabled(false);
		heightFeetTextField.setEnabled(false);
		heightInchesTextField.setEnabled(false);
		weightTextField.setEnabled(false);
		cholesterolTextField.setEnabled(false);
		bloodPressureSystolicTextField.setEnabled(false);
		bloodPressureDiastolicTextField.setEnabled(false);
		saveButton.setEnabled(false);
		printButton.setEnabled(false);
		loadButton.setEnabled(false);	
	}
	
	public void enableFields(){
		nameFirstTextField.setEnabled(true);
		nameLastTextField.setEnabled(true);
		ageTextField.setEnabled(true);
		heightFeetTextField.setEnabled(true);
		heightInchesTextField.setEnabled(true);
		weightTextField.setEnabled(true);
		cholesterolTextField.setEnabled(true);
		bloodPressureSystolicTextField.setEnabled(true);
		bloodPressureDiastolicTextField.setEnabled(true);
		saveButton.setEnabled(true);
		printButton.setEnabled(true);
		loadButton.setEnabled(true);
	}

}
