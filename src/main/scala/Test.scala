import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.clustering.{ KMeans, KMeansModel }
import org.apache.spark.mllib.linalg.Vectors

object Test {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("KMean Template")
    val sc = new SparkContext(conf)

    val data = sc.textFile("C:\\dev\\my\\KMeansTemplate\\data\\kmeans_data.txt")
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()

    // Cluster the data into two classes using KMeans
    val numClusters = 2
    val numIterations = 20
    val clusters = KMeans.train(parsedData, numClusters, numIterations)

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    val WSSSE = clusters.computeCost(parsedData)
    println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Within Set Sum of Squared Errors = " + WSSSE)

    // Save and load model
    clusters.save(sc, "target/Test")
    val sameModel = KMeansModel.load(sc, "target/Test")
    // $example off$

    sc.stop()

  }
}