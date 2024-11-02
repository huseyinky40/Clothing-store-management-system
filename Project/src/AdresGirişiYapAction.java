import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AdresGirişiYapAction extends JFrame {
    private JTextField adresBaşlığıField;
    private JTextField şehirField;
    private JTextField ilçeField;
    private JTextField mahalleField;
    private JTextField postaKoduField;
    private JTextField adresField;
    private JTextField telefonField;
    private JTextField adField;
    private JTextField soyadField;
    private String ürünBilgisi;

    public AdresGirişiYapAction(String ürün) {
        this.ürünBilgisi = ürün;
        
        setTitle("Adres Bilgileri");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 10, 10));
        add(panel);

        panel.add(new JLabel("Müşteri Bilgileri", SwingConstants.CENTER));
        panel.add(new JLabel(""));

        panel.add(new JLabel("Ad:"));
        adField = new JTextField();
        panel.add(adField);

        panel.add(new JLabel("Soyad:"));
        soyadField = new JTextField();
        panel.add(soyadField);

        panel.add(new JLabel("Telefon:"));
        telefonField = new JTextField();
        panel.add(telefonField);

        panel.add(new JLabel("Adres Bilgileri", SwingConstants.CENTER));
        panel.add(new JLabel("")); 

        panel.add(new JLabel("Adres Başlığı:"));
        adresBaşlığıField = new JTextField();
        panel.add(adresBaşlığıField);

        panel.add(new JLabel("Şehir:"));
        şehirField = new JTextField();
        panel.add(şehirField);

        panel.add(new JLabel("İlçe:"));
        ilçeField = new JTextField();
        panel.add(ilçeField);

        panel.add(new JLabel("Mahalle:"));
        mahalleField = new JTextField();
        panel.add(mahalleField);

        panel.add(new JLabel("Posta Kodu:"));
        postaKoduField = new JTextField();
        panel.add(postaKoduField);

        panel.add(new JLabel("Adres:"));
        adresField = new JTextField();
        panel.add(adresField);

        JButton satınAlButon = new JButton("Satın Al");
        satınAlButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                satınAl();
            }
        });
        panel.add(satınAlButon);
        panel.add(new JLabel("")); 

        setVisible(true);
    }

    private void satınAl() {
        String adresBaşlığı = adresBaşlığıField.getText();
        String ad = adField.getText();
        String soyad = soyadField.getText();
        String şehir = şehirField.getText();
        String ilçe = ilçeField.getText();
        String mahalle = mahalleField.getText();
        String postaKodu = postaKoduField.getText();
        String adres = adresField.getText();
        String telefon = telefonField.getText();
        

        String ürünBilgisiTam = ürünBilgisi + "," + ad + "," + soyad + "," + adresBaşlığı + "," + şehir + "," + ilçe + "," + mahalle + "," + postaKodu + "," + adres + "," + telefon;
        kaydetSatinAlinanMusteriBilgisi(ürünBilgisiTam);

        if (!adresBaşlığı.isEmpty() && !şehir.isEmpty() && !ilçe.isEmpty() && !mahalle.isEmpty() && !postaKodu.isEmpty() && !adres.isEmpty() && !telefon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Satın alma işlemi başarılı");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz.");
        }
    }
    
    private void kaydetSatinAlinanMusteriBilgisi(String bilgi) {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\satınalanmüşteribilgileri.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu, true))) {
            writer.write(bilgi);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AdresGirişiYapAction("");
    }
}
