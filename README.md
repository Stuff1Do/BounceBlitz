# BounceBlitz

**BounceBlitz** is a Java-based air hockey simulation built as a machine problem. Designed for both fun and educational use, it demonstrates core game mechanics, collision detection, and simple AI control. It is entirely offline and terminal based.

---

## Features

- **2D Air Hockey Gameplay**: Puck movement with bouncing off paddles and walls.
- **Player Control**: Keyboard input controls the player paddle.
- **AI Opponent**: Simple AI manages the opposing paddle.
- **Scoring System**: Tracks goals and displays the score.

---
Design & Architecture
Main Game Loop: Updates position, handles collision, and triggers rendering.

Collision Detection: Bouncing logic within window bounds and paddle impacts.

Object-Oriented: Entities like Puck, Paddle, and GameEngine simplify updates and interactions.

---

## Project Structure
BounceBlitz/
├── src/
│ └── ... # Java source files (main game loop, entities, physics)
├── assets/
│ └── ... # Game assets: images, sounds, etc.
├── README.md # This file
└── build/ # Compiled classes or jar output (if generated)


---

## ⚙️ Requirements

- Java Development Kit (JDK) 8 or newer.
- (Optional) Maven or Gradle if you want to automate builds or dependency management.
- A modern IDE (IntelliJ IDEA, Eclipse, VS Code) or command-line Java tools.

---

## 🚀 How to Build & Run

### With Command Line:
```bash
cd BounceBlitz
javac -d build src/*.java
java -cp build Main
```

### With IDE:
  1. Import the project as a Java application.
  2. Ensure src/ is marked as the source folder.
  3. Run the Main class.
  
---
Gameplay Controls
  Move paddle: Arrow keys (Left/Right/Up/Down)
  `More instructions in menu.`

---
Contributions are welcome!
  1. Fork the repo
  2. Create a feature branch (git checkout -b feature/X)
  3. Commit your changes
  4. Open a pull request
---
This project is an educational air hockey game for a machine problem assignment


