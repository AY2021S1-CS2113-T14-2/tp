
# Huynh Thi Thu Trang - Project Portfolio Page

## Overview
eCardnomics is a **single-use desktop flashcard application to create, manage, modify and interact with new flashcards 
 via Command
Line Interface (CLI)**. Its target user is Junior College Economics students with high volume of terms to remember, 
 eCardnomics can be useful to anyone who want to store and interact with the large amount of information he/she learns. 
 Flashcards with same field of information can be group into decks, which is a collection of similar Flashcards.
 Each deck can also have tags to give a summary of the information contained
in the deck and what field of information the deck is relevant. Furthermore, eCardnomics allows user to play and 
interact
with the Flashcards on a daily basis to enhance users' memory. Finally, Flashcards can be exported to PowerPoint format
which helps users find the learning process more interesting and visually satisfying.

### Summary of Contributions
[Code contribution](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=alwaysnacy)

In v1.0:
* Implemented CreateCommand, DecksCommand and DeletedDeckCommand in DeckCommand
* Add more methods in NormalParser() to follow the Code Quality guidelines
* Added more JUnit tests for **`CreateCommand`** and  **`DecksCommand`**

##### Enhancements implemented

In v2.0:
* Implemented the Tag features for Deck to make it easier to group similar decks and find them
    * Add tag to the deck on creation or an existing deck
    * Untag an existing tag of the decks
* Implemented SearchCommand to find decks that are relevant to interest field
    * search for similar words in tag field of deck
* Add more JUnit tests for all children of DeckCommand

In v2.1:
* Make deck only accept unique name
* Make Tag feature more reliable and efficient by eliminating duplicates and spaces

#### Contributions to documentation

* Wrote guidelines for all the Command I have implemented.
* Review other section.

#### Contributions to the DG

* Wrote the section for the **`Tag`** in Enhancement Features.
* Draw diagram for Overall Logic of the diagram
* Add terms to glossary.