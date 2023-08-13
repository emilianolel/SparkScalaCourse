// VALUES are immutable constants. Syntax: val <const_name>: <type> = <const_value>
val hello: String = "Hola!"

// VARIABLES are mutable. Syntax: var <variable_name>: <variable_type> = <variable_value>
var helloThere: String = hello

helloThere = hello + " There!"

println(helloThere)


// DATA TYPES
val numberOne: Int = 1
val truth: Boolean = true
val letterA: Char = 'a'
val pi: Double = 3.14159265
val piSinglePresision: Float = 3.14159265f
val bigNumber: Long = 123456789
val smallNumber: Byte = 127

// Scala implicitly casts data types to string
// and also don't need to terminate lines with ";"
println("here is a mess: " + numberOne + truth + letterA + pi + bigNumber)

// print format is in Scala. $ indicates variable name, f indicates that is a floating value, %.3 indicates we need 3
// digits of presision displayed after the decimal point.
println(f"Pi is about $piSinglePresision%.3f")

// add left 0's padding to numberOne in order to get a 5 digit string.
println(f"Zero padding on the left: $numberOne%05d")

// include variables
println(s"I can use the s prefix to use variables like $numberOne $truth $letterA")

// include and evaluate any expresion
println(s"The s prefix is not limited to variables; I can include any expression. Like ${1 + 2}.")

// regular expressions
val theUltimateAnswer: String = "To life, the universe, an everything is 42."
val pattern = """.* ([\d]+).*""".r
val pattern(answerString) = theUltimateAnswer // the answer stores in answerSting
val answer = answerString.toInt
println(answer)

// Booleans
val isGreater = 1 > 2
val isLesser = 1 < 2
val impossible = isGreater & isLesser
val anotherWay = isGreater && isLesser

val picard: String = "Picard"
val bestCaptain: String = "Picard"
val isBest: Boolean = picard == bestCaptain

// EXERCISE
// Write some code that takes the value of pi, doubles it, and then prints it within a string with
// three decimal places of precision to the right.

val doubledPi = pi * 2
println(f"The doubled pi value is: $doubledPi%.3f")