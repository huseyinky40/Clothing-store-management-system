import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FiyatGüncelleAction implements ActionListener {
    private static final String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Ürün Fiyatı Güncelle");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); 

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parçalar = satır.split(",");
                if (parçalar.length >= 6) {
                    JButton güncelleButon = new JButton("Ürün Ad: " + parçalar[1] +
                            " - Beden: " + parçalar[2] +
                            " - Renk: " + parçalar[3] +
                            " - Stok: " + parçalar[4] +
                            " - Fiyat(TL): " + parçalar[5]);

                    String ürünAdı = parçalar[1];
                    String beden = parçalar[2];
                    String renk = parçalar[3];

                    güncelleButon.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String yeniFiyatString = JOptionPane.showInputDialog(null, ürünAdı + " için yeni fiyatı girin:", "Fiyat Güncelle", JOptionPane.PLAIN_MESSAGE);
                            if (yeniFiyatString != null) {
                                try {
                                    int yeniFiyat = Integer.parseInt(yeniFiyatString);
                                    urunFiyatGuncelle(ürünAdı, beden, renk, yeniFiyat);
                                    JOptionPane.showMessageDialog(null, ürünAdı + " ürününün fiyatı başarıyla güncellendi.");
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Geçersiz fiyat girdiniz. Lütfen sayısal bir değer girin.");
                                }
                                frame.dispose();
                                new YöneticiArayüzü().setVisible(true);
                            }
                        }
                    });

                    panel.add(güncelleButon);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void urunFiyatGuncelle(String ürünAdı, String beden, String renk, int yeniFiyat) {
        List<String> güncelÜrünler = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parça = satır.split(",");
                if (parça[1].equals(ürünAdı) && parça[2].equals(beden) && parça[3].equals(renk)) {
                    parça[5] = String.valueOf(yeniFiyat);
                    satır = String.join(",", parça);
                }
                güncelÜrünler.add(satır);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU))) {
            for (String ürün : güncelÜrünler) {
                writer.write(ürün);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
