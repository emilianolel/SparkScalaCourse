package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.sql.functions._

object FriendsByAgeDatasetEmi {

  case class Friend (id: Int, name: String, age: Int, friends: Int)

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("FriendsByAgeDatasetEmi")
      .master("local[*]")
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._
    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Friend]

    val friendsByAge = people.select("age", "friends")

    // friendsByAge.groupBy("age").avg("friends").show()

    // friendsByAge.groupBy("age").avg("friends").orderBy("age").show()

    // friendsByAge.groupBy("age").agg(round(avg("friends"), 2)).orderBy("age").show()

    friendsByAge.groupBy("age").agg(round(avg("friends"), 2).alias("friends_avg")).orderBy("age").show()

    spark.stop()
  }

}
