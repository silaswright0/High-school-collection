/*
 * B Cutten
 * Nov 2021
 * An example of the Graphics 2D in Java, code from http://zetcode.com/gfx/java2d/
 */

package graphics2dexample;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Graphics2DExample extends JFrame {

    
    /**
     * Default constructor
     */
    public Graphics2DExample() {
        //create the User interface
        initUI();
    }

    
    /**
     * Create the custom JFrame and set some options
     */
    private void initUI() {        
        //set title of the JFrame
        setTitle("Graphics excersise 1");
        //add a custom JPanel to draw on
        add(new DrawingSurface());
        //set the size of the window
        setSize(300, 200);
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //make sure that all UI updates are concurrency safe (related to multi threading)
        //much more detailed http://www.javamex.com/tutorials/threads/invokelater.shtml
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //instantiate the main window
                Graphics2DExample windowFrame = new Graphics2DExample();
                //make sure it can be seen
                windowFrame.setVisible(true);
            }
        });
    }
}
