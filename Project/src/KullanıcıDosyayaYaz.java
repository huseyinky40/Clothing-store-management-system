import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KullanıcıDosyayaYaz {
    private static String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\kullanıcılar.txt";

    public static void kullanıcıBilgileriniDosyayaKaydet(Müşteri müşteri) {
        String kullanıcıAdı = müşteri.getKullanıcıAdı();
        String şifre = müşteri.getŞifre();
        String ad = müşteri.getAd();
        String soyad = müşteri.getSoyad();
        String eposta = müşteri.getEposta();
        String doğumTarihi = müşteri.getDoğumTarihi();

        if (kullanıcıBilgileriGeçerli(müşteri,kullanıcıAdı, şifre, ad, soyad, eposta, doğumTarihi)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU, true))) {
                int sonIndex = sonIndexiAl() + 1;
                writer.write(sonIndex + "," + kullanıcıAdı + "," + şifre + "," + ad + "," + soyad + "," + eposta + "," + doğumTarihi);
                writer.newLine();
                System.out.println("Kullanıcı kaydedildi: " + ad + " " + soyad);
            } catch (IOException e) {
                System.out.println("Dosya yazma hatası: " + e.getMessage());
            }
        } else {
            System.out.println("Hatalı kullanıcı bilgileri! Kullanıcı kaydedilemedi.");
        }
    }

    private static boolean kullanıcıBilgileriGeçerli(Müşteri müşteri, String kullanıcıAdı, String şifre, String ad, String soyad, String eposta, String doğumTarihi) {
        return müşteri.getKullanıcıAdı().equals(kullanıcıAdı) &&
               müşteri.getŞifre().equals(şifre) &&
               müşteri.getAd().equals(ad) &&
               müşteri.getSoyad().equals(soyad) &&
               müşteri.getEposta().equals(eposta) &&
               müşteri.getDoğumTarihi().equals(doğumTarihi);
    }

    private static int sonIndexiAl() {
        int sonIndex = -1;
        try {
            List<String> satırlar = Files.readAllLines(Paths.get(DOSYA_YOLU));
            if (!satırlar.isEmpty()) {
                String sonSatır = satırlar.get(satırlar.size() - 1);
                String[] parçalar = sonSatır.split(",");
                if (parçalar.length > 0 && !parçalar[0].isEmpty()) {
                    sonIndex = Integer.parseInt(parçalar[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz sayı formatı: " + e.getMessage());
        }
        return sonIndex;
    }

}
