// Data Structures

// Tuples
// Immutable lists

val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")
println(captainStuff)

// Refer to the individual fields with a ONE-BASED index
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)

// key - value pairs with arrow "->" operator
val picardShip = "Picard" -> "Enterprise-D"
println(picardShip)
println(picardShip._2)

// Different data types
val aBunchOfStuff = ("Kirk", 1964, true)


// Lists
// Like a tuple, but more functionality
// Must be of same type

val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")

// lists are zero-based
println(shipList(1))
println(shipList(0))

println(shipList.head)
println(shipList.tail) // sublist excluding the firs value

for (ship <- shipList) {println(ship)}

// map: apply any function we like to every item in the list

val backwardShips = shipList.map((ship: String) => {ship.reverse})

for (ship <- backwardShips) {println(ship)}


// reduce: combines together all the items in a collection using some function

val numberList = List(1, 2, 3, 4, 5)

val sum = numberList.reduce((x: Int, y: Int) => x + y)

println(sum)

// filter(): removes stuff
val iHateFives = numberList.filter((x: Int) => x != 5)

val iHateThrees = numberList.filter(_ != 3)

// concatenate lists

val moreNumbers = List(6, 7)

val lotsOfNumbers = numberList ++ moreNumbers

val reversed = numberList.reverse

val sorted = reversed.sorted

val lotsOfDuplicates = numberList ++ numberList

val distinctValues = lotsOfDuplicates.distinct

val maxValue = numberList.max

val total = numberList.sum

val hasThree = iHateThrees.contains(3)


// MAP (key-value pairs) Needs to have consistent data type

val shipMap = Map("Kirk" -> "Enterprise-D", "Siski" -> "Deep Space Nine", "Janeway" -> "Voyager")
println(shipMap("Janeway"))

println(shipMap.contains("Archer"))

// deal with missing values in Maps
val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
println(archersShip)

// EXERCISE
// Create a list of the numbers 1-20; your job is to print out numbers that are evenly divisible by three. (Scala's
// modula operator, like other languages, is %, which gives you the remainder after division. For example, 9 % 3 = 0
// because 9 is evenly divisible by 3.) Do this first by iterating through all the items in the list and testing each
// one as you go. Then, do it again by using a filter function on the list instead.

val numbersFromOnetoTwenty = List.range(1, 20 + 1)
println(numbersFromOnetoTwenty)

numbersFromOnetoTwenty.filter(_ % 3 == 0)
