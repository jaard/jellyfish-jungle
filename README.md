# Jellyfish Jungle

A Java game built with Maven.

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
