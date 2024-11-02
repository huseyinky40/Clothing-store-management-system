import java.util.List;

public class Ürün {
    public String ad;
    public List<String> bedenler;
    public List<Integer> fiyatlar;
    public List<String> renkler;
    public List<Integer> stoklar;

    public Ürün(String ad, List<String> bedenler, List<Integer> fiyatlar, List<String> renkler, List<Integer> stoklar) {
        this.ad = ad;
        this.fiyatlar = fiyatlar;
        this.bedenler = bedenler;
        this.renkler = renkler;
        this.stoklar = stoklar;
    }
    
    public void ürünEkle(String ad,String beden, int fiyat, String renk, int stok) {
    	this.ad = ad;
        bedenler.add(beden);
        renkler.add(renk);
        stoklar.add(stok);
        fiyatlar.add(fiyat);
    }

    public List<String> getBedenler() {
        return bedenler;
    }

    public List<String> getRenkler() {
        return renkler;
    }

    public List<Integer> getStoklar() {
        return stoklar;
    }
    
    public List<Integer> getFiyatlar(){
    	return fiyatlar;
    }

    public String getAd() {
        return ad;
    }
    
    @Override
    public String toString() {
        return "Ürün adı: " + ad + ", Renk: " + renkler.get(0);
    }
}
