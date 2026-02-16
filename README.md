# 🌊 DeepDive

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-E74C3C?style=for-the-badge&logo=libgdx&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

**A submarine adventure game diving into the depths!**

[Features](#-features) • [Installation](#-installation) • [Gameplay](#-gameplay) • [Screenshots](#-screenshots) • [Developer](#-developer)

---

## 🎮 About the Game

**DeepDive** is a mobile arcade game developed in Java using the libGDX framework. Control a submarine and embark on an adventure in the depths of the ocean. Collect compasses, avoid dangerous sea monsters, and complete all levels!

## ✨ Features

- 🚢 **Animated Submarine** - Movement and damage animations
- 👾 **Chocolate Monsters** - 8-frame smooth animated enemies
- 🧭 **Compass Collection** - Collect compasses to advance levels
- ❤️ **Health System** - Increasing health points per level
- 🎵 **Music & Sound Effects** - Atmospheric background music and collision sounds
- 🫧 **Bubble Effects** - Visual bubbles from the submarine
- 📈 **Increasing Difficulty** - Enemies speed up each level
- 💾 **Progress Save** - Unlocked levels are automatically saved
- ⏸️ **Pause/Resume** - Pause the game anytime

## 🎯 Gameplay

1. **Controls**: Touch the top half of the screen to move up, bottom half to move down
2. **Objective**: Collect compasses to advance levels
3. **Danger**: Avoid chocolate monsters - collision causes health loss!
4. **Levels**: 4 different levels, each harder than the previous

## 🛠️ Installation

### Requirements

- Java JDK 11 or higher
- Android SDK (for Android builds)
- Gradle

### Building

```bash
# Clone the project
git clone https://github.com/MertP06/Deep-Dive.git
cd Deep-Dive

# Build Android APK
./gradlew android:assembleDebug

# APK file location: android/build/outputs/apk/debug/
```

### Gradle Commands

| Command | Description |
|-------|----------|
| `./gradlew build` | Build entire project |
| `./gradlew clean` | Clean build folders |
| `./gradlew android:assembleDebug` | Create debug APK |
| `./gradlew android:assembleRelease` | Create release APK |
| `./gradlew android:lint` | Run Android code analysis |

## 📁 Project Structure

```
DeepDive/
├── android/ # Android platform module
│ ├── src/ # Android launcher
│ └── res/ # Android resources (icons, etc.)
├── core/ # Main game logic
│ └── src/main/java/com/mertp/deepdive/
│ ├── Main.java # Main game loop
│ ├── SubmarineAnim.java # Submarine animations
│ ├── Enemy.java # Enemy class
│ ├── EnemyManager.java # Enemy management
│ ├── CompassManager.java # Compass management
│ ├── BubbleManager.java # Bubble effects
│ ├── GameManager.java # Game state management
│ ├── LevelManager.java # Level management
│ ├── UIManager.java # Interface management
│ ├── InputManager.java # Input control
│ └── GameFlowController.java # Game flow control
├── assets/ # Game resources
│ ├── *.png # Sprite and background images
│ └── *.ogg # Sound files
└── gradle/ # Gradle wrapper files
```

## 🎨 Game Assets

| Asset | Description |
|--------|----------|
| `submarine.png` | Main submarine image |
| `idle1-4.png` | Submarine idle animation |
| `hit.png, hit2.png` | Submarine damage animation |
| `layer1-8.png` | Chocolate monster animation frames |
| `compass.png` | Collectible compass |
| `bubble.png` | Bubble effect |
| `heart.png` | Health indicator |
| `background.png` | Scrolling background |
| `bgm.ogg` | Background music |
| `collision.ogg` | Collision sound effect |

## 🔧 Technical Details

- **Framework**: [libGDX](https://libgdx.com/) - Cross-platform game development
- **Language**: Java
- **Minimum Android**: API 19 (Android 4.4 KitKat)
- **Project Generator**: [gdx-liftoff](https://github.com/libgdx/gdx-liftoff)

## 👨‍💻 Developer

**MertP06**

- GitHub: [@MertP06](https://github.com/MertP06)

## 📄 License

This project is open source and shared publicly.

---

**⭐ Don't forget to star the project if you liked it! ⭐**
