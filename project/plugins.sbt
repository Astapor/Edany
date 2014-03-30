logLevel := Level.Warn

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.4.0")

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

//addSbtPlugin("com.github.philcali" % "sbt-lwjgl-plugin" % "3.1.4")