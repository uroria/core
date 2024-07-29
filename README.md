[![Logo](https://github.com/Uroria/.github/blob/main/github_banner.png?raw=true)](https://uroria.com)

# Core Libraries
[![Modrinth](https://img.shields.io/modrinth/v/uroria-core?label=modrinth&style=for-the-badge&logo=modrinth&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9)](https://modrinth.com/project/uroria-core)
[![Maven Central](https://img.shields.io/maven-central/v/com.uroria.latest/core?label=latest&style=for-the-badge&logo=apachemaven&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9)](https://central.sonatype.com/artifact/com.uroria.latest/core)
[![Discord](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdiscord.com%2Fapi%2Finvites%2FRGX8rPshFG%3Fwith_counts%3Dtrue&query=%24.approximate_presence_count&style=for-the-badge&logo=discord&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9&label=Users%20Online)](https://dc.uroria.com/)

A Java library containing **various utilities and frameworks** used 
in all Uroria projects on all supported platforms.

## Installation

### Uroria Curepur / Gravity / Bridge
The Core Libraries are already preinstalled on all Curepur, Gravity or Bridge servers.

### Paper / Purpur
- Visit the <a href="https://github.com/uroria/core/releases">Releases page</a>
- Download the <b>latest</b> <code>core-universal-x.x.x.jar</code>
- Copy the `core-universal-x.x.x.jar` into your Paper/Purpur servers `plugins` directory
- Restart (**NOT RELOAD**) your Paper/Purpur server.

### Velocity
- Visit the <a href="https://github.com/uroria/core/releases">Releases page</a>
- Download the <b>latest</b> <code>core-universal-x.x.x.jar</code>
- Copy the `core-universal-x.x.x.jar` into your Velocity servers `plugins` directory
- Restart your Velocity server.

## Usage
To use this library in your own plugins, modules or mods, add the
dependency to your project.

**Replace `$VERSION` with the libraries version installed on your server.**

### Gradle (Groovy)
```groovy
// build.gradle
repositories {
    mavenCentral()
}

dependencies {
    compileOnly 'com.uroria.stable:core:$VERSION'
}
```

### Gradle (Kotlin)
```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.uroria.stable:core:$VERSION")
}
```

### Maven
```xml
<dependencies>
    <dependency>
        <groupId>com.uroria.stable</groupId>
        <artifactId>core</artifactId>
        <version>$VERSION</version>
        <scope>system</scope>
    </dependency>
</dependencies>
```

## License
This project is licensed under the [**Apache 2.0 License**](LICENSE).