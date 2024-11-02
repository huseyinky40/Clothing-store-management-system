import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KayıtOlAction extends JFrame {
	
    public KayıtOlAction() {

        setTitle("Müşteri Kayıt Ol");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        add(panel);

        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        JTextField txtKullanıcıAdı = new JTextField();
        panel.add(lblKullanıcıAdı);
        panel.add(txtKullanıcıAdı);

        JLabel lblŞifre = new JLabel("Şifre:");
        JPasswordField txtŞifre = new JPasswordField();
        panel.add(lblŞifre);
        panel.add(txtŞifre);

        JLabel lblEposta = new JLabel("E-posta:");
        JTextField txtEposta = new JTextField();
        panel.add(lblEposta);
        panel.add(txtEposta);

        JLabel lblAd = new JLabel("Ad:");
        JTextField txtAd = new JTextField();
        panel.add(lblAd);
        panel.add(txtAd);

        JLabel lblSoyad = new JLabel("Soyad:");
        JTextField txtSoyad = new JTextField();
        panel.add(lblSoyad);
        panel.add(txtSoyad);

        JLabel lblDoğumTarih = new JLabel("Doğum Tarihi:");
        JTextField txtDoğumTarih = new JTextField();
        panel.add(lblDoğumTarih);
        panel.add(txtDoğumTarih);

        JButton btnKayıtOl = new JButton("Kayıt Ol");
        btnKayıtOl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanıcıAdı = txtKullanıcıAdı.getText();
                String şifre = new String(txtŞifre.getPassword());
                String eposta = txtEposta.getText();
                String ad = txtAd.getText();
                String soyad = txtSoyad.getText();
                String doğumTarih = txtDoğumTarih.getText();
                
                Müşteri müşteri = new Müşteri(kullanıcıAdı, şifre, eposta, ad, soyad, doğumTarih);
                
                KullanıcıDosyayaYaz.kullanıcıBilgileriniDosyayaKaydet(müşteri);
                
                new ArelGiyimGUI().setVisible(true);
                dispose(); 
            }
        });
        panel.add(btnKayıtOl);
        
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
}
