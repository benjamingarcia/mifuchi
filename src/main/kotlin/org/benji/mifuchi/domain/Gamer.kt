package org.benji.mifuchi.domain

class Gamer(val deck:Map<Int, Card>, val discard:Map<Int, Card>, val hand:List<Card>, val treasure:List<Treasure>)