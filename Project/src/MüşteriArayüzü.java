import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MüşteriArayüzü extends JFrame {
    private JPanel panel;

    public MüşteriArayüzü() {
        setTitle("Müşteri Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        add(panel);

        JButton btnÜrünEkle = new JButton("Sepete Ürün Ekle");
        btnÜrünEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SepeteÜrünEkleAction().setVisible(true);
            }
        });
        panel.add(btnÜrünEkle);

        JButton btnÜrünSatınAl = new JButton("Ürün Satın Al");
        btnÜrünSatınAl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünSatınAlAction().setVisible(true);
            }
        });
        panel.add(btnÜrünSatınAl);

        JButton btnSatınAldıklarım = new JButton("Satın Aldıklarım");
        btnSatınAldıklarım.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SatınAldıklarımıGösterAction().setVisible(true);
            }
        });
        panel.add(btnSatınAldıklarım);

        JButton btnÜrünSil = new JButton("Sepetten Ürün Sil");
        btnÜrünSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SepettenÜrünSilAction().setVisible(true);
            }
        });
        panel.add(btnÜrünSil);

        JButton btnÇıkış = new JButton("Çıkış");
        btnÇıkış.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	satınAlınanlarıTemizle();
                sepetiTemizle();
                dispose(); 
                new ArelGiyimGUI().setVisible(true);
            }
        });
        panel.add(btnÇıkış);

        setVisible(true);
    }
    
    private void sepetiTemizle() {
        String sepetDosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(sepetDosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] ürünBilgileri = satır.split(",");
                String ürünAdı = ürünBilgileri[1];
                String beden = ürünBilgileri[2];
                String renk = ürünBilgileri[3];
                int adet = Integer.parseInt(ürünBilgileri[4]);
                stoğaÜrünEkle(ürünAdı, beden, renk, adet); 
                sepettenÜrünSil(ürünAdı, beden, renk); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sepettenÜrünSil(String ürünAdı, String beden, String renk) {
        String sepetDosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt";
        List<String> yeniSepetListesi = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sepetDosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] ürünBilgileri = satır.split(",");
                if (!ürünBilgileri[1].equals(ürünAdı) || !ürünBilgileri[2].equals(beden) || !ürünBilgileri[3].equals(renk)) {
                    yeniSepetListesi.add(satır);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sepetDosyaYolu))) {
            for (String ürünSatır : yeniSepetListesi) {
                writer.write(ürünSatır);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void stoğaÜrünEkle(String ürünAdı, String beden, String renk, int adet) {
        String ürünlerDosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(ürünlerDosyaYolu))) {
            List<String> ürünlerListesi = new ArrayList<>();
            String dosyaSatırı;
            while ((dosyaSatırı = reader.readLine()) != null) {
                String[] urunSatir = dosyaSatırı.split(",");
                if (urunSatir[1].equals(ürünAdı) && urunSatir[2].equals(beden) && urunSatir[3].equals(renk)) {
                    int mevcutStok = Integer.parseInt(urunSatir[4]);
                    urunSatir[4] = String.valueOf(mevcutStok + adet);
                    dosyaSatırı = String.join(",", urunSatir); 
                }
                ürünlerListesi.add(dosyaSatırı);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ürünlerDosyaYolu))) {
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
    }
    
    private void satınAlınanlarıTemizle() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\satınalınanürünler.txt"))) {
            writer.write(""); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MüşteriArayüzü().setVisible(true);
        });
    }
}
