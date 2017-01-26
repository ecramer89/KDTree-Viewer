import java.awt.Color;
import java.awt.Graphics;

public class Point {

	private int[] data;
	
	
	public Point(int[] data){
		this.data=data;
	}
	
	
	@Override
	public String toString(){
		StringBuffer s=new StringBuffer();
		s.append("{");
		for(int i=0;i<data.length;i++){
			s.append(data[i]);
			if(i<data.length-1) s.append(",");
		}
		s.append("}");
		
		
		return s.toString();
	}


	public int getValue(int dimension) {
		// TODO Auto-generated method stub
		return data[dimension];
	}


	public int numDimensions() {
		// TODO Auto-generated method stub
		return data.length;
	}
	
	
	public void paint(Graphics g, int x, int y, int width, int height){
		
		g.setColor(new Color(data[0], data[1], data[2]));
		g.fillRect(x-width/2, y-height/2, width, height);
		g.setColor(Color.white);
		g.drawRect(x-width/2, y-height/2, width, height);
		
	}


	public void setData(int[] data) {
		this.data=data;
		
	}
	
	
}
