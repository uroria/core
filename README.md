[![Logo](https://github.com/Uroria/.github/blob/main/github_banner.png?raw=true)](https://uroria.com)

# Uroria Core Libraries

[![Website](https://img.shields.io/badge/website-%23.svg?style=for-the-badge&color=%232D2D2D&logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMWVtIiB2aWV3Qm94PSIwIDAgNjQwIDUxMiI+PCEtLSEgRm9udCBBd2Vzb21lIFBybyA2LjQuMiBieSBAZm9udGF3ZXNvbWUgLSBodHRwczovL2ZvbnRhd2Vzb21lLmNvbSBMaWNlbnNlIC0gaHR0cHM6Ly9mb250YXdlc29tZS5jb20vbGljZW5zZSAoQ29tbWVyY2lhbCBMaWNlbnNlKSBDb3B5cmlnaHQgMjAyMyBGb250aWNvbnMsIEluYy4gLS0+PGRlZnM+PHN0eWxlPi5mYS1zZWNvbmRhcnl7b3BhY2l0eTowLjQ7ZmlsbDojNmNlZmYwO30uZmEtcHJpbWFyeXtmaWxsOiM2Y2VmZjA7fTwvc3R5bGU+PC9kZWZzPjxwYXRoIGNsYXNzPSJmYS1wcmltYXJ5IiBkPSJNMzA0IDY0YTE0NCAxNDQgMCAxIDAgMCAyODggMTQ0IDE0NCAwIDEgMCAwLTI4OHpNMTExIDM2N2MtOS40IDkuNC05LjQgMjQuNiAwIDMzLjlzMjQuNiA5LjQgMzMuOSAwbDE4LjUtMTguNWMzNC4zIDI3LjcgNzQuOSA0My44IDExNi41IDQ4LjNWNDY0SDE4NGMtMTMuMyAwLTI0IDEwLjctMjQgMjRzMTAuNyAyNCAyNCAyNEg0MjRjMTMuMyAwIDI0LTEwLjcgMjQtMjRzLTEwLjctMjQtMjQtMjRIMzI4VjQzMC43YzQ5LjEtNS4zIDk2LjgtMjYuNyAxMzQuNC02NC4zYzgxLjctODEuNyA4Ny4xLTIxMSAxNi4xLTI5OC45TDQ5NyA0OWM5LjQtOS40IDkuNC0yNC42IDAtMzMuOXMtMjQuNi05LjQtMzMuOSAwTDQyOC41IDQ5LjZjLTkuNCA5LjQtOS40IDI0LjYgMCAzMy45YzY4LjcgNjguNyA2OC43IDE4MC4yIDAgMjQ4LjlzLTE4MC4yIDY4LjctMjQ4LjkgMGMtOS40LTkuNC0yNC42LTkuNC0zMy45IDBMMTExIDM2N3oiLz48cGF0aCBjbGFzcz0iZmEtc2Vjb25kYXJ5IiBkPSIiLz48L3N2Zz4=)](https://uroria.com)
[![Discord](https://img.shields.io/badge/discord-%23.svg?label=&style=for-the-badge&logo=discord&logoColor=%236CEFF0&color=%232D2D2D)](https://dc.uroria.com)
[![Twitter](https://img.shields.io/badge/@urorianetwork-%23.svg?style=for-the-badge&logo=x&logoColor=%236CEFF0&color=%232D2D2D)](https://twitter.uroria.com)
[![YouTube](https://img.shields.io/badge/@uroria-%23.svg?style=for-the-badge&logo=youtube&logoColor=%236CEFF0&color=%232D2D2D)](https://yt.uroria.com)

The **Core Libraries** consist of many dependencies, which are
used in nearly all projects, so they should be compiled into one project,
which should be present on **every platform**. It also consists
of many utilities like **Results, Options and Config files**.

Currently supported platforms are **Paper and Velocity**. 
Fabric is currently under construction.

## Downloads

Coming soon.

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
