import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SatınAldıklarımıGösterAction extends JFrame {
    private JPanel panel;
    private JTextArea textArea;
    private List<String> satınAlınanÜrünlerListesi;

    public SatınAldıklarımıGösterAction() {
        setTitle("Satın Aldıklarım");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        satınAlınanÜrünlerListesi = new ArrayList<>();
        satınAlınanÜrünleriOku();

        StringBuilder sb = new StringBuilder();
        for (String ürün : satınAlınanÜrünlerListesi) {
            String[] ürünBilgileri = ürün.split(",");
            if (ürünBilgileri.length >= 6) {
                String ürünFormat = "Ürün Ad: " + ürünBilgileri[1] + " - Beden: " + ürünBilgileri[2] + " - Renk: " + ürünBilgileri[3] + " - Adet: " + ürünBilgileri[4] + " - Fiyat(TL): " + ürünBilgileri[5];
                sb.append(ürünFormat).append("\n");
            }
        }
        textArea.setText(sb.toString());

        setVisible(true);
    }

    private void satınAlınanÜrünleriOku() {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\satınalınanürünler.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                satınAlınanÜrünlerListesi.add(satır);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SatınAldıklarımıGösterAction();
    }
}
