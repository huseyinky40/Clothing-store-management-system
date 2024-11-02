import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ÜrünleriDosyayaYaz {
    private static final String DOSYA_YOLU = "C:\\Users\\HUSEYIN\\eclipse-workspace\\Proje\\src\\ürünler.txt";

    public static void ürünleriYaz(String ürünAdı, List<String> bedenler, List<String> renkler, List<Integer> stoklar, List<Integer> fiyatlar) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU, true))) {
            int index = getSonIndex() + 1;
            for (int i = 0; i < bedenler.size(); i++) {
                writer.write(index + "," + ürünAdı + "," + bedenler.get(i) + "," + renkler.get(i) + "," + stoklar.get(i) + "," + fiyatlar.get(i));
                writer.newLine();
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getSonIndex() {
        try {
            List<String> satirlar = Files.readAllLines(Paths.get(DOSYA_YOLU));
            if (!satirlar.isEmpty()) {
                String sonSatir = satirlar.get(satirlar.size() - 1);
                String[] parçalar = sonSatir.split(",");
                return Integer.parseInt(parçalar[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
