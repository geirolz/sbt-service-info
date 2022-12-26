## SBT Service Info

[![CICD](https://github.com/geirolz/sbt-service-info/actions/workflows/cicd.yml/badge.svg)](https://github.com/geirolz/sbt-service-info/actions/workflows/cicd.yml)
[![Scala Steward](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://gitlab.com/moneyfarm-tech/sandbox/steward)
[![Mergify Status](https://img.shields.io/endpoint.svg?url=https://gh.mergify.io/badges/geirolz/sbt-service-info&style=flat)](https://mergify.io)

This plugin adds custom keys to [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo) to have more information about the microservice.
Settings are typed to improve readability and consistency. 

Options
- `serviceBoundedContext` = Specify service bounded context info, default `unknown`.
- `serviceProcessingPurpose` = Specify service processing purpose, can be `OLTP` or `OLAP` default `unknown`.
- `serviceTags` = Specify service useful tags
- `serviceTagsIncludeDependencies` = Boolean to specify if automatic add tags related to service dependencies or not, default `true`.
- `serviceTagsIncludeDependenciesVersions` = When `serviceTagsIncludeDependencies` is true this flag specify if add dependencies version in the tags or not, default `true`
