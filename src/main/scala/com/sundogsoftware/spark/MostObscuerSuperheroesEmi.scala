package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object MostObscuerSuperheroesEmi {

  // Function to extract the hero ID and number of connections from each line
  def countCoOccurrences(line: String): (Int, Int) = {
    val elements = line.split("\\s+")
    (elements(0).toInt, elements.length - 1)
  }

  case class SuperHeroNames(id: Int, name: String)
  case class SuperHero(_1: Int, _2:  Int)

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MostPopularSuperhero")

    // Load up the superhero co-appearance data
    val lines = sc.textFile("data/marvel-graph.txt")

    // Convert to (heroID, number of connections) RDD
    val pairings = lines.map(countCoOccurrences)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("MostObscureSuperheroes")
      .master("local[*]")
      .getOrCreate()

    // Create schema when reading Marvel-names.txt
    val superHeroNamesSchema = new StructType()
      .add("id", IntegerType, nullable = true)
      .add("name", StringType, nullable = true)

    // Build up a hero ID -> name Dataset
    import spark.implicits._
    val names = spark.read
      .schema(superHeroNamesSchema)
      .option("sep", " ")
      .csv("data/Marvel-names.txt")
      .as[SuperHeroNames]

    // Convert pairings to spark dataset
    val connections = pairings.toDS.as[SuperHero]

    val superHeroConnections = connections.select($"_1".alias("id"), $"_2".alias("connections"))

    // Asuming one connection is the least Emi
    val superHeroOneConnection = superHeroConnections.filter($"connections" === 1).select($"id")

    names.filter($"id".isin(superHeroOneConnection.as[String].collect(): _*)).show()

    // Not assuming one connection is the least Emi
    val superHeroMinConnection: Int = superHeroConnections.orderBy($"connections".asc).first.getInt(1)

    val superHeroesMinConnection = superHeroConnections.filter($"connections" === superHeroMinConnection).select($"id")

    names.filter($"id".isin(superHeroesMinConnection.as[String].collect(): _*)).show()

    // Professor solution
    // Asuming one connection is the least


  }
}
