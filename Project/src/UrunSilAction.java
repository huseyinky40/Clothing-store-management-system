import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UrunSilAction implements ActionListener {
    private static final String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Mağazadan Ürün Sil");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400); 
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); 

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length >= 6) {
                    JButton silButton = new JButton("Ürün Ad: " + parcalar[1] +
                            " - Beden: " + parcalar[2] +
                            " - Renk: " + parcalar[3] +
                            " - Stok: " + parcalar[4] +
                            " - Fiyat(TL): " + parcalar[5]);
                    
                    String urunAdi = parcalar[1];
                    silButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            urunSil(urunAdi);
                            frame.dispose();
                            JOptionPane.showMessageDialog(null, urunAdi + " ürünü başarıyla silindi.");
                        }
                    });

                    panel.add(silButton);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void urunSil(String urunAdi) {
        List<String> guncelUrunler = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (!parcalar[1].equals(urunAdi)) {
                    guncelUrunler.add(satir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dosyayı güncellemek için yeniden yaz
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU))) {
            for (String urun : guncelUrunler) {
                writer.write(urun);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
