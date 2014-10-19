import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class Display extends JPanel {

    private int WIDTH = 500;
    private int HEIGHT = 600;
    private JTextArea textArea;

    public Display() {
        super();
        setLayout(new GridLayout(5,1));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        textArea = new JTextArea(15, 85);
        textArea.requestFocus();
        textArea.setFont(new Font("Arial", Font.BOLD, 12));
        add(textArea);

        JLabel label = new JLabel("wynik: ");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        final TextField translationResult = new TextField();
        translationResult.setEditable(false);
        translationResult.setSize(new Dimension(100, 20));

        JButton button1 = new JButton("Check by letter frequency");
        JButton button2 = new JButton("Check by dictionary");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textArea.getText();
                LanguageEngine engine = new LanguageEngine(input);
                translationResult.setText(engine.translateByLetterFreq());
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textArea.getText();
                LanguageEngine engine = new LanguageEngine(input);
                translationResult.setText(engine.dictionaryTranslate());
            }
        });
        add(button1);
        add(button2);
        add(label);
        add(translationResult);
    }


}
