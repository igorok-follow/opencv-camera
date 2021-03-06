import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CameraPanel extends JPanel implements Runnable, ActionListener {

    BufferedImage bufferedImage;
    VideoCapture videoCapture;
    String str;
    JPanel panel = new JPanel();
    JButton test = new JButton("\u058E");
    String string;
    File file;
    int counter = 0;

    CameraPanel() {
        addComponentsOnFrame();
        fileSaver();
        revalidate();
    }

    private void fileSaver() {
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                counter = random.nextInt(10000);
                file = new File(String.valueOf(counter + ".png"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


                try {
                    ImageIO.write(bufferedImage, "png", file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                string = JOptionPane.showInputDialog(null, "Enter the file name: ", "Input", JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.YES_OPTION == 1) {
                    File file1 = new File(string + ".png");

                    if (file.exists()) {
                        System.out.println("File " + counter + ".png exists");
                    }

                    boolean succsess = file.renameTo(file1);

                    if (!succsess) {
                        System.out.println("File " + file.getName() + "was not renamed!");
                    }
                } else {
                    if (file.delete()) {
                        System.out.println(file.getName() + " was deleted");
                        System.out.println(string);
                    } else {

                    }
                }
            }
        });
    }

    private void addComponentsOnFrame() {
        setLayout(null);
        JLabel label = new JLabel("Version 0.1.0");
        label.setForeground(Color.WHITE);
        label.setSize(100, 10);
        label.setBounds(270, 10, 100, 10);
        add(label);

        test.setBounds(590, 180, 45, 40);

        add(test);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        System.loadLibrary("opencv_java410");
        videoCapture = new VideoCapture(0);
        Mat webcam_img = new Mat();

        if (videoCapture.isOpened()) {
            while (true) {
                videoCapture.read(webcam_img);
                if (!webcam_img.empty()) {
                    MatToBufferedImage(webcam_img);
                    repaint();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (bufferedImage == null) {
            return;
        }

        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }



    public void MatToBufferedImage(Mat matBGR) {
        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
        byte[] src = new byte[width * height * channels];

        matBGR.get(0, 0, src);

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] target = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        System.arraycopy(src, 0, target, 0, src.length);
    }
}
