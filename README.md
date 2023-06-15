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

### Custom Evaluation

```java
class CustomEvaluator implements Evaluator {
    // generate random evaluation
    public int evaluate(Board board){
        // random value between -1 and 1
        final double eval = -1 + Math.random() * 2;
        return eval;
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

# Installation

Coming soon!