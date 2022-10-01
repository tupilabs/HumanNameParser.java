# HumanNameParse.java

![](https://github.com/tupilabs/HumanNameParser.java/workflows/CI/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tupilabs_HumanNameParser.java&metric=alert_status)](https://sonarcloud.io/dashboard?id=tupilabs_HumanNameParser.java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tupilabs_HumanNameParser.java&metric=coverage)](https://sonarcloud.io/dashboard?id=tupilabs_HumanNameParser.java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.tupilabs/human-name-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.tupilabs/human-name-parser)

Java port Author: Bruno P. Kinoshita

Original library Author: [Jason Priem](https://github.com/jasonpriem) (credits go to him)
Original library Author Website: https://github.com/jasonpriem/HumanNameParser.php

License: [MIT](http://www.opensource.org/licenses/mit-license.php)

## Description
Takes human names of arbitrary complexity and various wacky formats like:

* J. Walter Weatherman 
* de la Cruz, Ana M. 
* James C. ('Jimmy') O'Dell, Jr.
* Dr. Omar A.

and parses out the:

* leading initial (Like "J." in "J. Walter Weatherman")
* first name (or first initial in a name like 'R. Crumb')
* nicknames (like "Jimmy" in "James C. ('Jimmy') O'Dell, Jr.")
* middle names
* last name (including compound ones like "van der Sar' and "Ortega y Gasset"), and
* suffix (like 'Jr.', 'III')
* salutations (like 'Mr.', 'Mrs.', 'Dr')
* postnominals (like 'PHD', 'CPA')

## Usage

```
<dependencies>
  <dependency>
    <groupId>com.tupilabs</groupId>
    <artifactId>human-name-parser</artifactId>
  </dependency>
</dependencies>
```

```
Name name = new Name("Sérgio Vieira de Mello");
HumanNameParserBuilder builder = new HumanNameParserBuilder(name);
HumanNameParserParser parser = builder.build();
String firstName = parser.getFirst();
String nicknames = parser.getNicknames();
// ...
```

## Changelog

### 0.3 (20??-??-??)

- [#15](https://github.com/tupilabs/HumanNameParser.java/pull/15) JUnit Jupiter migration (thanks to @mureinik).

### 0.2 (2020-07-09)

- [#10](https://github.com/tupilabs/HumanNameParser.java/issues/10) support custom
postnominals. We have added a builder to create a parser. It uses the same default
values as before for suffixes, postnominals, prefixes, and salutations. But now
users can tell the builder to replace or append values to these lists.

### 0.1 (2020-04-06)

- Initial release to Maven Central
