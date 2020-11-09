# Zhixiang Teoh - Project Portfolio Page

## Overview

eCardnomics is a **desktop flashcard application to quickly create, manage, and access new flashcards via a Command
 Line Interface (CLI)**. eCardnomics is targeted at economics students in Junior College in Singapore, and aims to
 enhance students’ study experience as an efficient and handy aid for active recall. 
  
The main goals of this application are to help students store and segment their economics subject syllabus into
 different decks, consolidate bite-sized information within each topic by way of flash cards, and offer a fun
  way for students to study and revise.

## Summary of Contributions

### Code contributed

Summary: [tP Code Dashboard](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=zhixiangteoh)

In short, I did the following:

**v1.0**
* [Set up UG, write Introduction, Preliminaries](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/a4eb7de173daddef27e3c76c7a58b62939063e0b), [Command Summary](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/c9d16d286de7d1338ae8ec4dcaf224f45f8f0b78) 
* UG for [Normal Mode, half of Deck Mode](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/afd37e97d5f1fe4d585538d3b8973cf45dd75b51)
* [Half of Deck Mode commands, for v1.0](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/4f52a8d8aa29b3dbe61442f4eaf3117bf3f1c9ac)
* [JUnit Tests for DeckParser, NormalParser](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/768192db9fdb8e2064ca44348375dd7664faf855), Ui

**v2.0**
* UG for [Game Mode](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/207103b41966691a768502f899e1b6d4c23a3950), [Help commands](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/15b51255ecde6085d941e0825180668e5d1a149d), [Command Summary](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/4129639e2d6e3c6fc421e1e0ebec79148b967d96), FAQ
* DG for [Commands](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/072d00d0e703b9d90d486f0c8ab35bc9f6bf7261) and [Game Mode](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/ba39ebbb46ce52e55001857c0c8a65e57af053a3) sections, [Product Scope, User Stories](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/b22e88ee875479db0b77ee021487ae6253afd3b0)
* Implementation of [Game Mode](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/f3975adca5c43c6172d4cc7d7d5a6452493368d5)
* Refine Help commands
* JUnit Tests for Game Mode

### Enhancements implemented

In v1.0, I split the work with Wayne in implementing the Deck Mode commands. Specifically, I implemented:

* [DeleteCommand](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/4f52a8d8aa29b3dbe61442f4eaf3117bf3f1c9ac)
* [HelpCommand](https://github.com/AY2021S1-CS2113-T14-2/tp/commit/622e584ff2847c15877375fd05a3c7db60bc9858)

`DeleteCommand` required me to implement a `y/n` prompt method for the `Ui` class, to be used by `DeckParser`, which
 was later used by all commands in the application that utilise `y/n` prompts. [Example of the user-interface of the
  `y/n` prompt](https://ay2021s1-cs2113-t14-2.github.io/tp/UserGuide.html#examples-4).

In v2.0, I implemented the [Game Mode feature](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/94). For this, our
 team originally thought it would be just another command implemented as part of Normal Mode or Deck Mode, but I felt
  it deserved its own mode. I created a separate package, `game`, and parser class `GameParser` for Game Mode
  , with the `game` package abstracting away the entire Game Mode implementation from the rest of the application.
    
So the resulting `game` package is structured as such:

```
game 
  | Game
  | GameEngine
     | runGameLoop(), update()
  | GameStorage
     | originalDeck, deque, retestStore
```

Here, I tried as much as possible to incorporate the Single Responsibility Principle, both within the `game` classes
 and the SRP-ness of the existing classes.

### Contributions to team-based tasks

Some examples:

* Re-organised packages a few times
* [Released JAR v1.0 and v1.1](https://github.com/AY2021S1-CS2113-T14-2/tp/releases)
* Maintained issue tracker - authored over 37 issues, closed and opened relevant issues, wrapped up Milestones
* [Created and started several Test classes](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/55), e.g. `UiTest`, `NormalParserTest`, `DeckParserTest`
* [Started and set up User Guide](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/57)
* Wrote some trivial aspects of User Guide (e.g. Introduction, Preliminaries, Command Summary) and Developer Guide
 (e.g. Product Scope, User Stories)
* Formatted and arranged [team Google Docs](https://docs.google.com/document/d/1e6HD8JaxAlITihBmqEDQnyKyVa52FLcAWi3Im_7wS6g/edit)

### Review/mentoring contributions

Some examples:

* Reviewed and merged about 5-10 Pull Requests by other teammates
* [Authored](https://github.com/AY2021S1-CS2113-T14-2/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Azhixiangteoh+) over 30
 Pull Requests

### Contributions beyond the project team

Notable posts/responses posted in forum, and peer software testing:

* [Generating markdown table of contents](https://github.com/nus-cs2113-AY2021S1/forum/issues/108)
* [Comments on others' forum posts](https://github.com/nus-cs2113-AY2021S1/forum/issues?q=is%3Aissue+is%3Aclosed+zhixiangteoh)
* [Peer Evaluation-Dry Run on `Scheduler--;`](https://github.com/zhixiangteoh/ped/issues)

### Contributions to the UG

Mainly [Introduction](https://ay2021s1-cs2113-t14-2.github.io/tp/UserGuide.html#introduction), v1.0 Normal Mode
 commands, [Features - Game Mode](https://ay2021s1-cs2113-t14-2.github.io/tp/UserGuide.html#features---game-mode), [Command Summary](https://ay2021s1-cs2113-t14-2.github.io/tp/UserGuide.html#command-summary).

* Introduction
* Contents
* [v1.0 Normal Mode commands](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/57)
* some v1.0 Deck Mode commands
* [`help` commands](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/156)
* `start` commands
* [Game Mode commands and gameplay description](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/100)
* Anywhere Mode
* FAQ
* Command Summary

### UG Extracts

#### Features - Game Mode

##### Gameplay

Questions are displayed in a randomised order. At each question, the user will:

1. Try to attempt an answer at the question, by typing at the prompt; then
2. Press `<enter>` (optionally with an empty attempt).

Then, the correct answer is displayed, and our 'advanced' algorithm scores the user's attempt against the correct
 answer. Finally, the user is given the option to re-attempt the question later. See below for example gamplay.
 
##### Examples

* Start Game Mode (from within Deck Mode)

```java
[Deck - Micro-Economics]
  > list
// ------------------------------------------------------------
// You are now viewing deck: Micro-Economics
// 1. Question: What is the Law of demand?
// 
// 2. Question: What is the Law of supply?
//
// 3. Question: What is price elasticity of demand?
//
// 4. Question: What is price elasticity of supply?
// ------------------------------------------------------------
[Deck - Micro-Economics]
  > start
```

### Contributions to the DG

Mainly [Commands section](https://ay2021s1-cs2113-t14-2.github.io/tp/DeveloperGuide.html#commands) and 
[Exceptions section](https://ay2021s1-cs2113-t14-2.github.io/tp/DeveloperGuide.html#exceptions) under Design, 
and [Game Mode section](https://ay2021s1-cs2113-t14-2.github.io/tp/DeveloperGuide.html#game-mode) under Features. 

Relevant Pull Requests for DG:

* Commands [v2.0](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/89), [v2.1](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/166)
* [Exceptions](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/166)
* Game Mode [v2.0](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/104), [v2.1](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/166)
* [Trivial](https://github.com/AY2021S1-CS2113-T14-2/tp/pull/105)

### DG Extracts

#### Design - Exceptions section

#### Feature Implementation - Game Mode

#### General Architecture
  
[DG-Implementation-Features-Game-Mode-Architecture](../images-dg/DG-Game-Mode-Architecture-Overview.png?raw=true
 "Game Mode
 Architecture Overview")

[DG-Implementation-Features-Game-Storage-Game-Engine](../images-dg/DG-Game-Storage-Game-Engine.png?raw=true "Game
 Mode Game Storage Game Engine")

#### Example Use Case

For a more contextual use case, consider the following scenario of Econs Wiz attempting the Game Mode for the first
 deck, `Demand-Supply`, in his deck list.

> Note: Focus on the biggest box in the diagram!

![DG-Implementation-Features-Game-Use-Case](../images-dg/DG-Game-Use-Case.png?raw=true "Game Mode Use Case")

**API**: [seedu/ecardnomics/game](https://github.com/AY2021S1-CS2113-T14-2/tp/tree/master/src/main/java/seedu/ecardnomics/game)

Since there are quite a few things going on in this diagram, here are the key takeaways (the last of which arguably
 the most important):
- the retest question pool (`retestStore`) is updated upon the user's attempt at each question and response to the
 prompt to include or exclude the flash card to be displayed again—if `y` then the flash card is added to the
  `retestStore`
- whenever the current question pool (`deque`) is emptied, i.e. all flash cards have been popped off and displayed to
 the user, the retest question pool (`retestStore`) is consulted from which to create a new question pool (`deque`)
- after a specific question is displayed, it does not appear again (even if the user has chosen to re-encounter the
 question) until after all other questions in the current question pool (`deque`) have been exhausted 

#### Sequential Flow

For a more formal sequential flow of the inner workings of Game Mode, the following elaborates the execution
 sequence of Game Mode, from after a `start` command has been parsed in Normal Mode:

![DG-Implementation-Features-Game-Mode-Sequence](../images-dg/DG-Game-Mode-Sequence.png?raw=true "Game Mode UML Sequence
 Diagram")
 
 In the above diagram the key takeaway is the existence of an *intermediary* `game:Game` object that holds
  `GameEngine` and `GameStorage` together. In fact, this is the sole purpose of the `Game` class: to hold the
   current game instance, in a Single-Responsibility-Principle (SRP) and Object-Oriented Programming (OOP) manner. 
   This intermediary role of the `Game` class is also illustrated in the upper part of the earlier [use case
    diagram](#example-use-case). Note how it is from this `game` object that the main game loop, run and managed by
     `GameEngine`, is started.
 
[DG-Implementation-Features-Game-Mode-Sequence-run-game-loop-sd](../images-dg/DG-run-game-loop-sd.png?raw=true "Game
 Mode UML Sequence Diagram run game loop sd")
 
The main game loop. As with all simple games, this flash card game mode is fundamentally built on the concept of a
 possibly never-ending game loop with specific end conditions. In this case, the main end condition is explicitly
  that the `command` object that is parsed and returned upon the `getAttempt()` call (that prompts the user for an
   answer attempt) is either a `DoneGameCommand` or `ExitCommand`. 

> This is not the *only* end condition, though, because the other important but implicit end scenario is when the
> question pool is exhausted (i.e., `storage.deque` is empty) **and** the retest question pool (`storage.retestStore
>`) is empty.

Naturally, the other sequence of special note here is the whole `update()` sequence, and even more specifically the
 `updateDeque()` call within the `update()` sequence. Notice how `updateDeque()` calls `createRandomisedStack(storage
 .retestStore)` with the `retestStore` as argument. This essentially creates a new randomised question pool from the
  retest question pool. 
  
Notice how this `updateDeque()` sequence is only called exactly when the `storage.deque` is empty (i.e., when all
 questions have been popped off the question pool). This is important because it ensures that the user encounters all
  available questions in the `deque` at least once before the retest questions are later displayed. Cross-check this
   with the detailed descriptions of the inner workings of the game loop implementation shown in the earlier
    [architecture](#general-architecture) and [use case](#example-use-case) diagrams.
  
Lastly, notice how `refreshRetestStore()` is called at the end of `updateDeque()` to, as its name suggests, clear the
 retest question pool to get ready to store the next wave of retest questions. This is also covered in the bottom few
  lines of the `GameEngine` portion of the use case diagram.
