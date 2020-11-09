
# Liew Wei Siew - Project Portfolio Page

## Overview

eCardnomics is a **desktop flashcard application to quickly create, manage, and access new flashcards via a Command
Line Interface (CLI)**. While it is mainly targeted at Junior College economics students, eCardnomics can be used
by anyone who would need to keep track of large amounts of *text-based* information. This is facilitated by the 
capabilities to group similar flashcards into decks and tag decks to provide a summary of the information contained
in the deck. Furthermore, the game mode and the feature of exporting decks into powerpoint allow review of the 
help theinformation to user to memorize the text data.

### Summary of Contributions

[Code contribution as detected by RepoSense.](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=liewws)

In v1.0:
* Implemented Help command in Normal Mode
* Refactored code in **`NormalParser`**
* Added JUnit tests for **`CreateCommand`** and  **`DeleteDeckCommand`**
* Updated behaviour of Delete Command in Normal Mode to the specification in the User Guide
* Added assertions to methods in **`NormalParser`**, **`Deck`**, **`DeckList`** and **`FlashCard`**
* Added logging for **`NormalParser`** methods.

##### Enhancements implemented

In v2.0:
* Implemented pretty printing for question and answer when printing flashcards and added JUnit tests
* Implemneted feature to update flashcards in existing decks and added Junit tests
* Implemented feature to add and delete flashcards in a single line command

In v2.1:
* Refactor the pretty printing method to **`Ui`** so that it can be used for formatting other output.
* Implement logging to file to replace the printing of log to console output.

##### Contributions to User Guide

* Wrote the section for the Update Command in Deck Mode
* Added the one-line versions of the commands to add flashcard, delete flashcard and delete deck.

##### Contributions to Developer Guide

* Described the User Interface and Deck Model under the Design section.
* Explained the Pretty Printing feature under the Implementation-Features section.
* Documented some non-functional requirements and manual testing steps. 

##### Contributions to team-based tasks

* Set-up the GitHub team organisation.
* Compiled some FAQs into the User Guide.

##### Review/mentoring contributions

Reviewed and provided recommendations for [this](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/94)
major pull request by Zhixiang.

##### Contributions beyond the project team

* Reviewed the Developer Guide for [ModTracker](https://github.com/nus-cs2113-AY2021S1/tp/pull/62) and provided
suggestions for improving UML diagrams.
* Tested v2.0 of PlanNUS and reported bugs including [this](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/185).
