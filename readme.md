[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2137/gr2137/-/tree/master/)

# O'burger - food ordering app
> A simple ordering app created using Java and Maven (build tool).

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [How to Build and Execute Project](#how-to-build-and-execute-project)
    * [QA tools](#qa-tools)
* [Project Architecture](#project-architecture)
* [Work Habits, Workflow and Code Quality](#work-habits-workflow-and-code-quality)
* [Key learnings](#key-learnings)
* [Project Status](#project-status)
* [Contact](#contact)


## General Information
**Overview:**
- [oburger](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2137/gr2137/-/tree/master/oburger) - Main project folder
- [docs](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2137/gr2137/-/tree/master/docs) - Contains documentation of every release.


## Technologies Used
- [Java](https://www.oracle.com/java/technologies/downloads/) - version 16.0.1
    - [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.12.4) - version 2.12.5
- [Apache Maven](https://maven.apache.org/) - version 3.8.2
    - [Jacoco](https://www.jacoco.org/jacoco/index.html) - version 0.8.7
    - [Checkstyle](https://checkstyle.sourceforge.io/) - version 3.1.2 
    - [Spotbugs](https://spotbugs.github.io/) - version 4.4.1

## How to Build and Execute Project
The project uses Maven to build and execute and it is fully functional with Gitpod. Otherwise, you should be able to import it into most IDEs with support for Maven.

To build, run `mvn clean install` in the root of the project-folder ([oburger](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2137/gr2137/-/tree/master/oburger)-folder). This will run all tests and quality checks.

The project must run in the [ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2137/gr2137/-/tree/master/oburger/ui)-module, either with `mvn jaxafx:run -f ui/pom.xml` or by first typing `cd ui` followed by `maven javafx:run`.
This should run the JavaFX app, which open an application window (corresponding to a JavaFX Stage with a Scene). Gitpod should notice that the internal VNC port is available and allow you to open the virtual screen in the Preview pane.

### QA tools
Running `mvn verify` will run following commands in given order:
- Validate
- Compile
- Test
- Package
- Verify

You may access the jacoco reports of the modules in:

`oburger/MODULE-NAME/target/site/MODULE-NAME/index.html`

where MODULE-NAME is any of the three modules ("core", "ui", "jackson").

## Project Architecture
The files are on the .json format and are serializations of Receipt objects. When ordering, the Order is used to create a Receipt and is finally serialized into the .json file. When deserialized, new Receipt objects are created, and are used to display previous receipts on the viewReceipts page.

We have chosen implicit saving (app). We think it fits our project better because the use cases are quite different from writing in Word files and the like; our app offers two distinct use cases, where the user never reads and writes to the file simultaneously. They're either reading (viewing receipts) or writing (making an order)

Here is PlantUML-diagram of the most important classes in the core-module:


<img src="/assets/img/oburger-plantuml.png" alt="PlantUML of core-module"/>


## Work Habits, Workflow and Code Quality
We have regular meetings every week on Mondays, Tuesdays and Fridays at Lysholm-bygget, Kalvskinnet. 
Every meeting usually goes as such: 
- Each member updates the others about what they have done, and if they have encountered any issues.
- We go through the issues together, sometimes there are quick fixes other times not. 
- Distribute work between all the members.
- Work in pairs or one-by-one, depending on what we do. 

To ensure code quality, we decided to configure some QA tools (Quality Assurance).
- Test coverage with [Jacoco](https://www.jacoco.org/jacoco/index.html).
- Ensure consistent style with [Checkstyle](https://checkstyle.sourceforge.io/).
- Static analysis for finding bugs with [Spotbugs](https://spotbugs.github.io/).

## Key learnings
_Copied directly from the subject's page_

Kunnskap: 

* har kunnskap om smidig programvareutviklingspraksis, og kunne forklare teknikker som korte utviklingssykluser, parprogrammering og testdrevet utvikling.
* har kunnskap om objektorienterte designprinsipper, kodekvaliteter og verktøy og teknikker for å forbedre dem
* har kunnskap om teknikker og rammer for automatisk testing av programvare
* har kunnskap om problemsporing, kildekodeadministrasjon, kontinuerlig integrasjon med automatiske bygg og deres rolle i smidig utvikling.
* har kunnskap om programvarearkitekturen til desktop og web klienter ved å bruke en REST API-server og hvordan den visualiseres og dokumenteres

Ferdigheter:

* kan skrive brukerhistorier, transformere dem om til utviklingsoppgaver som administreres av et sporingssystem og bruke et kildekodeadministrasjonssystem med forgrening og sammenslåing for å følge dem opp.
* kan designe, skrive og teste kode for desktop-klient og REST API-server, ved hjelp av moderne verktøy for utvikling- og kvalitetsvurdering
* kan strukturere programvare som moduler og konfigurere et byggesystem for å håndtere kjøring og testing av alle deler
* kan dokumentere programvaren med lettvekts formater og verktøy

Generelle kompetanser:
* kan planlegge og koordinere småskala utviklingsprosesser
* kan reflektere over både tekniske og organisatoriske aspekter av et programvareutviklingsprosjekt.


<!-- ## Screenshots
If you have screenshots you'd like to share, include them here.

-->

## Project Status
Project is: **_IN PROGRESS_**


## Contact
Created by:
- Kelvin Gia Huy Ma (kgma@stud.ntnu.no)
- Marcus Stephen Nordal (marcussn@stud.ntnu.no)
- Sindri Mørch Tomasson (sindrimt@stud.ntnu.no)
- Torbjørn Syvertsen Stakvik (torbjss@stud.ntnu.no)
