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

public class SepeteÜrünEkleAction extends JFrame {
    private JPanel panel;
    private List<String> ürünlerListesi;
    private int index; 

    public SepeteÜrünEkleAction() {
        setTitle("Sepete Ürün Ekle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(new JScrollPane(panel));

        ürünlerListesi = new ArrayList<>();
        ürünleriDosyadanOku();

        index = 0; 
        for (String ürün : ürünlerListesi) {
            String[] ürünBilgileri = ürün.split(",");
            String ürünFormat = "Ürün Ad: " + ürünBilgileri[1] + " - Beden: " + ürünBilgileri[2] + " - Renk: " + ürünBilgileri[3] + " - Stok: " + ürünBilgileri[4] + " - Fiyat(TL): " + ürünBilgileri[5];
            JButton ürünButon = new JButton(ürünFormat);
            ürünButon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sepeteEkle(ürün);
                }
            });
            panel.add(ürünButon);
            index++;
        }

        setVisible(true);
    }

    private void ürünleriDosyadanOku() {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                ürünlerListesi.add(satır);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sepeteEkle(String ürün) {
        String[] ürünBilgileri = ürün.split(",");
        String ürünAdı = ürünBilgileri[1];
        String beden = ürünBilgileri[2];
        String renk = ürünBilgileri[3];
        int stok = Integer.parseInt(ürünBilgileri[4]);
        int fiyat = Integer.parseInt(ürünBilgileri[5]);

        String adetStr = JOptionPane.showInputDialog(null, "Kaç adet satın almak istiyorsunuz?");
        int adet = Integer.parseInt(adetStr);
        dispose();

        if (adet <= stok) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\sepettekiürünler.txt", true));
                 BufferedWriter ürünWriter = new BufferedWriter(new FileWriter("C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt"))) {

                stok -= adet;

                String yeniÜrün = index + "," + ürünAdı + "," + beden + "," + renk + "," + stok + "," + fiyat;

                ürünWriter.write(yeniÜrün);
                ürünWriter.newLine();

                yeniÜrün = index + "," + ürünAdı + "," + beden + "," + renk + "," + adet + "," + fiyat;
                writer.write(yeniÜrün);
                writer.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Ürün sepete eklendi.");
        } else {
            JOptionPane.showMessageDialog(null, "Stokta yeterli ürün yok!");
        }
    }

    public static void main(String[] args) {
        new SepeteÜrünEkleAction();
    }
}
