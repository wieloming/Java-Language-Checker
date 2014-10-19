import javax.swing.*;

public class LanguageChecker {

    public static void main(String[] args) {
        JFrame window = new JFrame("LanguageChecker");

        Display frame = new Display();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(frame);
        window.setResizable(false);
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        window.pack();
    }
}
