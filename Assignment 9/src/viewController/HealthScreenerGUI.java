package viewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fileIO.ResultsFileReader;
import fileIO.ResultsFileWriter;
import model.HealthScreenUtility;
import model.Patient;
import model.ScreenResults;

public class HealthScreenerGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	public static final Color BACKCOLOR = new Color(160, 200, 255);
	
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

	/**
	 * Creates a GUI to conduct patient health screenings
	 */
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
		namePanel.setBackground(BACKCOLOR);

		JLabel nameHeader = new JLabel("Health Screening for: ");
		namePanel.add(nameHeader);

		nameFirstTextField = new JTextField("First Name", 7);
		namePanel.add(nameFirstTextField);
		
		nameLastTextField = new JTextField("Last Name", 7);
		namePanel.add(nameLastTextField);

		add(namePanel);


		//Date
		JPanel datePanel = new JPanel(flowLeft);
		datePanel.setBackground(BACKCOLOR);

		Date currentDate = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		JLabel dateLabel = new JLabel("Date: " + df.format(currentDate));
		datePanel.add(dateLabel);

		add(datePanel);

		//Age
		JPanel agePanel = new JPanel(flowLeft);
		agePanel.setBackground(BACKCOLOR);
		agePanel.setBorder(new EmptyBorder(0,0,0,10));

		JLabel ageHeader = new JLabel("Age: ");
		agePanel.add(ageHeader);

		ageTextField = new JTextField(2);
		agePanel.add(ageTextField);

		add(agePanel);

		//Height
		JPanel heightPanel = new JPanel(flowLeft);
		heightPanel.setBackground(BACKCOLOR);

		JLabel heightHeader = new JLabel("Height: ");
		heightPanel.add(heightHeader);

		JPanel feetPanel = new JPanel(new BorderLayout());
		feetPanel.setBackground(BACKCOLOR);
		feetPanel.setBorder(new EmptyBorder(0,0,0,5));

		heightFeetTextField = new JTextField(3);
		heightFeetTextField.setName("heightFeetTextField");
		feetPanel.add(heightFeetTextField, BorderLayout.WEST);

		JLabel feetSymbol = new JLabel("Feet");
		feetSymbol.setBorder(new EmptyBorder(0,4,0,0));
		feetPanel.add(feetSymbol, BorderLayout.EAST);

		heightPanel.add(feetPanel);

		JPanel inchesPanel = new JPanel(new BorderLayout());
		inchesPanel.setBackground(BACKCOLOR);
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
		weightPanel.setBackground(BACKCOLOR);

		JLabel weightHeader = new JLabel("Weight: ");
		weightPanel.add(weightHeader);

		weightTextField = new JTextField(3);
		weightTextField.setName("weightTextField");
		weightPanel.add(weightTextField);

		add(weightPanel);

		//Cholesterol
		JPanel cholesterolPanel = new JPanel(flowLeft);
		cholesterolPanel.setBackground(BACKCOLOR);

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
		bmiPanel.setBackground(BACKCOLOR);

		JLabel bmiHeader = new JLabel("Body Mass Index: ");
		bmiPanel.add(bmiHeader);

		JLabel bmiLabel = new JLabel();
		bmiPanel.add(bmiLabel);

		bmiClassificationLabel = new JLabel();
		bmiPanel.add(bmiClassificationLabel);

		add(bmiPanel);

		//Blood Pressure
		JPanel bloodPressurePanel = new JPanel(flowLeft);
		bloodPressurePanel.setBackground(BACKCOLOR);

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
		controlPanel.setBackground(BACKCOLOR);

		saveButton = new JButton("Save");
		controlPanel.add(saveButton);

		printButton = new JButton("Print");
		controlPanel.add(printButton);
		
		loadButton = new JButton("Load");
		controlPanel.add(loadButton);

		exitButton = new JButton("Exit");
		/**
		 * Saves all the patient data to a file with the file format MM-DD-YYYY.ser (Date).
		 * Then exits the program.
		 */
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (patients.size() != 0){
					LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int year = localDate.getYear();
					int month = localDate.getMonthValue();
					int day = localDate.getDayOfMonth();
					ResultsFileWriter rfw = new ResultsFileWriter(month + "-" + day + "-" + year + ".txt");
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



		/**
		 * Remove text from the text field upon clicking.
		 */
		MouseAdapter clearTextFieldOnFirstClickListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField thisTextField = (JTextField)e.getComponent();
				thisTextField.setText("");
				thisTextField.removeMouseListener(this);
			}
		};
		nameFirstTextField.addMouseListener(clearTextFieldOnFirstClickListener);

		/**
		 * Removes text from text field upon gaining focus.
		 */
		nameLastTextField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e){
				JTextField thisTextField = (JTextField)e.getComponent();
				thisTextField.setText("");
				thisTextField.removeFocusListener(this);
			}
		});
		
		/**
		 * Validates and gathers all data from the text fields, sets classifications,
		 * and prompts user to verify data and submit. Then creates a Patient object
		 * from the data and adds it to patients ArrayList
		 */
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
		
		/**
		 * Generates the command line interface report and online pie charts for all the patients screened on the current day.
		 */
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
		
		/**
		 * Creates a ScreeningSelectionWindow to load a previous day of health screenings
		 */
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
	
	/**
	 * Validates and parses the patient name from the first and last name text fields
	 */
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
	
	/**
	 * Validates and parses patient age from text field
	 */
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

	/**
	 * Validates and parses patient cholesterol from text field
	 */
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

	/**
	 * Classifies the cholesterol based using HealthScreenUtility object helper methods.
	 * Assigns classification to the cholesterol classification label.
	 */
	private boolean classifyCholesterol(){
		if (cholesterolIsValid){
			HealthScreenUtility hsu = new HealthScreenUtility();
			cholesterolClassification = hsu.getCholesterolClassification(cholesterol);
			cholesterolClassificationLabel.setText(cholesterolClassification);
			return true;
		}
		return false;
	}


	/**
	 * Validates and parses patient height feet from text field 
	 */
	private int validateAndRetrieveHeightFeet(){
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

	/**
	 * Validates and parses patient height inches from text field.
	 */
	private double validateAndRetrieveHeightInches(){
		double inches;
		try {
			String inchesString = heightInchesTextField.getText();
			if (inchesString != null && !inchesString.isEmpty()){
				inchesString = inchesString.trim();
				inches = Double.parseDouble(inchesString);

				if (!inchesString.matches("^[0-9]*\\.?[0-9]$") || (inches < 0 || inches >= 12)){
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

	/**
	 * Validates and parses patient weight from text field.
	 */
	private int validateAndRetrieveWeight(){
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
		} catch (NumberFormatException e){
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
	 * Classifies the BMI using HealthScreenUtility object helper method
	 * Assigns classification to BMI classification label
	 */
	private boolean classifyBMI(){
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

	/**
	 * Validates and parses patient systolic blood pressure from text field.
	 */
	private int validateAndRetrieveSystolic(){
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

	/**
	 * Validates and parses patient diastolic blood pressure from text field
	 */
	private int validateAndRetrieveDiastolic(){
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

	/**
	 * Classify blood pressure using HealthScreenUtility helper methods.
	 * Assigns classification to blood pressure classification label
	 */
	private boolean classifyBloodPressure(){
		if (systolicIsValid && diastolicIsValid){
			HealthScreenUtility hsu = new HealthScreenUtility();
			bloodPressureClassification = hsu.getBloodPressureClassification(systolic, diastolic);
			bloodPressureClassificationLabel.setText(bloodPressureClassification);
			return true;
		}
		return false;
	}
	
	/**
	 * Clears text data from all text fields
	 */
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
	
	/**
	 * Disables all text fields and all buttons except exit button.
	 * Used when a SreeningSelectionWindow is displayed to direct user control.
	 */
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
	
	/**
	 * Enables all text fields and buttons.
	 * Used after a ScreeningSelectionWindow is closed to re-enable user control on GUI.
	 */
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
