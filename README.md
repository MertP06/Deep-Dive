# 🌊 DeepDive

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-E74C3C?style=for-the-badge&logo=libgdx&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

**Derinlere dalış yapan bir denizaltı macera oyunu!**

[Özellikler](#-özellikler) • [Kurulum](#-kurulum) • [Oynanış](#-oynanış) • [Ekran Görüntüleri](#-ekran-görüntüleri) • [Geliştirici](#-geliştirici)

</div>

---

## 🎮 Oyun Hakkında

**DeepDive**, libGDX framework'ü ile Java dilinde geliştirilmiş bir mobil arcade oyunudur. Oyunda bir denizaltıyı kontrol ederek okyanusun derinliklerinde maceraya atılırsınız. Pusula toplayın, tehlikeli deniz canavarlarından kaçının ve tüm seviyeleri tamamlayın!

## ✨ Özellikler

- 🚢 **Animasyonlu Denizaltı** - Hareket ve hasar animasyonları
- 👾 **Çikolata Canavarları** - 8 karelik akıcı animasyonlu düşmanlar
- 🧭 **Pusula Toplama** - Seviye geçmek için pusula toplayın
- ❤️ **Sağlık Sistemi** - Her seviyede artan can hakkı
- 🎵 **Müzik & Ses Efektleri** - Atmosferik arka plan müziği ve çarpışma sesleri
- 🫧 **Baloncuk Efektleri** - Denizaltından çıkan görsel baloncuklar
- 📈 **Artan Zorluk** - Her seviyede hızlanan düşmanlar
- 💾 **İlerleme Kayıt** - Açılan seviyeler otomatik kaydedilir
- ⏸️ **Duraklat/Devam** - Oyunu istediğiniz zaman durdurun

## 🎯 Oynanış

1. **Kontroller**: Ekranın üst yarısına dokunarak yukarı, alt yarısına dokunarak aşağı hareket edin
2. **Amaç**: Pusulaları toplayarak seviye atlayın
3. **Tehlike**: Çikolata canavarlarından kaçının - çarpışma can kaybettirir!
4. **Seviyeler**: 4 farklı seviye, her biri bir öncekinden daha zor

## 🛠️ Kurulum

### Gereksinimler

- Java JDK 11 veya üzeri
- Android SDK (Android derlemesi için)
- Gradle

### Derleme

```bash
# Projeyi klonlayın
git clone https://github.com/MertP06/Deep-Dive.git
cd Deep-Dive

# Android APK oluşturun
./gradlew android:assembleDebug

# APK dosyası: android/build/outputs/apk/debug/
```

### Gradle Komutları

| Komut | Açıklama |
|-------|----------|
| `./gradlew build` | Tüm projeyi derler |
| `./gradlew clean` | Build klasörlerini temizler |
| `./gradlew android:assembleDebug` | Debug APK oluşturur |
| `./gradlew android:assembleRelease` | Release APK oluşturur |
| `./gradlew android:lint` | Android kod analizi yapar |

## 📁 Proje Yapısı

```
DeepDive/
├── android/          # Android platform modülü
│   ├── src/          # Android launcher
│   └── res/          # Android kaynakları (ikonlar vb.)
├── core/             # Ana oyun mantığı
│   └── src/main/java/com/mertp/deepdive/
│       ├── Main.java              # Ana oyun döngüsü
│       ├── SubmarineAnim.java     # Denizaltı animasyonları
│       ├── Enemy.java             # Düşman sınıfı
│       ├── EnemyManager.java      # Düşman yönetimi
│       ├── CompassManager.java    # Pusula yönetimi
│       ├── BubbleManager.java     # Baloncuk efektleri
│       ├── GameManager.java       # Oyun durumu yönetimi
│       ├── LevelManager.java      # Seviye yönetimi
│       ├── UIManager.java         # Arayüz yönetimi
│       ├── InputManager.java      # Giriş kontrolü
│       └── GameFlowController.java # Oyun akış kontrolü
├── assets/           # Oyun kaynakları
│   ├── *.png         # Sprite ve arka plan görselleri
│   └── *.ogg         # Ses dosyaları
└── gradle/           # Gradle wrapper dosyaları
```

## 🎨 Oyun Varlıkları

| Varlık | Açıklama |
|--------|----------|
| `submarine.png` | Ana denizaltı görseli |
| `idle1-4.png` | Denizaltı bekleme animasyonu |
| `hit.png, hit2.png` | Denizaltı hasar animasyonu |
| `layer1-8.png` | Çikolata canavarı animasyon kareleri |
| `compass.png` | Toplanabilir pusula |
| `bubble.png` | Baloncuk efekti |
| `heart.png` | Can göstergesi |
| `background.png` | Kayan arka plan |
| `bgm.ogg` | Arka plan müziği |
| `collision.ogg` | Çarpışma ses efekti |

## 🔧 Teknik Detaylar

- **Framework**: [libGDX](https://libgdx.com/) - Çapraz platform oyun geliştirme
- **Dil**: Java
- **Minimum Android**: API 19 (Android 4.4 KitKat)
- **Proje Oluşturucu**: [gdx-liftoff](https://github.com/libgdx/gdx-liftoff)

## 👨‍💻 Geliştirici

**MertP06**

- GitHub: [@MertP06](https://github.com/MertP06)

## 📄 Lisans

Bu proje açık kaynak olarak paylaşılmaktadır.

---

<div align="center">

**⭐ Projeyi beğendiyseniz yıldız vermeyi unutmayın! ⭐**

</div>
