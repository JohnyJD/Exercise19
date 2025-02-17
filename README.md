# Exercise19

Evaluation console app which outputs different values based on numerical logical operations
/conditions. Conditions can be imported via configuration file. Final result will
be printed on console or, if enabled, to specified file location. Runnable specifications
are modifiable via configuration

Application uses special syntax to define conditions

### Prerequisities
- JDK 21
- Maven project
- Console application

## Defining conditions
App uses interpreter design pattern in order to interpret logical operations based on predefined rules.
This way app have more flexibility to define new rules, and also
with less configuration complexity.

### Syntax
- Operation value Logical_Operation Operation Value ....
- Operations : DIV, EQ, NEQ
- Logical operations: AND, OR
- Each rule must be separate via gap !
- Input value is not assignable - it is passed dynamically via Evaluator
- Example : DIV 2 OR EQ 10 - divisible by 2 or equals 10

## Configuration
- int start - starting value of iteration;
- int end   - end value of iteration;
- int step  - step of iteration;
- expressions - array of expressions in special syntax
- boolean enableFileLogger - enable file logging
- String fileLogPath - path to log file

### Expressions
- String expression - expression e.g. DIV 2
- String result - result value if expression will be true

### Example of config file
`
 {
  "start": 1,
  "end": 100,
  "step": 1,
  "expressions": [
    {
      "expression": "DIV 2",
      "result": "foo"
    },
    {
      "expression": "DIV 4",
      "result": "fuu"
    },
    {
    "expression": "DIV 5 AND NEQ 5 AND NEQ 10",
      "result": "ahoj"
    },
    {
      "expression": "EQ 1 OR EQ 2 OR EQ 3 OR EQ 100",
      "result": "ahoj22222"
    }
  ],
  "enableFileLogger": true,
  "fileLogPath" : "/Users/jandunaj/Desktop/Exercise19/log2.txt"
}
`
