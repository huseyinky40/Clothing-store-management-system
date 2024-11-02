import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StokKontrolAction implements ActionListener {
    private static final String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Stok Kontrolü");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parça = satır.split(",");
                if (parça.length >= 5) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JLabel ürünLabel = new JLabel("Ürün Adı: " + parça[1]);
                    panel.add(ürünLabel);
                    JLabel bedenLabel = new JLabel("Beden: " + parça[2]);
                    panel.add(bedenLabel);
                    JLabel renkLabel = new JLabel("Renk: " + parça[3]);
                    panel.add(renkLabel);
                    JLabel stokLabel = new JLabel("Stok: " + parça[4]);
                    panel.add(stokLabel);

                    // Boşluk ekleyerek panele ekle
                    panel.add(Box.createVerticalStrut(10));

                    frame.add(panel);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        frame.setVisible(true);
    }
}
