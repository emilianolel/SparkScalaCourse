package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

object TotalSpentByCustomerEmi {

  def parseLine(line: String): (Int, Float) = {
    val fields = line.split(",")
    val customerId = fields(0).toInt
    val moneySpent = fields(2).toFloat
    (customerId, moneySpent)
  }

  def main(args: Array[String]): Unit = {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using the local machine
    val sc = new SparkContext("local", "TotalSpentByCustomerEmi")

    // Load each line of my book into an RDD
    val input = sc.textFile("data/customer-orders.csv")

    val parsedLines = input.map(parseLine)

    val totalSpentByCustomer = parsedLines.reduceByKey((x, y) => x + y)

    val orderedResults = totalSpentByCustomer.map(x => (x._2, x._1)).sortByKey()

    val results = orderedResults.collect()

    // results.foreach(println)

    for (result <- results) {
      val customer = result._2
      val totalSpentAmount = result._1

      println(f"Customer $customer total spent amount was: $$$totalSpentAmount%.2f")
    }


  }

}
