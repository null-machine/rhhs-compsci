
// graphics
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;

// gui
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;

// listeners
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// util
import java.util.ArrayList;

/**
 * Editor.java
 * a toolkit for floor plan manipulation 
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/
class Editor extends JPanel implements ActionListener {
    
    private Renderer renderer; // ref for search function
    private ArrayList<Rectangle> tableRects;
    private ArrayList<Rectangle> miscRects;
    private ArrayList<Integer> tableTargets;
    private ArrayList<Table> tables;
    
    private JTextField search;
    private JScrollPane scroll;
    private JTextArea info;
    
    /**
     * Editor
     * creates and maintains the left panel of the floor plan
     * @param tableRects - ArrayList of rectangles, representing tables
     * @param miscRects - ArrayList of rectangles, representing drawn rectangles
     * @param tableTargets - ArrayList of rectangles that have been selected
     * @param tables - ArrayList of tables for displayed information
     */
    Editor(ArrayList<Rectangle> tableRects, ArrayList<Rectangle> miscRects, ArrayList<Integer> tableTargets, ArrayList<Table> tables) {
        this.tableRects = tableRects;
        this.renderer = renderer;
        this.tableTargets = tableTargets;
        this.tables = tables;
        this.miscRects = miscRects;
        this.setPreferredSize(new Dimension(320, 720));
        
        search = new JTextField();
        search.addActionListener(this);
        search.setPreferredSize(new Dimension(200, 20));
        search.setActionCommand("search");
        this.add(search);
        
        info = new JTextArea("infooooooo");
        info.setEditable(false);
        
        scroll = new JScrollPane(info);
        scroll.setPreferredSize(new Dimension(200, 600));
        this.add(scroll);
    }
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        setDoubleBuffered(true);
        setBackground(new Color(62, 55, 53)); 
        repaint();
    }
    
    /**
     * actionPerformed
     * pans the searched rectangle into the center of the screen
     * @param e - ActionEvent storing information about the search text
     */
    public void actionPerformed(ActionEvent e) {
        
        tableTargets.clear();
        int input = Integer.parseInt(search.getText()) - 1;
        if((input >= 0) && (input <= tableRects.size() - 1)) {
            int x = (int)tableRects.get(input).getX();
            int y = (int)tableRects.get(input).getY();
            tableTargets.add(input);
            
            
            for(Rectangle rect : tableRects) {
                rect.translate(480 - x - 35, 360 - y - 35);
            }
            for(Rectangle rect : miscRects) {
                rect.translate(480 - x - 35, 360 - y - 35);
            }
        }
        
    }
    
    /**
     * update
     * Updates the information about students in the selected table.
     * @param tableTargets - ArrayList of rectangles that have been selected
     */
    public void update(ArrayList<Integer> tableTargets) {
        String displayInfo = "";
        if(tableTargets.size() == 1) {
            ArrayList<Student> students = tables.get(tableTargets.get(0)).getStudents();
            for(Student student : students) {
                displayInfo += student.getName() + "\n";
                displayInfo += student.getStudentNumber() + "\nDietary Restrictions:\n";
                for(String dietRes : student.getDietaryRestrictions()) {
                    displayInfo += dietRes + "\n";
                }
                displayInfo += "\n\n";
            }
        } else {
            displayInfo = "A- Move and Inspect Tool\nS- Selection Box Tool\nD- Draw Rectangle Tool\nCtrl+S - Save\nCtrl+L - Load\nSpace- Hold to Pan\nCtrl+Z - undo";
        }
        
        info.setText(displayInfo);
    }
}

