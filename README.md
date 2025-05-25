# Uçak Bileti Rezervasyon Sistemi

Bu proje Java dilinde geliştirilmiş basit bir uçak bileti rezervasyon sistemidir. Kullanıcılar uçuşları görüntüleyebilir ve istedikleri uçuş için rezervasyon yapabilir.
Projede OOP (nesne yönelimli programlama) yapısı kullanılmıştır.

## Özellikler

- Uçuşları listeleme  
- Rezervasyon yapma  
- Rezervasyon bilgilerini dosyaya kaydetme (`rezervasyonlar.csv`)  
- Uygulama başlatıldığında dosyadaki eski rezervasyonları okuma  
- Koltuk seçimi ve doluluk kontrolü  
- Temel sınıflar: `Ucak`, `Lokasyon`, `Ucus`, `Rezervasyon`

## Kullanılan Yapılar

Projede şu konulara yer verilmiştir:

- Sınıflar ve nesneler
- Overloading, metotlar ve scope kullanımı
- Yapıcı metodlar (constructor)
- Encapsulation (getter metodları)
- Inner class olmadan sade sınıf yapısı
- Dosya işlemleri (okuma ve yazma)
- ArrayList ile uçuş ve koltuk listesi yönetimi
- Kullanıcıdan bilgi alma (`Scanner`)

## Nasıl Çalıştırılır?

1. Proje dosyasını bir Java editöründe açın (örneğin IntelliJ IDEA veya VS Code).
2. `UcusRezervasyonSistemi` sınıfını çalıştırın.
3. Konsoldaki menüden uçuşları görüntüleyebilir veya rezervasyon yapabilirsiniz.

> Rezervasyon bilgileri `rezervasyonlar.csv` adlı dosyaya otomatik olarak kaydedilir ve uygulama her başlatıldığında bu dosya okunur.

## Notlar

Bu proje okul ödevi kapsamında geliştirilmiştir. Temel seviye Java bilgisiyle yazılmıştır. Kod yapısı mümkün olduğunca sade tutulmaya çalışılmıştır.

