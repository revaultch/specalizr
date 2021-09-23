<p align="center">
  <img width="100rem" src="../api/src/main/resources/logo.svg" />
</p>

# Specalizr - Web implementation

Uses Selenium / Chrome webdriver to play action chain against chrome

## Supported 

These are the elements and queries supported currently. Of course, you can create your own by extending the DSL.

### Elements

Button, CheckBox, Field, Image, Item (generic element), Link, Panel (generic container), RadioButton, Selector (Dropdown)

### Queries

Contains, Id, Name, Label, Placeholder, Proximity (rightOf, leftOf, above, below), Text

## How to

Consider checking examples under src/test

### Customise a resolver

In case your target website has a custom way of rendering components you can provide your own resolver.

See FormSpreeCustomExample under src/test

