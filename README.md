# Jellyfish Jungle

<img width="800" alt="Image" src="https://github.com/user-attachments/assets/857ddf74-d024-40d2-9d4a-f7e8d787fd3b" />

A simple sidescroller game where you take charge of a cute seal's nutritional needs:
Eat the blue jellyfish, avoid the red ones!

I originally built this in 2013 together with a friend as a deliverable for a Java programming course.
In March 2025 I finally managed to find the complete source code on an old disk - so I did some cleanup, fixed a few bugs and ported the project to Maven.

Some more bugfixes and features may follow.

## Build

### Jar only

```
mvn package
```

The jar will be in `target/jellyfish-jungle-<version>.jar`.

### macOS app (.dmg)

```
mvn package -Pmacos
```

The .dmg will be in `target/dist/`.

Requires macOS (uses `sips` and `iconutil` for icon generation, and `jpackage` for packaging).

## Run

```
java -jar target/jellyfish-jungle-<version>.jar
```
