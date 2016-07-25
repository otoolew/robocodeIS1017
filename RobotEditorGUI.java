package robocodeIS1017;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.pitt.is17.finalproject.robots.MyRobot1;
import edu.pitt.is17.finalproject.robots.MyRobot2;
import robocode.control.*;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleMessageEvent;


@SuppressWarnings("serial")
public class RobotEditorGUI extends JFrame {

	public static final String ROBOCODE_INSTALL_DIR = "/robocode";
	public static final String ROBOT_1_NAME = "edu.pitt.is17.finalproject.robots.MyRobot1";
	public static final String ROBOT_2_NAME = "edu.pitt.is17.finalproject.robots.MyRobot2";

// GUI Set UP
	
// PANELS
	private JFrame mainFrame;
    private JPanel pnlLeft; 
    private JPanel pnlRight;
    
// LABELS
    // General 
    private JLabel lblVersus;
    // Robo1 Panel
    private JLabel lblRobo1;
    private JLabel lblBulletEnergy;
    private JLabel lblStepDistance;
    private JLabel lblRightRotAngle;
    private JLabel lblLeftRotAngle;
    // Robo2 Panel
    private JLabel lblRobo2;
    private JLabel lblBulletEnergy2;
    private JLabel lblStepDistance2;
    private JLabel lblRightRotAngle2;
    private JLabel lblLeftRotAngle2;
    
    // Sliders
    private JSlider sldBullet;
    private JSlider sldLeftAngle;
    private JSlider sldRightAngle;
    private JSlider sldStepDistance;
    private JSlider sldBullet2;
    private JSlider sldLeftAngle2;
    private JSlider sldRightAngle2;
    private JSlider sldStepDistance2;
    
    // Text Fields
    private JTextField txtBullet;
    private JTextField txtLeftAngle;
    private JTextField txtRightAngle;
    private JTextField txtStepDistance;
    private JTextField txtBullet2;
    private JTextField txtLeftAngle2;
    private JTextField txtRightAngle2;
    private JTextField txtStepDistance2;
    
    // Buttons
    private JButton btnSave;
    private JButton btnSave2;
    private JButton btnRun;
    
	public RobotEditorGUI(){
		initComponents();
		runBattle();
	}
	public static void main(String[] args) {
		RobotEditorGUI roboGui = new RobotEditorGUI();
	}
	
	private void initComponents(){
		
	// WINDOW
		// Initializing	
		mainFrame = new JFrame("Robot Editor GUI");
		//Settings
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setSize(750, 300);
		mainFrame.setLayout(null);
	// PANELS
		// Initializing		
		pnlLeft = new JPanel();
		pnlRight = new JPanel();
		// Settings		
		pnlLeft.setLayout(null);
		pnlRight.setLayout(null);
		pnlLeft.setBounds(25, 40, 300, 135);
		pnlRight.setBounds(400, 40, 300, 135);
		pnlRight.setBackground(Color.lightGray);
		pnlLeft.setBackground(Color.lightGray);
	// LABELS   
	    // Initializing
		lblVersus = new JLabel("v/s");	
		lblRobo1 = new JLabel("MyRobot1");
		lblBulletEnergy = new JLabel("Bullet Energy");
		lblStepDistance = new JLabel("Step Distance");
		lblRightRotAngle = new JLabel("Rot. Angle R");
		lblLeftRotAngle = new JLabel("Rot. Angle L");		
		lblRobo2 = new JLabel("MyRobot2");
		lblBulletEnergy2 = new JLabel("Bullet Energy");
		lblStepDistance2 = new JLabel("Step Distance");
		lblRightRotAngle2 = new JLabel("Rot. Angle R");
		lblLeftRotAngle2 = new JLabel("Rot. Angle L");
		// Settings
		//      .setBounds(x,y,width,hieght) The Width and hieght of the container
		lblVersus.setBounds(345, 95, 150, 30);
		
		// Font and Size
		lblVersus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		
		// Color
		lblBulletEnergy.setBackground(Color.lightGray);
		lblStepDistance.setBackground(Color.lightGray);
		lblRightRotAngle.setBackground(Color.lightGray);
		lblLeftRotAngle.setBackground(Color.lightGray);
		lblBulletEnergy2.setBackground(Color.lightGray);
		lblStepDistance2.setBackground(Color.lightGray);
		lblRightRotAngle2.setBackground(Color.lightGray);
		lblLeftRotAngle2.setBackground(Color.lightGray);
		
		
		// GUI Placement
				
		lblRobo1.setBounds(25, 15, 150, 30);
		lblBulletEnergy.setBounds(10, 5, 150, 30);
		lblLeftRotAngle.setBounds(10, 35, 150, 30);	
		lblRightRotAngle.setBounds(10, 65, 150, 30);		
		lblStepDistance.setBounds(10, 95, 150, 30);
		
		lblRobo2.setBounds(400, 15, 150, 30);
		lblBulletEnergy2.setBounds(10, 5, 150, 30);
		lblLeftRotAngle2.setBounds(10, 35, 150, 30);
		lblRightRotAngle2.setBounds(10, 65, 150, 30);
		lblStepDistance2.setBounds(10, 95, 150, 30);
		
		
	// SLIDERS
		// Initializing
		sldBullet = new JSlider(1,10);
		sldLeftAngle = new JSlider(0,180);
		sldRightAngle = new JSlider(0,180);
		sldStepDistance = new JSlider(10,1000);
		sldBullet2 = new JSlider(1,10);
		sldLeftAngle2 = new JSlider(0,180);
		sldRightAngle2 = new JSlider(0,180);
		sldStepDistance2 = new JSlider(10,1000);
		// Settings Color 
		sldBullet.setBackground(Color.lightGray);
		sldLeftAngle.setBackground(Color.lightGray);
		sldRightAngle.setBackground(Color.lightGray);
		sldStepDistance.setBackground(Color.lightGray);		
		sldBullet2.setBackground(Color.lightGray);
		sldLeftAngle2.setBackground(Color.lightGray);
		sldRightAngle2.setBackground(Color.lightGray);
		sldStepDistance2.setBackground(Color.lightGray);
		
		// GUI Placement
		sldBullet.setBounds(100, 5, 150, 30);
		sldLeftAngle.setBounds(100, 35, 150, 30);
		sldRightAngle.setBounds(100, 65, 150, 30);
		sldStepDistance.setBounds(100, 95, 150, 30);
		sldBullet2.setBounds(100, 5, 150, 30);
		sldLeftAngle2.setBounds(100, 35, 150, 30);
		sldRightAngle2.setBounds(100, 65, 150, 30);
		sldStepDistance2.setBounds(100, 95, 150, 30);
		// Event Listeners
		sldBullet.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldBulletStateChanged(evt);            
            }
        });
		sldLeftAngle.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldLeftAngleStateChanged(evt);            
            }
        });
		sldRightAngle.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldRightAngleStateChanged(evt);            
            }
        });
		sldStepDistance.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldStepDistanceStateChanged(evt);            
            }
        });
		sldBullet2.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldBullet2StateChanged(evt);            
            }
        });
		sldLeftAngle2.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldLeftAngle2StateChanged(evt);            
            }
        });
		sldRightAngle2.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldRightAngle2StateChanged(evt);            
            }
        });
		sldStepDistance2.addChangeListener(new ChangeListener() {      
            public void stateChanged(ChangeEvent evt) {
            	sldStepDistance2StateChanged(evt);            
            }
        });
				
	// TEXT FIELDS
		// Initializing
		txtBullet = new JTextField();
	    txtLeftAngle = new JTextField();
	    txtRightAngle = new JTextField();
	    txtStepDistance = new JTextField();
	    txtBullet2 = new JTextField();
	    txtLeftAngle2 = new JTextField();
	    txtRightAngle2 = new JTextField();
	    txtStepDistance2 = new JTextField();
	    // Settings
		txtBullet.setBounds(255, 5, 40, 30);
	    txtLeftAngle.setBounds(255, 35, 40, 30);
	    txtRightAngle.setBounds(255, 65, 40, 30);
	    txtStepDistance.setBounds(255, 95, 40, 30);
	    txtBullet2.setBounds(255, 5, 40, 30);
	    txtLeftAngle2.setBounds(255, 35, 40, 30);
	    txtRightAngle2.setBounds(255, 65, 40, 30);
	    txtStepDistance2.setBounds(255, 95, 40, 30);
	    // Event Listeners	    
	    txtBullet.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtBulletActionPerformed(evt);
            }
        });
	    txtLeftAngle.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtLeftAngleActionPerformed(evt);
            }
        });
	    txtRightAngle.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtRightAngleActionPerformed(evt);
            }
        });
	    txtStepDistance.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtStepDistanceActionPerformed(evt);
            }
        });
	    txtBullet2.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtBullet2ActionPerformed(evt);
            }
        });
	    txtLeftAngle2.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtLeftAngle2ActionPerformed(evt);
            }
        });
	    txtRightAngle2.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtRightAngle2ActionPerformed(evt);
            }
        });
	    txtStepDistance2.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
            	txtStepDistance2ActionPerformed(evt);
            }
        });
	    	    
	// BUTTONS
		// Intializing
		btnRun = new JButton("Run Battle");
		btnSave = new JButton("Save");
		btnSave2 = new JButton("Save");
		// Initializing		
		btnSave.setBounds(25, 176, 300, 20);
		btnSave2.setBounds(400, 176, 300, 20);
		btnRun.setBounds(300, 210, 120, 40);
		// Event Listeners
		btnRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	btnRunActionPerformed(evt);
            }
        });
		btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	btnSaveActionPerformed(evt);
            }
        });
		btnSave2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	btnSave2ActionPerformed(evt);
            }
        });
	// LAYOUT	
		// Adding Components
		mainFrame.add(pnlLeft);
		mainFrame.add(pnlRight);
		mainFrame.add(lblVersus);
		mainFrame.add(lblRobo1);
		mainFrame.add(lblRobo2);
		mainFrame.add(btnRun);
		mainFrame.add(btnSave);
		mainFrame.add(btnSave2);
		
		// Adding Components
		pnlLeft.add(lblBulletEnergy);
		pnlLeft.add(lblLeftRotAngle);
		pnlLeft.add(lblRightRotAngle);
		pnlLeft.add(lblStepDistance);		
		pnlLeft.add(sldBullet);
		pnlLeft.add(sldLeftAngle);
		pnlLeft.add(sldRightAngle);
		pnlLeft.add(sldStepDistance);
		pnlLeft.add(txtBullet);
		pnlLeft.add(txtLeftAngle);
		pnlLeft.add(txtRightAngle);
		pnlLeft.add(txtStepDistance);
		
		// Adding Components
		pnlRight.add(lblBulletEnergy2);
		pnlRight.add(lblLeftRotAngle2);
		pnlRight.add(lblRightRotAngle2);
		pnlRight.add(lblStepDistance2);
		pnlRight.add(sldBullet2);
		pnlRight.add(sldLeftAngle2);
		pnlRight.add(sldRightAngle2);
		pnlRight.add(sldStepDistance2);
		pnlRight.add(txtBullet2);
		pnlRight.add(txtLeftAngle2);
		pnlRight.add(txtRightAngle2);
		pnlRight.add(txtStepDistance2);
		
		// Setting mainFrame to Visable
		mainFrame.setVisible(true); // Display frame (make visible)
	}
	// BUTTON EVENT HANDLERS
	private void btnRunActionPerformed(ActionEvent evt) {                                          
		runBattle();
    } 
	private void btnSaveActionPerformed(ActionEvent evt) { 
		
		Properties props = new Properties();
	    InputStream input = null;
	 
	    try {
	    	File file = new File("C:\\Users\\Jack\\Desktop\\Eclipse Projects\\wbo4_FinalProject\\config\\MyRobot1.properties");
	        input = new FileInputStream(file);
	        System.out.println("LOADING CORRECTLY");	        
	        props.load(input);
	        
	        String bullet = props.getProperty("bulletenergy");
	        String step = props.getProperty("stepdistance");
	        String rRot = props.getProperty("rightrotationangle");
	        String lRot = props.getProperty("leftrotationangle");
	        
	        System.out.println("Old Robot Properties: "+bullet+" "+step+" "+rRot+" "+lRot);	        
	        System.out.println("Text Properties: "+txtBullet.getText()+" "+txtStepDistance.getText()+" "+
	        		txtRightAngle.getText()+" "+txtLeftAngle.getText());
	        
	        props.setProperty("bulletenergy", txtBullet.getText());
			props.setProperty("stepdistance", txtStepDistance.getText());
			props.setProperty("rightrotationangle", txtRightAngle.getText());
			props.setProperty("leftrotationangle", txtLeftAngle.getText());
			
			File propOutput = new File("C:\\Users\\Jack\\Desktop\\Eclipse Projects\\wbo4_FinalProject\\config\\MyRobot1.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			props.store(fileOut, "Robot Properties");
			fileOut.close();
			
			System.out.println("Text Set to Properties: "+txtBullet.getText()+" "+txtStepDistance.getText()+" "+
	        		txtRightAngle.getText()+" "+txtLeftAngle.getText());
			
			bullet = props.getProperty("bulletenergy");
	        step = props.getProperty("stepdistance");
	        rRot = props.getProperty("rightrotationangle");
	        lRot = props.getProperty("leftrotationangle");
	        
	        System.out.println("New Robot1 Properties: "+bullet+" "+step+" "+rRot+" "+lRot);
	        
	    }	    
	    catch ( Exception e ) { 
	    	System.out.println("NOT LOADING CORRECTLY");
    	}		
	}

	private void btnSave2ActionPerformed(ActionEvent evt) {                                          
		Properties props = new Properties();
	    InputStream input = null;
	    System.out.println("LOADING CORRECTLY");
	    try {
	    	File file = new File("C:\\Users\\Jack\\Desktop\\Eclipse Projects\\wbo4_FinalProject\\config\\MyRobot2.properties");
	        input = new FileInputStream(file);
	        System.out.println("LOADING CORRECTLY");	        
	        props.load(input);
	        
	        String bullet = props.getProperty("bulletenergy");
	        String step = props.getProperty("stepdistance");
	        String rRot = props.getProperty("rightrotationangle");
	        String lRot = props.getProperty("leftrotationangle");
	        
	        System.out.println("Old Robot Properties: "+bullet+" "+step+" "+rRot+" "+lRot);	        
	        System.out.println("Text Properties: "+txtBullet2.getText()+" "+txtStepDistance2.getText()+" "+
	        		txtRightAngle2.getText()+" "+txtLeftAngle2.getText());
	        
	        props.setProperty("bulletenergy", txtBullet2.getText());
			props.setProperty("stepdistance", txtStepDistance2.getText());
			props.setProperty("rightrotationangle", txtRightAngle2.getText());
			props.setProperty("leftrotationangle", txtLeftAngle2.getText());
			System.out.println("Text Set to Properties: "+txtBullet2.getText()+" "+txtStepDistance2.getText()+" "+
	        		txtRightAngle2.getText()+" "+txtLeftAngle2.getText());
			
			File propOutput = new File("C:\\Users\\Jack\\Desktop\\Eclipse Projects\\wbo4_FinalProject\\config\\MyRobot2.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			props.store(fileOut, "Robot Properties");
			
			fileOut.close();
			
			bullet = props.getProperty("bulletenergy");
	        step = props.getProperty("stepdistance");
	        rRot = props.getProperty("rightrotationangle");
	        lRot = props.getProperty("leftrotationangle");
	        
	        System.out.println("New Robot2 Properties: "+bullet+" "+step+" "+rRot+" "+lRot);
	        
	    }	    
	    catch ( Exception e ) { 
	    	System.out.println("NOT LOADING CORRECTLY");
    	}		
	 	   
    }  
	// SLIDER EVENT HANDLERS
	// Slider 1 Change Handlers
	private void sldBulletStateChanged(ChangeEvent evt){                                          
		txtBullet.setText(String.valueOf(sldBullet.getValue()));
    }  
	private void sldLeftAngleStateChanged(ChangeEvent evt){                                          
		txtLeftAngle.setText(String.valueOf(sldLeftAngle.getValue()));
    }
	private void sldRightAngleStateChanged(ChangeEvent evt){                                          
		txtRightAngle.setText(String.valueOf(sldRightAngle.getValue()));
    }
	private void sldStepDistanceStateChanged(ChangeEvent evt){                                          
		txtStepDistance.setText(String.valueOf(sldStepDistance.getValue()));
    }
	// Slider 2 Change Handlers
	private void sldBullet2StateChanged(ChangeEvent evt){                                          
		txtBullet2.setText(String.valueOf(sldBullet2.getValue()));
    }  
	private void sldLeftAngle2StateChanged(ChangeEvent evt){                                          
		txtLeftAngle2.setText(String.valueOf(sldLeftAngle2.getValue()));
    }
	private void sldRightAngle2StateChanged(ChangeEvent evt){                                          
		txtRightAngle2.setText(String.valueOf(sldRightAngle2.getValue()));
    }
	private void sldStepDistance2StateChanged(ChangeEvent evt){                                          
		txtStepDistance2.setText(String.valueOf(sldStepDistance2.getValue()));
    }
	// TEXT EVENT HANDLERS
	private void txtBulletActionPerformed(ActionEvent evt){
		try{
			sldBullet.setValue(Integer.parseInt(txtBullet.getText()));
		}
        catch(Exception ex){
        	txtBullet.setText("!");        	
        }
	};
	private void txtLeftAngleActionPerformed(ActionEvent evt){
		try{
			sldLeftAngle.setValue(Integer.parseInt(txtLeftAngle.getText()));
		}
        catch(Exception ex){
        	txtLeftAngle.setText("!");        	
        }
	};
	private void txtRightAngleActionPerformed(ActionEvent evt){
		try{
			sldRightAngle.setValue(Integer.parseInt(txtRightAngle.getText()));
		}
        catch(Exception ex){
        	txtRightAngle.setText("!");
        }
	};
	private void txtStepDistanceActionPerformed(ActionEvent evt){
		try{
			sldStepDistance.setValue(Integer.parseInt(txtStepDistance.getText()));
		}
        catch(Exception ex){
        	txtStepDistance.setText("!");
        }
	};
	// TEXT 2 EVENT HANDLERS
	private void txtBullet2ActionPerformed(ActionEvent evt){
		try{
			sldBullet2.setValue(Integer.parseInt(txtBullet2.getText()));
		}
        catch(Exception ex){
        	txtBullet2.setText("!");        	
        }
	};
	private void txtLeftAngle2ActionPerformed(ActionEvent evt){
		try{
			sldLeftAngle2.setValue(Integer.parseInt(txtLeftAngle2.getText()));
		}
        catch(Exception ex){
        	txtLeftAngle2.setText("!");        	
        }
	};
	private void txtRightAngle2ActionPerformed(ActionEvent evt){
		try{
			sldRightAngle2.setValue(Integer.parseInt(txtRightAngle2.getText()));
		}
        catch(Exception ex){
        	txtRightAngle2.setText("!");
        }
	};
	private void txtStepDistance2ActionPerformed(ActionEvent evt){
		try{
			sldStepDistance2.setValue(Integer.parseInt(txtStepDistance2.getText()));
		}
        catch(Exception ex){
        	txtStepDistance2.setText("!");
        }
	};
	
	public static void runBattle(){
		// Disable log messages from Robocode
		RobocodeEngine.setLogMessagesEnabled(false);
		// Create the RobocodeEngine
		RobocodeEngine engine = new RobocodeEngine(new java.io.File(ROBOCODE_INSTALL_DIR));
		// Add our own battle listener to the RobocodeEngine
		engine.addBattleListener(new BattleAdaptor(){
			// Called when the battle is completed successfully with battle results
			public void onBattleCompleted(BattleCompletedEvent e) {
				System.out.println("-- Battle has completed --");
				// Print out the sorted results with the robot names
				System.out.println("Battle results:");
				for (robocode.BattleResults result : e.getSortedResults()) {
					System.out.println(" " + result.getTeamLeaderName() + ": " + result.getScore());
				}
			}
			// Called when the game sends out an information message during the battle
			public void onBattleMessage(BattleMessageEvent e) {
				System.out.println("Msg> " + e.getMessage());
			}
			// Called when the game sends out an error message during the battle
			public void onBattleError(BattleErrorEvent e) {
				System.out.println("Err> " + e.getError());
			}
		});
		// Show the Robocode battle view
		engine.setVisible(true);
		// Setup the battle specification
		int numberOfRounds = 5;
		BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
		RobotSpecification[] selectedRobots = engine.getLocalRepository(ROBOT_1_NAME+"*,"+ROBOT_2_NAME+"*");
		System.out.println("Robots selected");
		BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);
		System.out.println("Inactivity time:"+battleSpec.getInactivityTime());
		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true); // waits till the battle finishes
		// Cleanup our RobocodeEngine
		engine.close();
		// Make sure that the Java VM is shut down properly
		System.exit(0);
	}

}