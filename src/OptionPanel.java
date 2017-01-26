import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import static util.Util.*;

public class OptionPanel extends JPanel {
	private TreePanel treePanel;
	private JFormattedTextField redness;
	private JFormattedTextField blueness;
	private JFormattedTextField greenness;
	private JButton addPointButton;
	private JButton buildTreeButton;
	private JButton addPointToTreeButton;
	private JButton generateRandomButton;
	private JButton clearTreeButton;
	private Timer timer;
	
	private PointSet dataSet;
	Point[] randomPoints;
	int currRandomPoint;


	public OptionPanel(int width, int height, TreePanel treePanel){
		setPreferredSize(new Dimension(width, height));
		this.treePanel=treePanel;
		Font colorFieldFont=new Font("Arial", Font.BOLD, 60);
		NumberFormat colorFormat=NumberFormat.getIntegerInstance();
		randomPoints=new Point[25];
		
		timer=new Timer(30, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				treePanel.addPoint(randomPoints[currRandomPoint]);
				if(currRandomPoint==randomPoints.length-1){
					timer.stop();
					currRandomPoint=0;
				}else currRandomPoint++;
			}
		});
		
		dataSet=new PointSet(3);
		redness=new JFormattedTextField(colorFormat);
		redness.setFont(colorFieldFont);
		redness.setBackground(new Color(255,120,120));
		redness.setValue(0);


		blueness=new JFormattedTextField(colorFormat);
		blueness.setFont(colorFieldFont);
		blueness.setBackground(new Color(120, 120,255));
		blueness.setValue(0);

		greenness=new JFormattedTextField(colorFormat);
		greenness.setFont(colorFieldFont);
		greenness.setBackground(new Color(120,255,120));
		greenness.setValue(0);

		addPointButton=new JButton("Add Point to Dataset");
		buildTreeButton=new JButton("Build Tree from Dataset");  
		addPointToTreeButton=new JButton("Add Point to Tree");
		generateRandomButton=new JButton("Watch Random Tree Grow");
		clearTreeButton=new JButton("Clear Tree");
		
		addPointButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e){

				dataSet.addPoint(new Point(parseNumbersFromTextFields()));
				clearTextFields();
			}
		});

		buildTreeButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e){
				treePanel.buildTree(dataSet);
			}
		});

		addPointToTreeButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e){
				treePanel.addPoint(new Point(parseNumbersFromTextFields()));
				clearTextFields();
			}
		});
		
		generateRandomButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e){
				//populate randompoints
				for(int i=0;i<randomPoints.length;i++){
				randomPoints[i]=new Point(new int[]{(int) random(0, 255),(int) random(0, 255),(int) random(0, 255)});
					
				}
			
				timer.start();
			}
		});

		
		clearTreeButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e){
				treePanel.clearTree();
			}
		});
		
		setLayout(new GridLayout(8,0));

		add(redness);
		add(greenness);
		add(blueness);
		add(addPointButton);
		add(buildTreeButton);
		add(addPointToTreeButton);
		add(generateRandomButton);
		add(clearTreeButton);

	}
	
	private void clearTextFields(){
		redness.setText("0");
		greenness.setText("0");
		blueness.setText("0");
		
	}



	private int[] parseNumbersFromTextFields(){
		int[] result=new int[3];
		try{
			result[0]=Integer.parseInt(redness.getText());
            
			result[1]=Integer.parseInt(greenness.getText());

			result[2]=Integer.parseInt(blueness.getText());
			

		}catch(NumberFormatException e){}
		
		
		
		return result;

	}









}
