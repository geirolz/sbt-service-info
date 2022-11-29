(sbt ";clean;publishLocal") &&
cd test &&
(sbt ";clean;test;serviceTags")