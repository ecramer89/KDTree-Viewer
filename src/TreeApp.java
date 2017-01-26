import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeApp extends JFrame {
	
	
	
	public TreeApp(String title){
		super(title);
	
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout());
		int displayWidth=1200;
		int displayHeight=800;
		
		TreePanel treePanel=new TreePanel(displayWidth*3/4, displayHeight);
	
		container.add(treePanel);
		OptionPanel optionPanel=new OptionPanel(displayWidth/4, displayHeight, treePanel);
		container.add(optionPanel);
		add(container);
		pack();
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new TreeApp("KD Tree Maker");


	}

}
