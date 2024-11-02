import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GirişYapAction extends JFrame { 
    public GirişYapAction() { 
        setTitle("Müşteri Girişi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        add(panel);

        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        JTextField txtKullanıcıAdı = new JTextField();
        panel.add(lblKullanıcıAdı);
        panel.add(txtKullanıcıAdı);

        JLabel lblŞifre = new JLabel("Şifre:");
        JPasswordField txtŞifre = new JPasswordField();
        panel.add(lblŞifre);
        panel.add(txtŞifre);

        JButton btnGirişYap = new JButton("Giriş Yap");
        btnGirişYap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanıcıAdı = txtKullanıcıAdı.getText();
                String şifre = new String(txtŞifre.getPassword());

                if (kullaniciGirisKontrolu(kullanıcıAdı, şifre)) {
                    new  MüşteriArayüzü().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(GirişYapAction.this, "Kullanıcı adı veya şifre yanlış!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnGirişYap);
        
        JButton geriButton = new JButton("Geri");
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ArelGiyimGUI().setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(geriButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private boolean kullaniciGirisKontrolu(String kullanıcıAdı, String şifre) {
        String dosyaYolu = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\kullanıcılar.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parça = satır.split(",");
                if (parça.length >= 2 && parça[1].equals(kullanıcıAdı) && parça[2].equals(şifre)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
