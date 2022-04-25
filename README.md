# Allegro Summer Experience 2022
My email in recruitment process: balickimateusz00@gmail.com
## Table of contents
* [Project description](#Project-description)
* [Setup](#Setup)
* [Requirements](#Requirements)
* [Further improvements](#Further-improvements)
## Project description
It's a server application created as an Allegro Summer e-Xperience 2022 Software
Engineer submission. It communicates with GitHub API to fetch data from all pages of the paginated result and can be used to return:
* list of repositories created by a given GitHub user with the information about
programming languages used in each of them
* GitHub user's data (login, name, bio) with the information about programming
languages used in all of the user's repositories
## Setup
### Run from Terminal
* You must have Java 11 and Gradle installed
* Clone this repository:
  * <code>git clone https://github.com/BalickiMateusz/allegro-summer-experience-2022.git</code>
* Open allegro-summer-experience-2022 local project directory in terminal
* Finally, run the project: 
  * <code>gradlew run</code> on Windows or
<code>./gradlew run</code> on Linux
### Run from .jar file
* Download "allegro-summer-experience-2022-1.0-SNAPSHOT.jar" file from here:
  * [First release](https://github.com/BalickiMateusz/allegro-summer-experience-2022/releases/tag/allegro-summer-experience-2022)
* Open terminal and navigate to the directory with the downloaded .jar file
* Run by executing following command:
  * <code>java -jar allegro-summer-experience-2022-1.0-SNAPSHOT.jar</code>
### Run from IntelliJ IDEA (or any other IDE)
* Clone this repository:
  * from IDE itself (e.g. *Get from VCS*)
  * or <code>git clone https://github.com/BalickiMateusz/allegro-summer-experience-2022.git</code>
* Wait until the indexing is done
* Run MainApp.main() 
## Requirements
* You must have Java version 11 installed
## Further improvements
* This application displays all records on one page. Due to the readability concerns, it could be improved by adding pagination.
* This project could be improved by handling other requests to
GitHub API, which would extend returned data with further informations about requested user and
his repositories.
