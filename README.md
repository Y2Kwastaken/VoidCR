# VoidCR

VoidCR is a (WIP) server software for [CosmicReach](https://finalforeach.itch.io/cosmic-reach) that is built upon the
ideals of a reliable and easy to use API. VoidCR does not integrate with mixins and instead uses a patch based approach.
While more restrictive for plugin developers this ensures plugin compatibility and better API stability promises cross
version.

## Deprecation Schedule

The deprecation schedule goals for VoidCR are paramount to its goal of cross version compatibility. The current goal
unless sweeping internal changes occur an expected 3 version compatibility guarantee is good for an API like this.

## How To Install

Currently there are no public buildable jars for VoidCR and there won't be for the foreseeable future as this is a
massive project.

### Developer Build

Currently while no releases are on github, you can install a developer build via the following method listed below

1. Clone VoidCR repository
2. enter the void-server folder and add a folder called "decompile"
3. Inside of your new "decompile" folder add the Cosmic-Reach-Server-%VERSION%.jar
4. go back to the main VoidCR folder and run `./gradlew setup`
5. you can run the void-server-VoidCR-all.jar with `java -jar void-server-VoidCR-all.jar`

Please create an issue for any setup issues or ping me on my thread in the Cosmic Reach discord, currently there have been known bugs on windows
and they may not be patched still.

## Contributions

VoidCR contributions are welcome, however, please create an issue **ON GITHUB** regarding your idea before getting to work the last thing that anyone wants is to dump
a ton of time into a pull request that will instantly be closed. Pull Requests will aptly be tested and reviewed before merger
