public class Müşteri {
    private String kullanıcıAdı;
    private String şifre;
    private String eposta;
    private String ad;
    private String soyad;
    private String doğumTarihi;

    public Müşteri(String kullanıcıAdı, String şifre, String eposta, String ad, String soyad, String doğumTarihi) {
        this.kullanıcıAdı = kullanıcıAdı;
        this.şifre = şifre;
        this.eposta = eposta;
        this.ad = ad;
        this.soyad = soyad;
        this.doğumTarihi = doğumTarihi;
    }
    
    public void kayıtOl(String kullanıcıAdı,String şifre,String eposta, String ad, String soyad, String doğumTarihi) {
    	this.kullanıcıAdı = kullanıcıAdı;
    	this.şifre = şifre;
        this.eposta = eposta;
        this.ad = ad;
        this.soyad = soyad;
        this.doğumTarihi = doğumTarihi;
        System.out.println("Yeni müşteri kaydedildi:" + ad + " " + soyad);
    }
    
    public String getKullanıcıAdı() {
        return kullanıcıAdı;
    }
    
    public String getŞifre() {
        return şifre;
    }

    public void setKullanıcıAdı(String kullanıcıAdı) {
        this.kullanıcıAdı = kullanıcıAdı;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }
    
    public String getAd() {
        return ad;
    }
    
    public String getEposta() {
        return eposta;
    }
    
    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
    
    public String getSoyad() {
        return soyad;
    }
    
    public String getDoğumTarihi() {
        return doğumTarihi;
    }

}