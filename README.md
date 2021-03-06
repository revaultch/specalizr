<p align="center">
  <img width="100rem" src="./api/src/main/resources/logo.svg" />
</p>

# Specalizr

An implementation and platform agnostic, human-readable visual test scenario definition [DSL](./api).
<br>

![Build](https://github.com/borjafernandez/specalizr/actions/workflows/full-build.yaml/badge.svg)

<br>

<p align="center">
  <img width="720px" src="./web/src/main/resources/demo.gif?raw=true" />
</p>
<br>

## What problem does it solve ?

It enables _**Acceptance**_ test automation.

1. You can now provide your developers with test scenarios at the beginning of a development cycle.


2. Developers can use the test scenarios at any time during the development cycle to verify acceptance. Since test
   scenarios are human readable, they also serve as documentation.


3. At the end of the cycle the developers deliver features as test scenarios are green.


4. Finally acceptance tests become non-regression tests for further development cycles.

## How

- By providing a language that relies on visual description of elements rather than underlying implementation.

- By decoupling scenario description from scenario execution.

- By providing scenario players that understand and execute the language.

## A simple example :

Here's a use case involving a unit conversion (using Google unit converter)

<p align="center">
  <img src="./web/src/main/resources/google-test.png" />
</p>

``` java
        final var leftField = field(leftOf(item(with(text("=")))), below(selector(with(text("Length")))));
        final var rightField = field(rightOf(leftField));
        final var actions = first(click(item(with(text("I agree")))))
                .then(write("unit converter").into(field(above(button(with(text("Google Search")))))))
                .then(press(ENTER))
                .then(select("Mile").from(selector(with(text("Meter")))))
                .then(select("kilometre").from(selector(with(text("Centimeter")))))
                .then(clear(leftField))
                .then(write("50").into(leftField))
                .andLastly(validate(that(rightField), containsText("80.4672")));

        play(actions, with(webPlayer));
```

## Main concepts

### Action

A command to be performed on a given element (i.e. press, select, write, clear, ...)

### ActionChain

A list of chained actions representing a test scenario

### Element

Represents a visual component (i.e. button, select, checkbox, link, text ...)

### Query

Describes how to find an element (i.e. with(text("Length")) ... rightOf(item(with(text(...)))))

### Player

A component that takes an Action chain and plays it against any given platform (web, mobile, desktop ...)

## How to

### Define a scenario

Start with the "first" keyword. It will instantiate an ActionChain and will act as a scenario builder.

You can then chain actions aka ActionDefinition by using "then" keyword.

``` java

      first(click(item(with(text("I agree")))))
        .then(write("unit converter").into(field(above(button(with(text("Google Search")))))))
        .then(press(ENTER))
        ...
        ...
        .andLastly(validate(that(rightField), containsText("80.4672")));

```

### Execute a scenario

Once you have defined a scenario, you can play it using an ActionDefinitionPlayerRegistry. This registry is responsible
for providing players for ActionDefinition instances.

``` java
        play(actions, with(seleniumPlayer));
```

see web/src/test/java/ch.qarts.specalizr.examples package for examples

### Extend the framework

#### New command

In order to create a new command you need to implement ActionDefinition interface

Check api for examples

``` java
public class WriteActionDefinition<T extends Writable> implements ActionDefinition 
```

#### New element

In order to create a new element you need to extend ElementBase class

Check api for examples

``` java
public class Button extends ElementBase implements Clickable 
```

#### New query component

In order to create a new query component you need to extend ElementQueryComponent class

Check api for examples

``` java
public class TextQueryComponent extends ElementQueryComponent {
```

## Implementations

There is currently a [Web](./web) implementation available.

## Dependencies

In order to make things happen you need to embed the following dependencies :

- API (contains DSL)

``` xml
        <dependency>
            <groupId>ch.qarts.specalizr</groupId>
            <artifactId>api</artifactId>
            <version>VERSION_HERE</version>
        </dependency>
``` 

- Any player (here web player powered by selenium)

``` xml
        <dependency>
            <groupId>ch.qarts.specalizr</groupId>
            <artifactId>web</artifactId>
            <version>VERSION_HERE</version>
        </dependency>
``` 

# Credits

Inspired by [taiko.dev](https://taiko.dev)

# License

Specalizr is an open-source project distributed under the [MIT](https://choosealicense.com/licenses/mit/) license