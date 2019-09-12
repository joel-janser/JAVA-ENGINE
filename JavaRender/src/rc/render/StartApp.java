package rc.render;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class StartApp {

	public static void main(String[] args) {
	    JFrame frame = new JFrame();
	    
	    Element main = new Element(null);
	    main.width = 800;
	    main.height = 600;
	    main.backgroundColor = Color.GRAY;
	    main.setSpace(32);
	    
	    Element blueBox = new Element(main);
	    blueBox.setDistance(32);
	    blueBox.setSpace(32);
	    blueBox.backgroundColor = Color.BLUE;
	    blueBox.widthType = SizeType.PIXEL;
	    blueBox.width = 500;
	    blueBox.flowType = ElementFlow.HORIZONTAL;
	    blueBox.heightType = SizeType.CONTENT_SIZED;
	    main.addSubelement(blueBox);
	    
	    
	    Element redBox  =  new Element(blueBox);
	    redBox.setSpace(32);
	    redBox.setDistance(32);
	    redBox.width = 40;
	    redBox.widthType = SizeType.PIXEL;
	    redBox.height = 40;
	    redBox.heightType = SizeType.PIXEL;
	    redBox.backgroundColor = Color.red;
	    redBox.flowType = ElementFlow.HORIZONTAL;
	    blueBox.subElements.add(redBox);
	    
	    Element greenBox  =  new Element(blueBox);
	    greenBox.setSpace(32);
	    greenBox.setDistance(32);
	    greenBox.width = 80;
	    greenBox.widthType = SizeType.PERCENT;
	    greenBox.height = 10;
	    greenBox.heightType = SizeType.PIXEL;
	    greenBox.backgroundColor = Color.green;
	    greenBox.flowType = ElementFlow.HORIZONTAL;
	    blueBox.subElements.add(greenBox);
	    
	    Element greenBox2  =  new Element(blueBox);
	    greenBox2.setSpace(32);
	    greenBox2.setDistance(32);
	    greenBox2.width = 45;
	    greenBox2.widthType = SizeType.PIXEL;
	    greenBox2.height = 45;
	    greenBox2.heightType = SizeType.PIXEL;
	    greenBox2.backgroundColor = Color.white;
	    greenBox2.flowType = ElementFlow.HORIZONTAL;
	    blueBox.subElements.add(greenBox2);
	    
	    
	    main.render(0,0);
	    Render r = new Render();
	    r.element = main;
	    frame.getContentPane().add(r);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(800,600);
	    frame.setVisible(true);
	    
	    
	}

}
