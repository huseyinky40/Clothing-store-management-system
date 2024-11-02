import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ÜrünEkleAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Ürün> eklenenÜrünler = new ArrayList<>();

        boolean yeniÜrünEklensin = true;
        while (yeniÜrünEklensin) {
            String ürünAdı = JOptionPane.showInputDialog(null, "Ürün adını giriniz:");
            if (!ürünAdı.isEmpty()) {
                List<String> bedenler = new ArrayList<>();
                List<Integer> fiyatlar = new ArrayList<>();
                List<String> renkler = new ArrayList<>();
                List<Integer> stoklar = new ArrayList<>();

                boolean yeniÜrünBilgileriAlınsın= true;
                while (yeniÜrünBilgileriAlınsın) {
                    String beden = JOptionPane.showInputDialog(null, "Ürün bedenini giriniz (Çıkış için boş bırakın):");
                    if (beden != null && !beden.isEmpty()) {
                        bedenler.add(beden);
                        int fiyat = Integer.parseInt(JOptionPane.showInputDialog(null, "Ürün fiyatını giriniz:"));
                        fiyatlar.add(fiyat);
                        String renk = JOptionPane.showInputDialog(null, "Ürün rengini giriniz:");
                        renkler.add(renk);
                        int stok = Integer.parseInt(JOptionPane.showInputDialog(null, "Ürün stok miktarını giriniz:"));
                        stoklar.add(stok);
                    } else {
                    	yeniÜrünBilgileriAlınsın = false;
                    }
                }


                eklenenÜrünler.add(new Ürün(ürünAdı, bedenler, fiyatlar, renkler, stoklar));
                JOptionPane.showMessageDialog(null, "Ürün başarıyla eklendi.");

                ÜrünleriDosyayaYaz.ürünleriYaz(ürünAdı, bedenler, renkler, stoklar, fiyatlar);
            } else {
                JOptionPane.showMessageDialog(null, "Ürün adı boş bırakılamaz.");
                yeniÜrünEklensin = false;
            }

            int seçenek = JOptionPane.showConfirmDialog(null, "Yeni ürün eklemek istiyor musunuz?", "Yeni Ürün Ekle", JOptionPane.YES_NO_OPTION);
            yeniÜrünEklensin = (seçenek == JOptionPane.YES_OPTION);
        }

        StringBuilder mesaj = new StringBuilder("Eklenen Ürünler:\n");
        for (Ürün ürün : eklenenÜrünler) {
            mesaj.append("Ürün Adı: ").append(ürün.getAd()).append(", Renk: ").append(ürün.getRenkler().get(0)).append("\n");
        }
        JOptionPane.showMessageDialog(null, mesaj.toString());
    }
}
