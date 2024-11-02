import java.util.ArrayList;
import java.util.List;

public class AlışverişSepeti {
    private List<Ürün> sepettekiÜrünler;

    public AlışverişSepeti() {
        this.sepettekiÜrünler = new ArrayList<>();
    }

    public void sepeteÜrünEkle(Ürün ürün) {
        sepettekiÜrünler.add(ürün);
        System.out.println(ürün.getAd() + " sepete eklendi.");
    }

    public void sepettenÜrünSil(Ürün ürün) {
        if (sepettekiÜrünler.contains(ürün)) {
            sepettekiÜrünler.remove(ürün);
            System.out.println(ürün.getAd() + " sepetten silindi.");
        } else {
            System.out.println("Bu ürün sepetinizde bulunmamaktadır: " + ürün.getAd());
        }
    }

    public void sepetiGöster() {
        if (sepettekiÜrünler.isEmpty()) {
            System.out.println("Sepet boş.");
        } else {
            System.out.println("Sepetteki Ürünler:");
            for (Ürün ürün : sepettekiÜrünler) {
                System.out.println(ürün.getAd());
            }
        }
    }
}
