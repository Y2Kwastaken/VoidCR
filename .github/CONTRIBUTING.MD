# Contributing To VoidCR

## Requirements

To start making changes you must have the following

- git
- a Java 21 **JDK**

## Setup

VoidCR can be setup very easily by following the steps below

1. Clone VoidCR repository
2. enter the void-server folder and add a folder called "decompile"
3. Inside of your new "decompile" folder add the Cosmic-Reach-Server-%VERSION%.jar
4. go back to the main VoidCR folder and run `./gradlew setup`
5. you can run the void-server-VoidCR-all.jar with `java -jar void-server-VoidCR-all.jar`

## Patches

Often times making modifications within VoidCR will require the creation of patches.
VoidCR's goal is to minimize the amount of patch diff there are various systems in place to do this.

Patches can be built using `./gradlew buildPatches` and likewise applied via `./gradlew applyPatches`

## ASM Transformation

### Using current transformations

VoidCR supports asm transformations through the data folder in `void-server` module `data/voidcr.ajex`
`.ajex` is my in house asm transformation helper. There are two main types of transformations supported
by VoidCR. **AT**, **APIGEN**, access transformers expand the scope of a field. APIGEN generates api mirroring
methods inside of Cosmic Reach's source code without creating patch diff.

After adding a transformation of any kind you must run `./gradlew setup` again. **MAKE SURE YOU BUILD PATCHES BEFORE
SETTING UP AGAIN** as all un built patch progress will be lost.

### Adding a transformation

Creating transformations are well explained over at the [Artisan](https://github.com/Y2Kwastaken/Artisan) repository on
my github.
Before adding a transformation ask yourself the following:

- How much does this reduce patch diff?
- How maintainable is this transformation?
- Is this transformation simple?

Any asm transformation should do the following

- Greatly reduce patch diff
- Be easily maintainable
- Be extremely simple transformations
