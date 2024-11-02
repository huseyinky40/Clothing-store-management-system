import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class YöneticiGirişiGUI {
    private JFrame frame;

    public YöneticiGirişiGUI() {
        frame = new JFrame("Arel Giyim - Yönetici İşlemleri");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);


        adminGirişEkranıGöster(); 
    }

    private void adminGirişEkranıGöster() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel başlıkLabel = new JLabel("Yönetici Girişi");
        panel.add(başlıkLabel, BorderLayout.NORTH);

        JPanel girişPaneli = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(girişPaneli, BorderLayout.CENTER);

        JLabel kullanıcıAdıLabel = new JLabel("Kullanıcı Adı:");
        JTextField kullanıcıAdıField = new JTextField(10);
        JLabel şifreLabel = new JLabel("Şifre:");
        JPasswordField şifreField = new JPasswordField(10);
        JButton girişButon = new JButton("Giriş Yap");

        girişButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = kullanıcıAdıField.getText();
                String sifre = new String(şifreField.getPassword());

                if (kullaniciAdi.equals("1") && sifre.equals("1")) {
                    frame.dispose(); 
                    YöneticiArayüzü yoneticiArayuzu = new YöneticiArayüzü();
                    yoneticiArayuzu.setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Yanlış kullanıcı adı veya şifre!");
                }
            }
        });

        girişPaneli.add(kullanıcıAdıLabel);
        girişPaneli.add(kullanıcıAdıField);
        girişPaneli.add(şifreLabel);
        girişPaneli.add(şifreField);
        girişPaneli.add(new JLabel());
        girişPaneli.add(girişButon);
        
        JButton geriButton = new JButton("Geri");
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		frame.dispose();
                new ArelGiyimGUI().setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(geriButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(YöneticiGirişiGUI::new);
    }
}