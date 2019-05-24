import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CameraFrame extends JFrame implements ActionListener {

    CameraPanel cameraPanel;
    JMenu menu = new JMenu("Change Camera (don't work in this version)");
    JMenuBar bar = new JMenuBar();
    JMenuItem menuItem = new JMenuItem("Camera");

    CameraFrame() {
        System.loadLibrary("opencv_java410");
        VideoCapture capture = new VideoCapture(0);

        cameraPanel = new CameraPanel();
        add(cameraPanel);
        Thread thread = new Thread(cameraPanel);

        bar.add(menu);
        menu.add(menuItem);
        setJMenuBar(bar);

        int i = 1;
        while (capture.isOpened()) {
            capture.release();
            capture = new VideoCapture(i);
            i++;
        }

        thread.start();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(650, 500));
        setTitle("Web-cam picture");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        CameraFrame cameraFrame = new CameraFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
