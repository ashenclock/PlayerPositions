# 📍 PlayerPositions (Minecraft Plugin)

Utility plugin to inspect offline player information on Minecraft servers, including last known position and logout metadata.

## ✨ Features

- Read saved player position data for offline users
- Retrieve last logout context
- Designed for Minecraft server administration workflows

## 📁 Project Layout

```text
src/
├── plugin.yml
├── me/AshenClock/
│   ├── PlayerPositions.java
│   └── commands/
│       ├── CommandPosition.java
│       └── OffPlayers.java
└── net/querz/...
```

## 📌 Notes

- The source includes MCA/region file utilities used for world/player data processing.
- Build metadata files are not currently included in this repository; if needed, add your preferred plugin build setup (Maven/Gradle) and package as a server plugin jar.

## 📄 License

See `license.md`.

