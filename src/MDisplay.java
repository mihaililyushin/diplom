import javax.swing.*;

public class MDisplay {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Meat day");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,760);
        frame.add(new MMain(frame));
        frame.setVisible(true);


    }
}