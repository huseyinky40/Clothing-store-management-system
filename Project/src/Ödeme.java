public class Ödeme {
	
    private static boolean kartNumarasıGeçerliMi(String kartNumarası) {
        return kartNumarası.matches("^\\d{16}");
    }

    private static boolean sonKullanmaTarihiGeçerliMi(String sonKullanmaTarihi) {
        return sonKullanmaTarihi.matches("^\\d{2}/\\d{2}");
    }

    private static boolean CVVKoduGeçerliMi(String cvv) {
        return cvv.matches("^\\d{3}");
    }
    
    public static boolean krediKartıylaÖdemeYap(String kartNumarası, String sonKullanmaTarihi, String cvv) {
        if (!kartNumarasıGeçerliMi(kartNumarası)) {
            System.out.println("Geçersiz kredi kartı numarası.");
            return false;
        }

        if (!sonKullanmaTarihiGeçerliMi(sonKullanmaTarihi)) {
            System.out.println("Geçersiz son kullanma tarihi formatı");
            return false;
        }

        if (!CVVKoduGeçerliMi(cvv)) {
            System.out.println("Geçersiz CVV kodu.");
            return false;
        }

        System.out.println("Ödeme başarıyla tamamlandı.");
        return true;
    }
}
