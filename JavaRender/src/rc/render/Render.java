package rc.render;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Render extends JPanel {
	public Element element;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		element.paint(g);
		
		// JSON Read the file from web
		// Apply CSS
		// Render...
		/*
		 * Render order:
		 * 1. Calculate the sizes, render from top component to all subcomponents 
		 * 2. Paint when the sizes are known
		 * 3. When rendering text, the max_width has to be known
		 * 4. Images need a ratio (e.g. 3/2) or a size 
		 * 5. 50%, 20px, content_sized
		 * 
		 * <rectangle>
		 * width, height, x, y
		 * relativeTo:
		 * border:
		 * shadow:
		 * background:
		 * distance
		 * space
		 * 
		 * 
		 * <text>
		 * font:
		 * box: [paint, baseline xyz]
		 * color:
		 * 
		 * <input>
		 * 
		 * 
		 * 
		 */
	}
	
	
}
