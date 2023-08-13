// Flow control

// if / else

// one line
if (1 > 3) println("Imposible!") else println("The world make sense.")

// multiple line
if (1 > 3) {
  println("Imposible!")
  println("Really?")
} else {
  println("The world make sense.")
  println("still.")
}

// Matching
val number = 4
number match {
  case 1 => println("One")
  case 2 => println("Two")
  case 3 => println("Three")
  case _ => println("Something else")
}

// for loops
for (x <- 1 to 4) {
  val sqared = x * x
  println(sqared)
}


// while
var x = 10
while (x >= 0) {
  println(x)
  x -= 1
}


// do while
x = 0
do {println(x); x += 1} while (x <= 10)


// Expressions
{val x = 10; x + 20}
println({val x = 10; x + 20})

// EXERCISE
// Write some code that prints out the first 10 values of the Fibonacci sequence.
// This is the sequence where every number is the sum of the two numbers before it.
// So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34

var fibonacciValueAfter = 0
var fibonacciValueBefore = 1
var fibonacciValue = fibonacciValueAfter + fibonacciValueBefore
var it = 0


do {
  it match {
    case 0 => println(0)
    case 1 => println(1)
    case _ => {
      println(fibonacciValue);
      fibonacciValueAfter = fibonacciValueBefore;
      fibonacciValueBefore = fibonacciValue;
      fibonacciValue = fibonacciValueAfter + fibonacciValueBefore
    }
  };
  it += 1
} while (it < 10)

