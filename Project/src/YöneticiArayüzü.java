import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class YöneticiArayüzü extends JFrame {
    public YöneticiArayüzü() {

        setTitle("Arel Giyim - Yönetici Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        JPanel anaPanel = new JPanel();
        anaPanel.setLayout(new GridLayout(6, 1, 10, 10)); 
        add(anaPanel);

        JButton ürünEkleButon = new JButton("Mağazaya Ürün Ekle");
        ürünEkleButon.addActionListener(new ÜrünEkleAction());
        anaPanel.add(ürünEkleButon);

        JButton ürünSilButon = new JButton("Mağazadan Ürün Sil");
        ürünSilButon.addActionListener(new UrunSilAction());
        anaPanel.add(ürünSilButon);

        JButton fiyatGüncelleButon = new JButton("Fiyat Güncelle");
        fiyatGüncelleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FiyatGüncelleAction().actionPerformed(e); 
            }
        });
        anaPanel.add(fiyatGüncelleButon);
        
        JButton stokKontrolButon = new JButton("Stok Kontrol Et");
        stokKontrolButon.addActionListener(new StokKontrolAction());
        anaPanel.add(stokKontrolButon);

        JButton satılanlarıGörüntüleButon = new JButton("Satışlar");
        satılanlarıGörüntüleButon.addActionListener(new SatışlarıGörüntüleAction());
        anaPanel.add(satılanlarıGörüntüleButon);

        JPanel altPanel = new JPanel();
        anaPanel.add(altPanel);

        JButton çıkışButon = new JButton("Çıkış Yap");
        çıkışButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Yönetici çıkış yaptı.");
                dispose();
                new ArelGiyimGUI().setVisible(true);
            }
        });
        altPanel.add(çıkışButon);

        setVisible(true);
    }

    public static void main(String[] args) {
        new YöneticiArayüzü();
    }
}
