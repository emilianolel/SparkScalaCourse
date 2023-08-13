package com.sundogsoftware.spark

import org.apache.spark.sql.types.{StructType, FloatType, IntegerType}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TotalSpentByCustomerDatasetEmi {

  case class Customer(customer_id: Int, item_id: Int, price: Float)

  /** Our main function where the action happens */
  def main(args: Array[String]): Unit = {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("TotalSpentByCustomerDatasetEmi")
      .master("local[*]")
      .getOrCreate()

    // define schema
    val customerSchema = new StructType()
      .add("customer_id", IntegerType, nullable = true)
      .add("item_id", IntegerType, nullable = true)
      .add("price", FloatType, nullable = true)

    // Read the file as dataset
    import spark.implicits._
    val ds = spark.read
      .schema(customerSchema)
      .csv("data/customer-orders.csv")
      .as[Customer]

    val dsSelection = ds.select("customer_id", "price")

    dsSelection
      .groupBy("customer_id")
      .agg(round(sum("price"), 2).alias("total_spend_amt"))
      .orderBy("total_spend_amt")
      .show(dsSelection.count.toInt)


    spark.stop()
  }

}
