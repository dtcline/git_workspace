import javax.swing.JFrame;


public class Example extends JFrame {
    
    public Example() {
        setTitle("Simple example");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Example();
    }

}
