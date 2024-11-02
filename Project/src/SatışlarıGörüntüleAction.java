import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SatışlarıGörüntüleAction implements ActionListener {
    private static final String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\satınalanmüşteribilgileri.txt";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Satışlar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parça = satır.split(",");
                if (parça.length >= 15) {

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JLabel müşteriLabel = new JLabel("Müşteri bilgileri");
                    panel.add(müşteriLabel);
                    JLabel adSoyadLabel = new JLabel("ad-soyad: " + parça[6] + " " + parça[7]);
                    panel.add(adSoyadLabel);
                    JLabel telefonLabel = new JLabel("telefon: " + parça[14]);
                    panel.add(telefonLabel);
                    JLabel boşlukLabel = new JLabel("   ");
                    panel.add(boşlukLabel);
                    JLabel bilgiLabel = new JLabel("Aldığı ürün bilgileri");
                    panel.add(bilgiLabel);
                    JLabel ürünLabel = new JLabel("Ürün adı: " + parça[1] + " | Ürün bedeni: " + parça[2] + " | Ürün rengi: " + parça[3]);
                    panel.add(ürünLabel);
                    JLabel fiyatLabel = new JLabel("Ürün fiyatı: " + parça[5]);
                    panel.add(fiyatLabel);
                    JLabel adetLabel = new JLabel("Satın alınan adet: " + parça[4]);
                    panel.add(adetLabel);
                    JLabel boşluk2Label = new JLabel("   ");
                    panel.add(boşluk2Label);
                    JLabel adressLabel = new JLabel("Adres bilgileri");
                    panel.add(adressLabel);
                    JLabel adresBilgileriLabel = new JLabel("Adres başlığı: " + parça[8] + " | İl-İlçe: " + parça[9] + "/" + parça[10] + " | Mahalle: " + parça[11]);
                    panel.add(adresBilgileriLabel);
                    JLabel postaKodLabel = new JLabel("Posta Kodu: " + parça[12]);
                    panel.add(postaKodLabel);
                    JLabel adresLabel = new JLabel("Adres: " + parça[13]);
                    panel.add(adresLabel);
                    JLabel boşluk3Label = new JLabel("   ");
                    panel.add(boşluk3Label);
                    JLabel düzÇizgiLabel = new JLabel("                            ----------------------------------------------");
                    panel.add(düzÇizgiLabel);

                    panel.add(Box.createVerticalStrut(10));

                    mainPanel.add(panel);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
