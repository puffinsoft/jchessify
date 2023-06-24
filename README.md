<p align="center">
    <img src="docs/logo.png"  height="100"/>
</p>

<h2 align="center">Java chess engine framework.</h2>
<p align="center">jChessify makes writing chess engines in Java fun. <br /> It lets you focus on the high-level functionality whilst giving you unparalleled flexibility.</p>

- [x] minimax
- [x] alpha-beta pruning
- [x] opening book support

### Basic Chess Engine
```java
Board chessBoard = new Board();
chessBoard.loadFromFen("f/e/n");

Engine engine = new Engine(new PieceValueEvaluator());
engine.maxDepth = 2;
Move bestMove = engine.getBestMove(chessBoard);
```

> **Note**: chess logic (`Board`, `Move`) is powered by [bhlangonijr/chesslib](https://github.com/bhlangonijr/chesslib)

This example uses `PieceValueEvaluator`, an evaluator which evaluates player advantage through piece values.

### Custom Evaluation

```java
class CustomEvaluator implements Evaluator {
    // positive if advantage, negative if disadvantage
    public int evaluate(Board board){
        // random value between -100 and 100
        return new Random().nextInt(201) - 100;
    }
}
```
```java
Engine engine = new Engine(new CustomEvaluator());
```

### Opening Book

```java
OpeningBook openingBook = new OpeningBook("path/to/book.txt");
String bestMoveSAN = openingBook.findNextMove("e4 e5"); //Nf3
chessBoard.doMove(bestMoveSAN);
```

> **Note**: jChessify's `OpeningBook` uses a specific format. The [default opening book](src/main/resources/games.txt) provided should be enough.

## Installation

You can install jChessify through Apache Maven. Add the following to your `pom.xml`:

```xml
<dependency>
  <groupId>io.github.colonelparrot</groupId>
  <artifactId>jchessify</artifactId>
  <version>1.0.2</version>
</dependency>
```

Installation instructions for Gradle and others can be found [here](https://search.maven.org/artifact/io.github.colonelparrot/jchessify/1.0.2/jar).