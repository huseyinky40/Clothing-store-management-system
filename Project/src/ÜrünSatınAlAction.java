import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ÜrünSatınAlAction extends JFrame {
    private JPanel panel;
    private List<String> sepetUrunleriListesi;

    public ÜrünSatınAlAction() {
        setTitle("Ürün Satın Al");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(new JScrollPane(panel));

        sepetUrunleriListesi = new ArrayList<>();
        urunleriSepettenOku();

        for (String urun : sepetUrunleriListesi) {
            String[] urunBilgileri = urun.split(",");
            if (urunBilgileri.length >= 6) {
                String urunFormat = "Ürün Ad: " + urunBilgileri[1] + " - Beden: " + urunBilgileri[2] + " - Renk: " + urunBilgileri[3] + " - Adet: " + urunBilgileri[4] + " - Fiyat: " + urunBilgileri[5];
                JButton urunButton = new JButton(urunFormat);
                urunButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Seçtiğiniz ürünü satın almak istiyor musunuz?", "Satın Alma", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                        	dispose();
                            odemeEkrani(urun);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ürün satın alma işlemini iptal ettiniz.");
                        }
                    }
                });
                panel.add(urunButton);
            }
        }

        setVisible(true);
    }

    private void urunleriSepettenOku() {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                sepetUrunleriListesi.add(satir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void odemeEkrani(String urun) {
        JFrame odemeFrame = new JFrame("Ödeme Ekranı");
        odemeFrame.setSize(300, 200);
        odemeFrame.setLayout(new GridLayout(4, 2));
        odemeFrame.setLocationRelativeTo(null);

        JLabel kartNumarasiLabel = new JLabel("Kart Numarası:");
        JTextField kartNumarasiField = new JTextField();
        JLabel cvvLabel = new JLabel("CVV:");
        JTextField cvvField = new JTextField();
        JLabel sonKullanmaTarihiLabel = new JLabel("Son Kullanma Tarihi:");
        JTextField sonKullanmaTarihiField = new JTextField();

        JButton odemeYapButton = new JButton("Ödeme Yap");
        JButton iptalEtButton = new JButton("İptal Et");

        odemeYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kartNumarasi = kartNumarasiField.getText();
                String cvv = cvvField.getText();
                String sonKullanmaTarihi = sonKullanmaTarihiField.getText();

                if (Ödeme.krediKartıylaÖdemeYap(kartNumarasi, sonKullanmaTarihi, cvv)) {
                    sepettenUrunSil(urun);
                    kaydetSatinAlinanUrun(urun);
                    JOptionPane.showMessageDialog(null, "Ödeme başarılı.");
                    odemeFrame.dispose();
                    new AdresGirişiYapAction(urun).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Ödeme bilgileriniz hatalı, lütfen kontrol ediniz.");
                }
            }
        });

        iptalEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odemeFrame.dispose();
            }
        });

        odemeFrame.add(kartNumarasiLabel);
        odemeFrame.add(kartNumarasiField);
        odemeFrame.add(cvvLabel);
        odemeFrame.add(cvvField);
        odemeFrame.add(sonKullanmaTarihiLabel);
        odemeFrame.add(sonKullanmaTarihiField);
        odemeFrame.add(odemeYapButton);
        odemeFrame.add(iptalEtButton);

        odemeFrame.setVisible(true);
    }

    private void sepettenUrunSil(String urun) {
        sepetUrunleriListesi.remove(urun);
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu))) {
            for (String sepetUrunu : sepetUrunleriListesi) {
                writer.write(sepetUrunu);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void kaydetSatinAlinanUrun(String urun) {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\satınalınanürünler.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu, true))) {
            writer.write(urun);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ÜrünSatınAlAction();
    }
}
