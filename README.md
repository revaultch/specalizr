<p align="center">
  <img width="100rem" src="./api/src/main/resources/logo.svg" />
</p>

# Specalizr

An implementation and platform agnostic, human-readable visual test scenario definition DSL.

## Project Goal :

Enable visual scenario ATDD in Java. Allow software producers and integrators to deliver E2E / front-end tests with
wireframes.

Streamline collaboration between all project stakeholders.

Example for a google unit conversion use case :

``` java

        final var actions = first(write("unit converter").into(a(field(near(image(with(text("Google"))))))))
          .then(press(ENTER))
          .then(select("Speed").from(selector(with(text("Length")))))
          .then(select("Kilometer").from(selector(with(text("Meter")))))
          .then(select("Mile").from(selector(with(text("Centimeter")))))
          .then(write("10").into(field(near(selector(with(text("Kilometer")))))))
          .andLastly(validate(that(field(near(selector(with(text("Mile")))))), containsText("6,21371")));

        play(actions, with(seleniumPlayer));

```

## Concepts

### Action

A command to be performed on a given element (i.e. press, write, select, write ...)

### Element

Represents a visual component (i.e. button, select, checkbox, link, text ...)

### Query

Describes how to find an element (i.e. with(text("Length")) ... near(image(with(text(...)))))

### Player

A component that takes an Action chain and plays it against any given platform (web, mobile, desktop ...)

## Implementations

There is currently a Selenium implementation available.


