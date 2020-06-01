import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static JFrame frame;
	private static ArrayList<Shape> shapes;

	public static void main(String[] args) {
		shapes = new ArrayList<Shape>();
		GeometryScreen gs = new GeometryScreen();
		Scanner sc = new Scanner(System.in);

		int choice;
		while (true) {
			System.out.println(
					"1. Display Info\n2. Add\n3. Remove\n4. Modify\n5. Translate All\n6. Scale All\n7. Save\n8. Load\n9. Quit");
			System.out.println("Input your choice: ");
			choice = sc.nextInt();

			if (choice == 1) {
				display();

			} else if (choice == 2) {
				System.out.println(
						"1. Triangle\n2. Parallelogram\n3. Rectangle\n4. Square\n5. Rhombus\n6. Trapezoid\n7. Oval\n8. Circle\n");
				System.out.println("Input your choice: ");
				choice = sc.nextInt();

				Point centroid = null;
				if ((choice > 1) && (choice < 9)) {
					System.out.println("Input the x and y coordinates of the centroid: ");
					centroid = new Point(sc.nextInt(), sc.nextInt());
				}

				if (choice == 1) {
					int[][] vertices = new int[3][2];
					for (int i = 0; i < 3; i++) {
						System.out.println("Input the x and y coordinates of vertex " + (i + 1) + ":");

						vertices[i][0] = sc.nextInt();
						vertices[i][1] = sc.nextInt();
					}
					shapes.add(new Triangle(vertices));

				} else if (choice == 2) {
					System.out.println(
							"How far is the first vertex from the centroid, horizontally and then vertically: ");
					int x1 = sc.nextInt();
					int y1 = sc.nextInt();
					System.out.println(
							"How far is the second vertex from the centroid, horizontally and then vertically: ");
					int x2 = sc.nextInt();
					int y2 = sc.nextInt();
					shapes.add(new Parallelogram(centroid, x1, y1, x2, y2));

				} else if (choice == 3) {
					System.out.println("Input the width and height: ");
					int width = sc.nextInt();
					int height = sc.nextInt();
					shapes.add(new Rectangle(centroid, width, height));

				} else if (choice == 4) {
					System.out.println("Input the width:");
					int width = sc.nextInt();
					shapes.add(new Square(centroid, width));

				} else if (choice == 5) {
					System.out.println(
							"How far is the first vertex from the centroid, horizontally and then vertically: ");
					int x1 = sc.nextInt();
					int y1 = sc.nextInt();

					System.out.println(
							"\nHow far is the second vertex from the centroid, compared to the first one (e.g. '2.5' is 2.5 times as far as the first): ");
					double scale = sc.nextDouble();

					shapes.add(new Rhombus(centroid, x1, y1, scale));

				} else if (choice == 6) {

				} else if (choice == 7) {
					System.out.println("Input the width and height: ");
					shapes.add(new Oval(centroid, sc.nextInt(), sc.nextInt()));

				} else if (choice == 8) {
					System.out.println("Input the radius: ");
					shapes.add(new Circle(centroid, sc.nextInt()));
				}

			} else if (choice == 3) {
				display();
				System.out.println("Input shape to remove: ");
				choice = sc.nextInt();
				if (choice > 0 || choice < shapes.size() - 1)
					shapes.remove(choice - 1);
				else
					System.out.println("Couldn't find shape.");
			} else if (choice == 4) {
				display();

				System.out.println("Input shape to modify: ");
				choice = sc.nextInt();

				System.out.println("1. Translate\n2. Scale");
				if ((choice > 0) && (choice - 1 < shapes.size())) {
					if ((shapes.get(choice - 1) instanceof Rectangle) || (shapes.get(choice - 1) instanceof Oval)) {
						if (shapes.get(choice - 1) instanceof Circle) {
							System.out.println("3. Change Radius");
						} else {
							System.out.println("3. Change Width");
							if (!(shapes.get(choice - 1) instanceof Square)) {
								System.out.println("4. Change Height");
							}
						}

					} else if (shapes.get(choice - 1) instanceof Rhombus) {
						System.out.println("3. Change Vertices");
					} else if ((shapes.get(choice - 1) instanceof Parallelogram)
							|| (shapes.get(choice - 1) instanceof Triangle)) {
						for (int i = 0; i < shapes.get(choice - 1).getVertices().length; i++) {
							System.out.println((i + 3) + ". Change Vertex ("
									+ (shapes.get(choice - 1).getVertices()[i].x
											+ shapes.get(choice - 1).getCentroid().x)
									+ ", " + (shapes.get(choice - 1).getVertices()[i].y
											+ shapes.get(choice - 1).getCentroid().y)
									+ ")");
						}
					}

					System.out.println("Input your choice: ");
					int choice2 = sc.nextInt();
					if (choice2 == 1) {
						System.out.println("Input the x and y translation: ");
						int translateX = sc.nextInt();
						int translateY = sc.nextInt();
						shapes.get(choice - 1).translate(translateX, translateY);

					} else if (choice2 == 2) {
						System.out.println("Input the scale factor (e.g. '2.5' to increase size by 2.5x): ");
						double scale = sc.nextDouble();
						shapes.get(choice - 1).scale(scale);

					} else if ((choice2 == 3) && ((shapes.get(choice - 1) instanceof Rectangle)
							|| (shapes.get(choice - 1) instanceof Oval))) {
						if (shapes.get(choice - 1) instanceof Circle) {
							System.out.println("Input new radius: ");
							((Circle) shapes.get(choice - 1)).setWidth(sc.nextInt());
						} else if (shapes.get(choice - 1) instanceof Oval) {
							System.out.println("Input new width: ");
							((Oval) shapes.get(choice - 1)).setWidth(sc.nextInt());
						} else if (shapes.get(choice - 1) instanceof Rectangle) {
							System.out.println("Input new width: ");
							((Rectangle) shapes.get(choice - 1)).setWidth(sc.nextInt());
						}

					} else if ((choice2 == 4) && ((shapes.get(choice - 1) instanceof Rectangle)
							|| (shapes.get(choice - 1) instanceof Oval))) {
						System.out.println("Input new height: ");
						if (shapes.get(choice - 1) instanceof Oval) {
							((Oval) shapes.get(choice - 1)).setHeight(sc.nextInt());
						} else if (shapes.get(choice - 1) instanceof Rectangle) {
							((Rectangle) shapes.get(choice - 1)).setHeight(sc.nextInt());
						}

					} else if ((choice2 == 3) && (shapes.get(choice - 1) instanceof Rhombus)) {
						System.out.println(
								"How far is the new first vertex from the centroid, horizontally and then vertically: ");
						int x1 = sc.nextInt();
						int y1 = sc.nextInt();

						System.out.println(
								"\nHow far is the new second vertex from the centroid, compared to the new first vertex (e.g. '2.5' is 2.5 times as far as the first): ");
						double scale = sc.nextDouble();

						shapes.get(choice - 1)
								.setVertices(new Point[] { new Point(x1, y1),
										new Point(((int) (y1 * scale)), ((int) (-x1 * scale))), new Point(-x1, -y1),
										new Point(-((int) (y1 * scale)), -((int) (-x1 * scale))) });

					} else if (!(shapes.get(choice - 1) instanceof Rhombus)
							&& ((shapes.get(choice - 1) instanceof Parallelogram)
									|| (shapes.get(choice - 1) instanceof Triangle))) {

						if ((choice2 > 2) && (choice2 - 3 < shapes.get(choice - 1).getVertices().length)) {
							if (choice2 > 4) {
								choice2 -= 2;
							}
							System.out.println("Input new vertex (x, y): ");
							shapes.get(choice - 1)
									.setVertex(
											new Point(sc.nextInt() - shapes.get(choice - 1).getCentroid().x,
													sc.nextInt() - shapes.get(choice - 1).getCentroid().y),
											choice2 - 3);
						}
					}

				} else {
					System.out.println("Couldn't find shape.");
				}

			} else if (choice == 5)

			{
				System.out.println("Input the x and y translation: ");
				int translateX = sc.nextInt();
				int translateY = sc.nextInt();
				for (int i = 0; i < shapes.size(); i++) {
					shapes.get(i).translate(translateX, translateY);
				}

			} else if (choice == 6) {
				System.out.println("Input the scale factor (double): ");
				double scale = sc.nextDouble();
				for (int i = 0; i < shapes.size(); i++) {
					shapes.get(i).setCentroid(new Point((int) (scale * (shapes.get(i).getCentroid().x - 250) + 250),
							((int) (scale * (shapes.get(i).getCentroid().y - 250) + 250))));
					shapes.get(i).scale(scale);
				}
			} else if (choice == 7) {
				System.out.println("Input the file name to save to: ");
				Serializer.save(sc.next(), shapes);

			} else if (choice == 8) {
				System.out.println("Input the file name to load from: ");
				shapes = Serializer.load(sc.next());

			} else if (choice == 9) {
				sc.close();
				System.exit(0);
			}
			frame.repaint();
		}

	}

	private static void display() {
		Collections.sort(shapes);
		for (int i = 0; i < shapes.size(); i++) {
			System.out.print((i + 1) + ". ");
			System.out.println(shapes.get(i).display() + "\n");
		}
	}

	private static class GeometryScreen {

		private GeometryScreen() {
			frame = new JFrame("Geometry Drawing Program 1.0");
			frame.getContentPane().add(new GraphicsPanel());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(520, 540);
			frame.setVisible(true);
		}

		private static class GraphicsPanel extends JPanel {

			public void paintComponent(Graphics g) {
				setDoubleBuffered(true);
				g.setColor(Color.RED);
				g.drawLine(250, 0, 250, 500);
				g.drawLine(0, 250, 500, 250);
				g.setColor(Color.BLACK);
				for (int i = 0; i < shapes.size(); i++) {
					shapes.get(i).draw(g);
				}
			}
		}
	}
}
