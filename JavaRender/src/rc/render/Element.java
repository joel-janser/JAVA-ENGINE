package rc.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Element {
	public int x;
	public int y;
	public int widthPixel;
	public int heightPixel;
	public int width;
	public SizeType widthType = SizeType.PIXEL;
	public int height;
	public SizeType heightType = SizeType.PIXEL;
	public int distanceTop = 0;
	public int distanceRight = 0;
	public int distanceBottom = 0;
	public int distanceLeft = 0;
	public int spaceTop = 0;
	public int spaceRight = 0;
	public int spaceBottom = 0;
	public int spaceLeft = 0;
	public Color backgroundColor = Color.WHITE;
	public Color borderColor = Color.WHITE;
	public ShapeType shapeType = ShapeType.RECTANGLE;

	public ElementFlow flowType = ElementFlow.VERTICAL;

	public Element superElement;
	public LinkedList<Element> subElements;

	public Element(Element parent) {
		this.subElements = new LinkedList<Element>();
		superElement = parent;
	}

	public void render(int x, int y) {
		int currentX = x;
		int currentY = y;
		int maxUsedHeight = 0;
		int maxY = 0;
		int maxX = 0;

		// int lineMaxDistanceBottom = 0;
		int lineY = y;

		if (widthType == SizeType.PIXEL) {
			widthPixel = width;
		}

		if (heightType == SizeType.PIXEL) {
			heightPixel = height;
		}
		if (heightType == SizeType.CONTENT_SIZED) {
			heightPixel = superElement.heightPixel;
		}
		
		if (widthType == SizeType.CONTENT_SIZED) {
			widthPixel = superElement.widthPixel - superElement.spaceLeft - superElement.spaceRight;
		}
		
		


		if (widthType == SizeType.PERCENT) {
			if (superElement.widthPixel != -1) {
				int maxWidth = superElement.widthPixel - superElement.spaceLeft - superElement.spaceRight;
				widthPixel = maxWidth * width / 100;
				System.out.println("Super: " + this.superElement.widthPixel + " " + widthPixel);
			} else {
				// Implement
				System.err.println("Ein Fehler ist aufgetreten");
			}
		}

		if (heightType == SizeType.PERCENT) {
			if (superElement.heightPixel != -1) {
				heightPixel = this.superElement.heightPixel / 100 * height;
			} else {
				// Implement
			}
		}

		int maxContentWidth = widthPixel;
		if (superElement != null) {			
			maxContentWidth = widthPixel - spaceLeft - spaceRight;
		}
		
		int usedHeight = 0;
		int lineHeight = 0;

		Element lastElement = null;
		for (Element subElement : subElements) {
			if (subElement.flowType == ElementFlow.VERTICAL) {
				currentX = x; // align-type (?)
			}
			if (subElement.flowType == ElementFlow.HORIZONTAL) {
				currentY = lineY;
			}

			if (currentX == x) {
				subElement.x = x + Math.max(spaceLeft, subElement.distanceLeft);
			} else {
				subElement.x = lastElement.getX2() + Math.max(lastElement.distanceRight, distanceLeft);
			}
			if (currentY == y) {
				subElement.y = y + Math.max(spaceTop, subElement.distanceTop);
			} else {
				subElement.y = lastElement.getY2() + Math.max(lastElement.distanceBottom, distanceTop);
			}
			//
			subElement.render(subElement.x, subElement.y);

			if (flowType == ElementFlow.HORIZONTAL) {
			//	if (widthType == SizeType.PIXEL || widthType == widthType.PERCENT) {
					if ( (currentX > x) && (subElement.getX2() > (getX2() - spaceRight))) {
						subElement.x = x + Math.max(spaceLeft, subElement.distanceLeft);
						currentY += lineHeight;
						subElement.y = maxY;
						lineHeight = 0;
					} else {
						lineHeight = Math.max(lineHeight, subElement.height); 
					}
					currentX = subElement.x + subElement.widthPixel;
				//} else {
				//	lineHeight = Math.max(lineHeight, subElement.height);
					// subElement.x = x + Math.max(spaceLeft, subElement.distanceLeft);
				//}
				// lineWidth+= subElement.widthPixel;
				// maxUsedWidth =Math.max(lineWidth, maxUsedWidth);
				// }
			} else if (flowType == ElementFlow.VERTICAL) {
				int leftSpace = Math.max(subElement.distanceLeft, spaceLeft);
				int rightSpace = Math.max(subElement.distanceRight, spaceRight);
				// lineWidth = 0;
				usedHeight += subElement.height;
				currentY += subElement.height;

				lineY += subElement.height;
				maxUsedHeight = Math.max(usedHeight, maxUsedHeight);
			}
			int newMaxY = subElement.y + subElement.heightPixel + Math.max(subElement.distanceBottom, spaceBottom);
			if (maxY < newMaxY) {
				maxY = newMaxY;
			}
			int newMaxX = subElement.x + subElement.widthPixel + Math.max(subElement.distanceRight, spaceRight);
			if (maxX < newMaxX) {
				maxX = newMaxX;
			}

			lastElement = subElement;
		}

		if (widthType == SizeType.CONTENT_SIZED) {
			widthPixel = maxX - x;
		}
		if (heightType == SizeType.CONTENT_SIZED) {
			heightPixel = maxY - y;
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(backgroundColor);
		g2d.fillRect(x, y, widthPixel, heightPixel);
		for (Element subElement : subElements) {
			subElement.paint(g);
		}
	}

	public void setDistance(int distance) {
		distanceTop = distance;
		distanceLeft = distance;
		distanceRight = distance;
		distanceBottom = distance;
	}

	public void setSpace(int space) {
		spaceTop = space;
		spaceLeft = space;
		spaceRight = space;
		spaceBottom = space;
	}

	public void addSubelement(Element e) {
		subElements.add(e);
	}

	public int getX2() {
		return x + widthPixel;
	}

	public int getY2() {
		return y + heightPixel;
	}
}