# Lotto

An application that imitates weekly lottery and calculates how many years it takes to win a jackpot. 

## Table of Contents
* [Introduction](#introduction)
* [Motivation](#motivation)
* [Technologies](#technologies)
* [Install](#install)
* [Features](#features)
* [API](#api)

## Introduction
LottoMachine has three optional types of lotto, with different rate of probability. The lottery runs till jackpot is reached in a lifetime (120 years).

## Motivation
This application was build as __a training project__ for course in Tampere University of Applied Sciences ( or Polytechnic, if you will).

## Technologies
Project is created with:
* Java 8.0
* Visual Studio Code 1.39

## Install
* Clone or download the repo
* In command-line compile with `javac` and dir path

<br>__Run:__
* In command-line with `java` and dir path

## Features
* Three different types of lottery
* Option to insert user's lotto row 
  * straight from command-line or
  * within application
* Keeps count of first appearances of each amount of right numbers e.g. `Got 2 right! Took 0 years`
* Optional print out for weekly row(s) and statistics
* When jackpot is reach, prints out the game statistics
* Shows how frustratingly unlikely it is to hit the jackpot in one lifetime!

## API
Some main concepts in application's API:
### lottoType
* __regular__
<br>Number pool's range: 1 - 40
<br>Lotto row's length: 7

* __bigWin__
<br>Number pool's range: 1 - 80
<br>Lotto row's length: 9

* __testRun__
<br>Number pool's range: 1 - 15
<br>Lotto row's length: 5

### numberPool
Pool of numbers where the correct row is drawn from.
<br>default starting int = 1

### userRow
The lotto row user chooses for himself.

### lottoRow
The lotto row that lottoMachine draws.
