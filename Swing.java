import javax.swing.*;
import java.awt.*;

/**
 * A template Swing JFrame class for creating different button layouts.
 * 
 * This class can generate:
 * - A "wasd" control layout (with directional buttons and placeholders).
 * - A single button layout.
 * - A default empty panel if no recognized layout type is given.
 *
 * @author Kaleb
 * @version javax.swing template class
 */
public class Swing extends JFrame {
    private int selected = 0;
    private int turtles = 10;

    /**
     * Creates a Swing window with a title, layout type, and optional button label.
     *
     * @param tlt  the window title
     * @param type the type of layout ("wasd", "one", or default)
     * @param btn  the label for the button if using the "one" layout
     */
    public Swing(String tlt,String type,String btn) {           
        defaultValues(tlt);

        JPanel MP = (type == "wasd") ? wasd() : 
                    (type == "one") ? oneButton(btn) : 
                    new JPanel();
        add(MP);
        
    }

    /**
     * Creates a Swing window with a title and layout type (no button label).
     *
     * @param tlt  the window title
     * @param type the type of layout ("wasd", "one", or default)
     */
    public Swing(String tlt,String type) {this(tlt,type,"N/A");}

    /**
     * Creates a Swing window with just a title (default layout and no button label).
     *
     * @param tlt  the window title
     */
    public Swing(String tlt) {this(tlt,"N/A","N/A");}

    /**
     * Creates a Swing window with default values (no title, no layout, no button).
     */
    public Swing() {this("N/A","N/A","N/A");}

    /**
     * Sets up default window values like title, close behavior, location, etc.
     *
     * @param tlt the window title
     */
    private void defaultValues(String tlt){
        setTitle(tlt);
        setVisible(true);           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   
        setLocation(0,0);
        setResizable(false);
    }

    /**
     * Creates a "wasd" style panel with 3 rows:
     * Row 1: left, up, right
     * Row 2: left, down, right
     * Row 3: placeholder buttons if unchanged close program
     *
     * @return JPanel containing the "wasd" control layout
     */
    private JPanel wasd(){
        setSize(377, 124); 
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        
        panel1.add(makeClrButton("l"));
        panel1.add(makeTxtButton("up"));
        panel1.add(makeClrButton("r"));
        panel2.add(makeTxtButton("left"));
        panel2.add(makeTxtButton("down"));
        panel2.add(makeTxtButton("right"));
        panel3.add(makeClrButton());
        panel3.add(makeClrButton());
        panel3.add(makeClrButton());
        
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        
        return mainPanel;
    }
    
    /**
     * Creates a panel containing a single button with a given label.
     *
     * @param tlt the text label for the button
     * @return JPanel containing the single button
     */
    private JPanel oneButton(String tlt){
        setSize(136, 77); 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(makeTxtButton(tlt));
        return panel;
    }

    /**
     * Creates a JButton with a background color no text and mouse hover/press effects.
     * * To make it call a certain function alter the handler method
     * by default will out put "name" not found
     *
     * @param clr The base color of the button
     * @param str The name of the button
     * @return The customized JButton
     */
    public JButton makeClrButton(Color clr,String str){
        JButton btn = new JButton(" ");
        btn.setName(str);
        btn.setBackground(clr);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension size = new Dimension(120, 40);
        btn.setPreferredSize(size);
        btn.setMinimumSize(size);
        btn.setMaximumSize(size);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true); 
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(clr, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        btn.addActionListener(e -> handler(btn.getName()));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(lighten(clr,0.2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(clr);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(darken(clr, 0.2));
            }
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(clr);
            }
        });

        return btn;
    }

    /**
     * Creates a JButton with a background color no text and mouse hover/press effects.
     * * To make it call a certain function alter the handler method
     * by default will out put "name" not found
     *
     * Background will defualt to 25, 90, 220 (blue)
     * 
     * @param str The name of the button
     * @return The customized JButton
     */
    public JButton makeClrButton(String str) {Color clr = new Color(25, 90, 220);return makeClrButton(clr,str);}

    /**
     * Creates a JButton with a background color no text and mouse hover/press effects.
     * * To make it call a certain function alter the handler method
     * by default will out put "name" not found
     *
     * Background will defualt to 25, 90, 220 (blue)
     * 
     * @return The customized JButton
     */
    public JButton makeClrButton() {Color clr = new Color(25, 90, 220);return makeClrButton(clr,"Color " + clr.getRed() + " " + clr.getGreen() + " " + clr.getBlue());}

    /**
     * Creates a JButton with text and mouse hover/press effects.
     * * To make it call a certain function alter the handler method
     * by default will out put "name" not found
     *
     * @param txt The text to display on the button
     * @param clr The color background on the button
     * @return The customized JButton
     */
    public JButton makeTxtButton(String txt,Color clr) {
        JButton btn = new JButton(txt);
        btn.setName(txt);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setBackground(clr);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension size = new Dimension(120, 40);
        btn.setPreferredSize(size);
        btn.setMinimumSize(size);
        btn.setMaximumSize(size);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true); 
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(clr, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        btn.addActionListener(e -> handler(txt));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(lighten(clr, 0.2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(clr);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(darken(clr, 0.2));
            }
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(clr);
            }
        });
        return btn;
    }

    /**
     * Creates a JButton with text and mouse hover/press effects.
     * * To make it call a certain function alter the handler method
     * by default will out put "name" not found.
     *
     * Background will defualt to 25, 90, 220 (blue)
     * 
     * @param txt The text to display on the button
     * @return The customized JButton
     */
    public JButton makeTxtButton(String txt) {return makeTxtButton(txt,new Color(25, 90, 220));}

    /**
     * Handles button actions based on the button's name. Needs to be preset to buttons.
     *
     * @param lbl The name of the button clicked
     */
    private void handler(String lbl) {
        switch (lbl) {
            case "Color 25 90 220":
                System.exit(0);
                break;
            case "r":
                selected = (selected + 1 > turtles - 1) ? 0 : selected + 1;
                System.out.println(selected);
                break;
            case "l":
                selected = (selected - 1 < 0) ? turtles - 1 : selected - 1;
                System.out.println(selected);
                break;
            case "up":
                TurtleDrawingKaleb.grab(selected,1);
                break;
            case "left":
                TurtleDrawingKaleb.grab(selected,2);
                break;
            case "down":
                TurtleDrawingKaleb.grab(selected,3);
                break;
            case "right":
                TurtleDrawingKaleb.grab(selected,4);
                break;
            case "star":
                TurtleDrawingKaleb.grab(selected,5);
                break;
            default:
                System.out.println(lbl + " clicked");
                System.out.println(lbl + " function not found for button");
        }
    }

    /**
     * Lightens a given color by a factor.
     * *AI CREATED THIS FUNCTION
     * @param color  Base color
     * @param factor Amount to lighten (0.0 to 1.0)
     * @return The lightened color
     */
    public Color lighten(Color color, double factor) {
        int r = (int) Math.min(255, color.getRed()   + 255 * factor);
        int g = (int) Math.min(255, color.getGreen() + 255 * factor);
        int b = (int) Math.min(255, color.getBlue()  + 255 * factor);
        return new Color(r, g, b);
    }

    /**
     * Darkens a given color by a factor.
     * *AI CREATED THIS FUNCTION
     * @param color  Base color
     * @param factor Amount to darken (0.0 to 1.0)
     * @return The darkened color
     */
    public Color darken(Color color, double factor) {
        int r = (int) Math.max(0, color.getRed()   - 255 * factor);
        int g = (int) Math.max(0, color.getGreen() - 255 * factor);
        int b = (int) Math.max(0, color.getBlue()  - 255 * factor);
        return new Color(r, g, b);
    }
}