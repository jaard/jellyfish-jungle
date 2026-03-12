# Jellyfish Jungle

A simple sidescroller game where you take charge of a cute seal's nutritional needs:
Eat the blue jellyfish, avoid the red ones!

I built this many years ago as a deliverable for a Java programming course.

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
