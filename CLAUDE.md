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
- Test mods in runClient: JEI 7.7.1.153, Curios 4.1.0.1, Thermal Foundation + CoFH Core (CurseMaven file ids in
  `gradle.properties`; provide `forge:gears/iron` and `thermal:machine_frame`, without them JEI hides the machine
  crafting recipes because those tags are empty), Mekanism 10.1.2.457 (steel casing for the machine block tag +
  creative energy cube for power), Industrial Foregoing + Titanium (iron/diamond gears + `machine_frame_pity`; same
  files as the pack). AllTheOres 1.16 (1.3.6) has NO gears: gears in the pack come from Thermal Foundation and IF.
  Note: `thermal:machine_frame` has no texture without Thermal Expansion (its textures ship there; the pack lacks it). Third-party mods go in `build.gradle` with `fg.deobf`,
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
- Part 1 DONE: mod structure. Part 2 DONE (2026-10-29): Titan Factory, Titan Crafter and Speed Upgrade moved here
  from TitanOres (ids `titanutilities:*`; ingredients/results that are TitanOres items keep `titanores:*`).
  Creative tab "Titan Utilities". Docs site NOT updated yet (user will reorganize it separately).
- Part 3 DONE (2026-10-29): Forge Upgrades moved here (TitanOres 1.0.4). `item/ForgeUpgradeItem`
  (`forge_upgrade_tier_2..5`, 5 = "X"): right-click a `forbidden_arcanus:hephaestus_forge` to raise its NBT `Level` by
  one (Forbidden & Arcanus 16.2.x can't upgrade it in survival; no compile dependency). Made by Hephaestus Forge
  rituals in the pack (`kubejs/data/titanmodpack/hephaestus_forge/rituals/forge_upgrade_tier_N.json`; the 1.16 forge
  has ONLY 6 pedestals, inputs are single items, essences must fit the tier caps). Textures `textures/item/forge_upgrade/`.
- Part 4 DONE (2026-10-29): final items + Blue Soil moved here (TitanOres 1.0.5). Pack updated: `titan_final_items.js`
  (Sponge Bob) and the 3 Source Crystal rituals give `titanutilities:*`; Titan Crafter disc recipe result too.
  Blue Soil (`ModBlocks.BLUE_SOIL`, cube_bottom_top, shovel; recipe in the pack KubeJS: Mystical Agriculture Infusion Altar, grass block + 4 `mysticalagradditions:insanium_block` + 4 eternal dust): TitanOres essence crops check the block
  tag `titanores:essence_crop_soil`, which this mod fills in `data/titanores/tags/blocks/essence_crop_soil.json`
  (TitanOres can't depend on this mod).
- Final items (10, moved here 2026-10-29 with TitanOres 1.0.5; textures `textures/item/finalitens/`): endgame components for the modpack. RECIPE RULE (user): recipes using only
  vanilla + Titan items go IN THE MOD (JSON); KubeJS in the modpack only for recipes needing other mods' items. All final items follow the Source Crystal style (epic rarity, no glow,
  no tooltip, no code comments). Modpack recipes that need other mods live in the PACK repo:
  `C:/Users/Pedro/curseforge/minecraft/Instances/TitanMods/kubejs/server_scripts/titan_final_items.js`
  (Sponge Bob = Create mechanical crafting 7x8). Recipe types without a KubeJS helper go as JSON in the pack's
  `kubejs/data/titanmodpack/...` (datapack): Source Crystal = Forbidden & Arcanus Hephaestus Forge rituals
  (`hephaestus_forge/rituals/source_crystal_<fire|ice|lightning>_heart.json`; FA 1.16 forge has ONLY 6 PEDESTALS (max 6 inputs, slots 0-5); inputs are single items, no tags, and
  position-independent; essences 600 aureal / 8 souls / 20000 blood / 350 XP = needs forge tier 3+). Docs site not updated yet. Done so far: `source_crystal`, `sponge_bob` (made with Create in the modpack),
  `ancestral_seed`, `world_core`. `raid_king` (crown): `item/RaidKingItem` (HEAD equipment slot, no armor, NBT `HeroLevel`) +
  `event/RaidKingEvents`: drops where the last raider dies once a raid with `getBadOmenLevel() >= getMaxBadOmenLevel()`
  turns `isVictory()` (pending map per world, checked on WorldTick); worn on head or Curios `head` slot (`compat/CuriosCompat` sends the HEAD IMC; tag `data/curios/tags/items/head.json`) -> Hero of the
  Village amplifier HeroLevel-1 renewed every 20 ticks. NOTE: in LivingDeathEvent the raider already left its raid
  (die() calls removeFromRaid first), so the raid is found with `getRaidAt(pos)`. Tested OK in game.
  Plain final items added 2026-10-15: `antenna`, `calculator`, `iron_man_mask`, `music_disc_titanium`, `universal_remote`
  (10 of 10 — all final items exist; only recipes remain).
- Universal Remote recipe (2026-10-29): Titan Crafter via KubeJS `event.custom` in the pack's
  `titan_final_items.js` (35 items from 10 mods, 200M FE = full machine, 6000 ticks). Docs: final-items.md + JEI-less
  screenshot `assets/recipes/universal_remote_titan_crafter.png`.
- Iron Man Mask recipe (2026-10-29): PneumaticCraft Pressure Chamber at 5.0 bar via KubeJS `event.custom`
  (3 red wool, 2 gold blocks, 2 `solarflux:sp_7`, 1 ultimate energy cube, 1 MekaSuit helmet). Chamber danger = 5.0,
  max 7.0 bar. Docs screenshot: `assets/recipes/iron_man_mask_pressure_chamber.png`.
- `eternal_dust` (plain item, texture by the user in `textures/item/eternal_dust/`): recipe in the pack KubeJS (2x2 shaped: fire/water over air/earth Mystical Agriculture agglomeratios). Docs: farming.md. DOCS RULE (user): everything Mystical Agriculture / farming related goes on the Farming page.
- Pack KubeJS scripts: `titan_final_items.js` (final items: Sponge Bob, Universal Remote, Iron Man Mask, Ancestral
  Seed) and `titan_farming.js` (Eternal Dust, Blue Soil, TitanOres seeds on the Infusion Altar = prosperity seed base +
  4 insanium blocks + 4 ore blocks, Botany Pots soil category `titan_blue_soil` + crops 2400 ticks, 75% essence / 5%
  seed). Ancestral Seed = emberite seed + 4 emberium blocks + 4 insanium blocks (Infusion Altar).
  PneumaticCraft caches its recipes for JEI: after changing PNC recipes, rejoin the world instead of `/reload`.
- NEXT: the user checks in game whether anything else should move.

Machine details (moved from TitanOres' CLAUDE.md; recipe ingredients like ingots still come from TitanOres):
- Titan Factory (name will change): 100M FE buffer (`energy/ModEnergyStorage`, receive-only, all sides), gradual
  consumption (energy/time per tick, pauses without power), items in from TOP (column-aware `TopInput`) and out from
  BOTTOM. Recipe type `titanutilities:titan_factory` (`left`/`right` columns of 3, `mirrored`, `energy`, `time`, `result`).
  Speed upgrades: item `speed_upgrade`, ONE slot (holds up to 4) in a tab on the RIGHT of the GUI next to the energy bar (separate handler, NBT "Upgrades", no automation access); each halves the time, total energy unchanged.
  Recipes: Emberium (3 emberite + 3 titanium ingots -> 3 emberium, 40M FE, 60 s); Emberium Block (3 emberite + 3 titanium blocks -> 3 emberium blocks, 80M FE, 120 s); Speed Upgrade (each column clock / swiftness potion via `forge:nbt` / solarite ingot -> 1, 800k FE, 10 s; mixed columns, so top automation cannot build it). User will define ~5 more recipes.
  Machine crafting recipe (`recipes/titan_factory.json`, shaped): `IVI / GMG / SGS` = emberite ingot, glass
  (`forge:glass/colorless`), iron gear (`forge:gears/iron`), machine block (tag `titanutilities:machine_blocks`, optional
  entries: mekanism steel casing, thermal machine frame, etc. — modpacks extend it by datapack), solarite ingot.
- Titan Crafter (`titan_crafter`): 9x7 grid (slots 0-62 row by row, 63 output; was 9x6 — user wanted an odd height for symmetric
  recipes; old 55-slot saves are migrated in `loadItems`), 200M FE buffer, same tick logic as the
  factory, no upgrades. Recipe type `titanutilities:titan_crafting` = FIXED 9x7 pattern (7 rows x 9 chars, space = empty,
  no offset/mirror). Top input is "template" style (`TitanCrafterItemHandlers.TopInput`: only tops up slots already
  holding the item); bottom = output. GUI `textures/gui/titan_crafter_gui.png` (240x240). Made in the factory:
  2 crafting tables + 2 enderium blocks (`forge:storage_blocks/enderium`, Thermal) + 2 `forge:gears/iron`, 80M FE, 240 s.
  TODO: switch those gears to `forge:gears/diamond` (available in the pack via Thermal Foundation / Industrial Foregoing); final recipes wait for the
  modpack mod list + special items. Crafter tested OK. Recipes: Titanium Music Disc (36 titanium ingots in a
  diamond shape + `#minecraft:music_discs` in the center, 36M FE, 60 s).
- JEI: `compat/jei/` (`@JeiPlugin`, only loaded by JEI): Titan Factory category using the machine GUI crop, looping
  animation (progress fills, energy bar statically shows only the recipe energy), click areas on the GUI arrows,
  "+" transfer handler (slots 0-5), upgrade tab registered as a JEI extra area. JEI 7.7.1 recipe windows are FIXED at
  198 px wide, so the Titan Crafter category uses a compact custom layout (180 px) instead of its 240 px GUI.

