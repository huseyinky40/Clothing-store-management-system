import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArelGiyimGUI {
    private JFrame frame;

    public ArelGiyimGUI() {
        frame = new JFrame("Arel Giyim Başlangıç Sayfası");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        frame.add(panel);
             

        JLabel labelArelGiyim = new JLabel("AREL GİYİM", SwingConstants.CENTER);
        labelArelGiyim.setForeground(Color.BLUE);
        labelArelGiyim.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(labelArelGiyim);



        JButton btnYöneticiGişiri = new JButton("Yönetici Girişi");
        btnYöneticiGişiri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new YöneticiGirişiGUI();
            }
        });
        panel.add(btnYöneticiGişiri);

        JButton btnMüşteriKayıtOl = new JButton("Müşteri Kayıt Ol");
        btnMüşteriKayıtOl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new KayıtOlAction().setVisible(true);
            }
        });
        panel.add(btnMüşteriKayıtOl);

        JButton btnMüşteriGirişi = new JButton("Müşteri Girişi");
        btnMüşteriGirişi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GirişYapAction().setVisible(true);
            }
        });
        panel.add(btnMüşteriGirişi);

        frame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArelGiyimGUI gui = new ArelGiyimGUI();
        });
    }
}
