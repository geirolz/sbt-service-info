## SBT Service Info

This plugin adds custom keys [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo) to have more information about the microservice.
Settings are typed to improve readability and consistency. 

Options
- `serviceBoundedContext` = Specify service bounded context info, default `unknown`.
- `serviceProcessingPurpose` = Specify service processing purpose, can be `OLTP` or `OLAP` default `unknown`.
- `serviceTags` = Specify service useful tags
- `serviceTagsIncludeDependencies` = Boolean to specify if automatic add tags related to service dependencies or not, default `true`.
- `serviceTagsIncludeDependenciesVersions` = When `serviceTagsIncludeDependencies` is true this flag specify if add dependencies version in the tags or not, default `true`
