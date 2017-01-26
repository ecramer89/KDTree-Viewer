import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;



/*parent panel for displaying the tree*/
public class TreePanel extends JPanel{
	private KDTree tree;
	

	public TreePanel(int width, int height){
		tree=new KDTree(3);
		
		setPreferredSize(new Dimension(width,height));
		setBackground(Color.black);
		
		
	}


	public void buildTree(PointSet data){
		tree.buildTree(data);
		repaint();

	}
	
	
	public void addPoint(Point p){
		tree.addPoint(p);
		repaint();
		
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		tree.paint(g, getSize().width, getSize().height);	

	}


	public void clearTree() {
		tree=new KDTree(3);
		repaint();
		
	}



	








}
