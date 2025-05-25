import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Uçak bilgilerini tutan sınıf
class Ucak {
    private String model, marka, seriNo;
    private int kapasite;

    // Yapıcı metod ile uçak nesnesi oluşturuluyor
    public Ucak(String marka, String model, String seriNo, int kapasite) {
        this.marka = marka;
        this.model = model;
        this.seriNo = seriNo;
        this.kapasite = kapasite;
    }

    // Uçağın özelliklerine erişmek için getter metodlar
    public String getModel() { return model; }
    public String getMarka() { return marka; }
    public String getSeriNo() { return seriNo; }
    public int getKapasite() { return kapasite; }
}

// Lokasyon bilgilerini tutan sınıf
class Lokasyon {
    private String ulke, sehir, havaalani;

    // Yapıcı metod
    public Lokasyon(String ulke, String sehir, String havaalani) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havaalani = havaalani;
    }

    // Lokasyon bilgilerine erişmek için getter metodlar
    public String getUlke() { return ulke; }
    public String getSehir() { return sehir; }
    public String getHavaalani() { return havaalani; }
}

// Uçuş bilgilerini temsil eden sınıf
class Ucus {
    private String ucusNo;
    private Ucak ucak;
    private Lokasyon kalkis, varis;
    private LocalDateTime tarihSaat;
    private List<Integer> doluKoltuklar = new ArrayList<>(); // Rezerve edilen koltuklar burada tutulur

    // Yapıcı metod ile uçuş oluşturuluyor
    public Ucus(String ucusNo, Ucak ucak, Lokasyon kalkis, Lokasyon varis, LocalDateTime tarihSaat) {
        this.ucusNo = ucusNo;
        this.ucak = ucak;
        this.kalkis = kalkis;
        this.varis = varis;
        this.tarihSaat = tarihSaat;
    }

    // Belirli bir koltuk numarası için rezervasyon yapılabilir mi kontrol eder
    public boolean koltukRezerveEt(int koltukNo) {
        if (koltukNo < 1 || koltukNo > ucak.getKapasite() || doluKoltuklar.contains(koltukNo)) {
            return false;
        }
        doluKoltuklar.add(koltukNo);
        return true;
    }

    // Uçuş bilgilerini ekrana yazdırır
    public void ucusBilgileriniGoster() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        System.out.println("\n=== UÇUŞ BİLGİLERİ ===");
        System.out.println("Uçuş No: " + ucusNo);
        System.out.println("Uçak: " + ucak.getMarka() + " " + ucak.getModel() + " (Seri: " + ucak.getSeriNo() + ")");
        System.out.println("Kapasite: " + ucak.getKapasite() + " koltuk | Dolu: " + doluKoltuklar.size());
        System.out.println("Kalkış: " + kalkis.getSehir() + " (" + kalkis.getHavaalani() + ")");
        System.out.println("Varış: " + varis.getSehir() + " (" + varis.getHavaalani() + ")");
        System.out.println("Tarih/Saat: " + tarihSaat.format(formatter));
        System.out.println("Boş Koltuklar: " + (ucak.getKapasite() - doluKoltuklar.size()));
    }

    // Getter metodlar
    public String getUcusNo() { return ucusNo; }
    public Ucak getUcak() { return ucak; }
}

// Rezervasyon bilgilerini tutan sınıf
class Rezervasyon {
    private Ucus ucus;
    private String ad, soyad;
    private int yas, koltukNo;

    // Yapıcı metod ile rezervasyon nesnesi oluşturuluyor
    public Rezervasyon(Ucus ucus, String ad, String soyad, int yas, int koltukNo) {
        this.ucus = ucus;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.koltukNo = koltukNo;
    }

    // Rezervasyon bilgilerini ekrana yazdırır
    public void rezervasyonBilgileriniGoster() {
        System.out.println("\n=== REZERVASYON BİLGİLERİ ===");
        System.out.println("Yolcu: " + ad + " " + soyad + " (" + yas + " yaş)");
        System.out.println("Koltuk No: " + koltukNo);
        ucus.ucusBilgileriniGoster();
    }

    // Rezervasyon bilgilerini CSV formatında döndürür
    public String toCSV() {
        return String.join(",",
                ucus.getUcusNo(),
                ad,
                soyad,
                String.valueOf(yas),
                String.valueOf(koltukNo),
                ucus.getUcak().getSeriNo()
        );
    }
}

// Ana uygulama sınıfı
public class UcusRezervasyonSistemi {
    private static List<Ucus> ucuslar = new ArrayList<>(); // Tüm uçuşlar burada tutulur
    private static Scanner scanner = new Scanner(System.in); // Kullanıcıdan giriş almak için

    public static void main(String[] args) {
        // Başlangıçta bazı örnek veriler yükleniyor
        verileriOlustur();

        // Konsol menüsü başlatılıyor
        while (true) {
            System.out.println("\n=== ANA MENÜ ===");
            System.out.println("1. Tüm Uçuşları Listele");
            System.out.println("2. Rezervasyon Yap");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Satır sonunu temizler

            switch (secim) {
                case 1:
                    ucuslariListele();
                    break;
                case 2:
                    rezervasyonYap();
                    break;
                case 3:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçim! Lütfen 1-3 arasında bir sayı girin.");
            }
        }
    }

    // Örnek uçak, lokasyon ve uçuş verileri ekleniyor
    private static void verileriOlustur() {
        Ucak ucak1 = new Ucak("Boeing", "737", "B-737-001", 150);
        Ucak ucak2 = new Ucak("Airbus", "A320", "A-320-001", 180);

        Lokasyon istanbul = new Lokasyon("Türkiye", "İstanbul", "IST");
        Lokasyon ankara = new Lokasyon("Türkiye", "Ankara", "ESB");
        Lokasyon izmir = new Lokasyon("Türkiye", "İzmir", "ADB");

        ucuslar.add(new Ucus("TK101", ucak1, istanbul, ankara, LocalDateTime.of(2025, 6, 1, 10, 0)));
        ucuslar.add(new Ucus("TK202", ucak2, ankara, izmir, LocalDateTime.of(2025, 6, 2, 15, 30)));

        rezervasyonlariYukle(); // Önceden yapılan rezervasyonları dosyadan yükle
    }

    // Daha önce kaydedilmiş rezervasyonları dosyadan okuyup dolu koltukları işaretler
    private static void rezervasyonlariYukle() {
        File dosya = new File("rezervasyonlar.csv");
        if (!dosya.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length >= 6) {
                    String ucusNo = parcalar[0];
                    int koltukNo = Integer.parseInt(parcalar[4]);

                    for (Ucus ucus : ucuslar) {
                        if (ucus.getUcusNo().equalsIgnoreCase(ucusNo)) {
                            ucus.koltukRezerveEt(koltukNo);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Rezervasyonlar okunurken hata oluştu.");
        }
    }

    // Tüm uçuşları ekrana listeleyen metod
    private static void ucuslariListele() {
        System.out.println("\n=== TÜM UÇUŞLAR ===");
        for (Ucus ucus : ucuslar) {
            ucus.ucusBilgileriniGoster();
            System.out.println("-----------------------");
        }
    }

    // Kullanıcıdan bilgi alıp rezervasyon yapılmasını sağlayan metod
    private static void rezervasyonYap() {
        System.out.println("\n=== REZERVASYON EKRANI ===");
        ucuslariListele();

        System.out.print("\nRezervasyon yapmak istediğiniz uçuş numarasını girin: ");
        String ucusNo = scanner.nextLine();

        Ucus secilenUcus = null;
        for (Ucus ucus : ucuslar) {
            if (ucus.getUcusNo().equalsIgnoreCase(ucusNo)) {
                secilenUcus = ucus;
                break;
            }
        }

        if (secilenUcus == null) {
            System.out.println("Geçersiz uçuş numarası!");
            return;
        }

        System.out.print("Adınız: ");
        String ad = scanner.nextLine();

        System.out.print("Soyadınız: ");
        String soyad = scanner.nextLine();

        System.out.print("Yaşınız: ");
        int yas = scanner.nextInt();

        System.out.print("Koltuk numarası: ");
        int koltukNo = scanner.nextInt();
        scanner.nextLine();

        // Koltuk doluysa veya geçersizse kullanıcı uyarılır
        if (!secilenUcus.koltukRezerveEt(koltukNo)) {
            System.out.println("Bu koltuk rezerve edilemez! Lütfen başka bir koltuk seçin.");
            return;
        }

        // Rezervasyon nesnesi oluşturulur
        Rezervasyon yeniRezervasyon = new Rezervasyon(secilenUcus, ad, soyad, yas, koltukNo);
        dosyayaKaydet(yeniRezervasyon.toCSV()); // Dosyaya kayıt yapılır
        yeniRezervasyon.rezervasyonBilgileriniGoster(); // Bilgiler gösterilir
        System.out.println("Rezervasyonunuz başarıyla tamamlandı!");
    }

    // Rezervasyon bilgilerini CSV dosyasına kaydeden metod
    private static void dosyayaKaydet(String csv) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rezervasyonlar.csv", true))) {
            writer.write(csv);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Rezervasyon kaydedilirken hata oluştu!");
        }
    }
}
