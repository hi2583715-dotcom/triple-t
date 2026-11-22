# Wither Form (Fabric, Minecraft 26.3)

Hold the Wither Skull in your OFFHAND to become the Wither. Get it with /witherskull.

- Right-click: one wither skull (0.45 s cooldown)
- F (swap hands): triple skull blast (6 s cooldown). Shift + F swaps hands normally.
- Wither form: flight, soul-fire wings and a crown of three flames
- The item has its own animated texture (assets/witherform/...)

## Build the jar with no setup (GitHub)
1. Make a free GitHub account, click New repository, upload ALL files from this folder
   (including the hidden .github folder), commit.
2. Open the Actions tab -> "Build mod" -> wait for the green check.
3. Open the finished run -> Artifacts -> download witherform-jar.
4. Unzip it and put the .jar (NOT the -sources one) in .minecraft/mods
   with Fabric Loader and Fabric API for 26.3.

## Build locally instead
Install JDK 25 and Gradle, then run: gradle build   (jar appears in build/libs)
