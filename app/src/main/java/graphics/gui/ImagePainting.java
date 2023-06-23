package graphics.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePainting extends JPanel {
    private Image image;

    public ImagePainting(Image image) {
        super();

        this.image = image;

        setBackground(Color.PINK);
    }

    public void setImage(Image image) {
        this.image = image;

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * @param argbMatrix The location of a pixel corresponds to
     *                   (0, y) is on the left edge and (x, 0) is on the top.
     * @return
     */
    public static BufferedImage intArgbGridToImage(int[][] argbMatrix) {
        BufferedImage image = new BufferedImage(argbMatrix[0].length, argbMatrix.length, BufferedImage.TYPE_INT_ARGB);

        int[] flattenedGrid = Arrays.stream(argbMatrix).flatMapToInt(Arrays::stream).toArray();
        int[] imageData = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        System.arraycopy(flattenedGrid, 0, imageData, 0, flattenedGrid.length);

        return image;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new ImagePainting(
                intArgbGridToImage(new int[][] { { 0xFF000000, 0xFFFFFFFF }, { 0x00000000, 0x00000000 } })));

        frame.setSize(540, 540);
        frame.setVisible(true);
    }
}
