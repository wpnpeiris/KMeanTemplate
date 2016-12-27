
lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.0"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "KMeanTemplate",
    libraryDependencies ++= Seq (
    	"org.apache.spark" %% "spark-core" % "2.0.2",
    	"org.apache.spark" %% "spark-mllib" % "2.0.2")
    
  )