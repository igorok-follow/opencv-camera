import javax.swing.*;

public class OptionPane extends JFrame {

    JTextField field;
    JButton ok;
    JButton cancel = new JButton("Cancel");
    JLabel label = new JLabel("Enter name of the file: ");
    JPanel panel;

    OptionPane() {
        init();
    }

    private void setPanel() {
        panel = new JPanel();

        getContentPane().add(panel);
    }

    private void setOkButton() {
        ok = new JButton("Ok");
        ok.setBounds(50, 30, 30, 30);

        panel.add(ok);
    }

    private void setTextField() {
         field = new JTextField(30);
         field.setBounds(20, 20, 100, 20);

         panel.add(field);
    }

    private void setVisible() {
        setVisible(true);
    }

    private void settingsFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 500, 200, 150);
        setResizable(false);
    }

    private void init() {
        settingsFrame();
    }

}
