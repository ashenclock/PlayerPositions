# PlayerPositions

PlayerPositions is a small Spigot/Paper plugin for Minecraft 1.19+ that exposes two administrative commands:

- `/playerpositions` shows the sender's coordinates and the coordinates of other online players.
- `/offplayers` reads the first loaded world's `playerdata` files and reports the last saved coordinates and last-played time for offline players.

It is intentionally read-only: the plugin does not modify worlds, player files, permissions, or inventories.

## Why this project exists

This project is a compact example of Java plugin development and binary game-save parsing. It combines Bukkit's runtime API with a vendored, Apache-2.0-licensed NBT/MCA reader derived from [Querz/NBT](https://github.com/Querz/NBT).

## Requirements

- Java 17 or newer
- Spigot or Paper 1.19.x (the API dependency is `1.19.4-R0.1-SNAPSHOT`)
- Maven 3.8+

The server API is provided by the server at runtime and is not bundled in the plugin JAR.

## Build

```bash
mvn clean package
```

The resulting plugin is created at `target/PlayerPositions-1.1.0.jar`. Copy it to the server's `plugins/` directory and restart the server. The repository currently does not include a server world or generated build output.

## Commands and permissions

| Command | Permission | Default | Purpose |
| --- | --- | --- | --- |
| `/playerpositions` | `playerpositions.online` | Everyone | Show online player positions. |
| `/offplayers` | `playerpositions.offline` | Operators | Read saved positions for offline players. |

`/offplayers` reads the first loaded world only. Player files that are missing or do not contain a valid three-value `Pos` list are skipped safely.

## Design notes

- Coordinates are rounded to integer blocks for readable chat output.
- The plugin uses the Bukkit logger instead of writing directly to standard output.
- Command registration is validated at startup; a malformed `plugin.yml` disables the plugin instead of failing later.
- Offline player data is read from the active server world folder rather than from a hard-coded working-directory path.
- No credentials, network calls, telemetry, or external services are used.

## Project layout

```text
src/
├── me/AshenClock/
│   ├── PlayerPositions.java
│   └── commands/
├── net/querz/              # vendored NBT/MCA reader
└── plugin.yml
```

## Limitations

The plugin is a focused utility rather than a full history database. It does not track movement over time, support multiple worlds in one report, or provide a web dashboard. The current build targets the Spigot 1.19.4 API; compatibility with later server versions should be checked before deployment.

## License and provenance

The plugin code is released under the Apache License 2.0; see [`license.md`](license.md). The vendored NBT/MCA implementation retains its upstream Apache-2.0-compatible provenance. This repository contains no Minecraft world data, server credentials, or proprietary assets.
