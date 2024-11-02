import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SepettenÜrünSilAction extends JFrame {
    private JPanel panel;
    private List<String> sepetÜrünleriListesi;

    public SepettenÜrünSilAction() {
        setTitle("Sepetten Ürün Sil");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(new JScrollPane(panel));

        sepetÜrünleriListesi = new ArrayList<>();
        ürünleriSepettenOku();

        for (String ürün : sepetÜrünleriListesi) {
            String[] ürünBilgileri = ürün.split(",");
            if (ürünBilgileri.length >= 4) { 
                String ürünFormat = "Ürün Ad: " + ürünBilgileri[1] + " - Beden: " + ürünBilgileri[2] + " - Renk: " + ürünBilgileri[3] + " - Stok: " + ürünBilgileri[4] + " - Fiyat(TL): " + ürünBilgileri[5];
                JButton ürünButon = new JButton(ürünFormat);
                ürünButon.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sepettenSil(ürün);
                    }
                });
                panel.add(ürünButon);
            }
        }

        setVisible(true);
    }

    private void ürünleriSepettenOku() {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
            	sepetÜrünleriListesi.add(satır);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sepettenSil(String ürün) {
        String[] ürünBilgileri = ürün.split(",");
        if (ürünBilgileri.length >= 4) { 
            String ürünAdı = ürünBilgileri[1];
            String beden = ürünBilgileri[2];
            String renk = ürünBilgileri[3];
            int adet = Integer.parseInt(ürünBilgileri[4]);

            sepetÜrünleriListesi.remove(ürün);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt"))) {
                for (String ürünSatır : sepetÜrünleriListesi) {
                    writer.write(ürünSatır);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt"))) {
                List<String> ürünlerListesi = new ArrayList<>();
                String satır;
                while ((satır = reader.readLine()) != null) {
                    String[] ürünSatır = satır.split(",");
                    if (ürünSatır[1].equals(ürünAdı) && ürünSatır[2].equals(beden) && ürünSatır[3].equals(renk)) {
                        int mevcutStok = Integer.parseInt(ürünSatır[4]);
                        ürünSatır[4] = String.valueOf(mevcutStok + adet); 
                        satır = String.join(",", ürünSatır);
                    }
                    ürünlerListesi.add(satır);
                }

                // Ürünler.txt dosyasını güncellenmiş halde yeniden yaz
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt"))) {
                    for (String ürünSatır : ürünlerListesi) {
                        writer.write(ürünSatır);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Ürün sepetten silindi.");
            dispose();
            new SepettenÜrünSilAction(); 
            dispose();
            
        } else {
            JOptionPane.showMessageDialog(null, "Ürün bilgileri eksik.");
        }
    }
}
