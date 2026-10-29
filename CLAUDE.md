# Titan Utilities — project context

Utilities and final items for the **TitanMods** modpack. Author: Pedro (beginner in modding — explain steps in
**Portuguese** in chat; keep everything inside the mod in **English**). Sister mod of **TitanOres**
(`D:\projetos\Mods\TitanOres`, read its CLAUDE.md for the full project history).

## Stack
- Minecraft **1.16.5**, Forge **36.2.42**, official mappings, mod id `titanutilities`, package `com.titanmods.titanutilities`.
- Same Gradle setup as TitanOres: ForgeGradle 6 + Gradle 8.8 wrapper (system JDK 21), mod compiles/runs on **Java 8**
  via toolchain (JDK 8 path in the USER file `~/.gradle/gradle.properties`).
- **Requires TitanOres** (`mods.toml` mandatory dependency, ordering AFTER). In dev it is loaded from the sibling
  project's jar: `flatDir '../TitanOres/build/libs'` + `implementation fg.deobf("blank:titanores:${titanores_version}")`.
  After changing TitanOres, run `.\gradlew.bat build` THERE first; bump `titanores_version` in `gradle.properties`
  when TitanOres' version changes.
- Test mods in runClient: JEI 7.7.1.153, Curios 4.1.0.1. Third-party mods go in `build.gradle` with `fg.deobf`,
  never in `run/mods`.

## Commands (PowerShell, from repo root)
- Build: `.\gradlew.bat build` (filter with `Select-String 'error:|BUILD'`). Run: `.\gradlew.bat runClient` (the
  user tests in game). Don't write Java files with PowerShell `Set-Content` (BOM breaks javac).

## Conventions (same as TitanOres)
- IDs, file names, comments, logs in English; Portuguese only in `lang/pt_br.json`.
- Every item fire resistant; textures in lowercase folders, no spaces/uppercase.
- ALWAYS write a plan and wait for the user's approval before implementing; work in small parts.
- Modpack recipes with other mods' items go in the pack's KubeJS
  (`C:/Users/Pedro/curseforge/minecraft/Instances/TitanMods/kubejs/`); recipes with only vanilla/Titan items go in
  the mod as JSON.

## Current state
- Part 1 DONE: empty mod structure (main class only, no items/registries yet).
- NEXT: move the final items (and other modpack items) from TitanOres to here. Their ids change
  (`titanores:*` -> `titanutilities:*`): update the pack's KubeJS scripts/rituals and the docs at the same time.
