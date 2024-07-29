[![Logo](https://github.com/Uroria/.github/blob/main/github_banner.png?raw=true)](https://uroria.com)

# Uroria Core Libraries
[![Modrinth](https://img.shields.io/modrinth/v/uroria-core?label=modrinth&style=for-the-badge&logo=modrinth&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9)](https://modrinth.com/project/uroria-core)
[![Maven Central](https://img.shields.io/maven-central/v/com.uroria.latest/core?label=latest&style=for-the-badge&logo=apachemaven&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9)](https://central.sonatype.com/artifact/com.uroria.latest/core)
[![Website](https://img.shields.io/badge/docs-%23.svg?label=website&style=for-the-badge&logoColor=%2316f5f5&labelColor=%232D2D2D&color=%233881d9&logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMWVtIiB2aWV3Qm94PSIwIDAgNjQwIDUxMiI+PCEtLSEgRm9udCBBd2Vzb21lIFBybyA2LjQuMiBieSBAZm9udGF3ZXNvbWUgLSBodHRwczovL2ZvbnRhd2Vzb21lLmNvbSBMaWNlbnNlIC0gaHR0cHM6Ly9mb250YXdlc29tZS5jb20vbGljZW5zZSAoQ29tbWVyY2lhbCBMaWNlbnNlKSBDb3B5cmlnaHQgMjAyMyBGb250aWNvbnMsIEluYy4gLS0+PGRlZnM+PHN0eWxlPi5mYS1zZWNvbmRhcnl7b3BhY2l0eTowLjQ7ZmlsbDojNmNlZmYwO30uZmEtcHJpbWFyeXtmaWxsOiM2Y2VmZjA7fTwvc3R5bGU+PC9kZWZzPjxwYXRoIGNsYXNzPSJmYS1wcmltYXJ5IiBkPSJNMzA0IDY0YTE0NCAxNDQgMCAxIDAgMCAyODggMTQ0IDE0NCAwIDEgMCAwLTI4OHpNMTExIDM2N2MtOS40IDkuNC05LjQgMjQuNiAwIDMzLjlzMjQuNiA5LjQgMzMuOSAwbDE4LjUtMTguNWMzNC4zIDI3LjcgNzQuOSA0My44IDExNi41IDQ4LjNWNDY0SDE4NGMtMTMuMyAwLTI0IDEwLjctMjQgMjRzMTAuNyAyNCAyNCAyNEg0MjRjMTMuMyAwIDI0LTEwLjcgMjQtMjRzLTEwLjctMjQtMjQtMjRIMzI4VjQzMC43YzQ5LjEtNS4zIDk2LjgtMjYuNyAxMzQuNC02NC4zYzgxLjctODEuNyA4Ny4xLTIxMSAxNi4xLTI5OC45TDQ5NyA0OWM5LjQtOS40IDkuNC0yNC42IDAtMzMuOXMtMjQuNi05LjQtMzMuOSAwTDQyOC41IDQ5LjZjLTkuNCA5LjQtOS40IDI0LjYgMCAzMy45YzY4LjcgNjguNyA2OC43IDE4MC4yIDAgMjQ4LjlzLTE4MC4yIDY4LjctMjQ4LjkgMGMtOS40LTkuNC0yNC42LTkuNC0zMy45IDBMMTExIDM2N3oiLz48cGF0aCBjbGFzcz0iZmEtc2Vjb25kYXJ5IiBkPSIiLz48L3N2Zz4=)](https://uroria.com/core)
[![Discord](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fdiscord.com%2Fapi%2Finvites%2FRGX8rPshFG%3Fwith_counts%3Dtrue&query=%24.approximate_presence_count&style=for-the-badge&logo=discord&logoColor=%236CEFF0&labelColor=%232D2D2D&color=%233881d9&label=Users%20Online)](https://dc.uroria.com/)





The **Core Libraries** consist of many dependencies, which are
used in nearly all projects, so they should be compiled into one project,
which should be present on **every platform**. It also consists
of many utilities like **Results, Options and Config files**.

Currently supported platforms are **Paper and Velocity**. 
Fabric is currently under construction.

## Downloads

Coming soon on [our Website](https://www.uroria.com).

## License

This project is licensed under the [**Apache 2.0 License**](LICENSE).

## Building

First, download or clone this repository.

Run the following command inside the root directory:

*For unix-like systems:*
```shell
./gradlew shadowJar
```

*For windows systems:*
```shell
./gradlew.bat shadowJar
```

You can find the jars all builds in following directories:

| Platform                            | Path                 |
|-------------------------------------|----------------------|
| Universal (Paper, Velocity, Fabric) | universal/build/libs |
| Paper                               | paper/build/libs     |
| Velocity                            | velocity/build/libs  |
| Fabric                              | fabric/build/libs    |

**Note:** Only Jar-Files with the `-all.jar` extension are executable
on all platforms.
