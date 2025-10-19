# String Calculator Kata

## What is the String Calculator?

It's a simple calculator that takes a **string as input** and **evaluates it as a mathematical expression**, 
supporting operations like addition and subtraction and many more.

## Goals

- Practice Test-Driven Development (TDD)
- Learn incremental design by implementing one small feature at a time
- Reinforce clean code practices and simplicity

## Requirements

### Requirement 0 – Integers

If the input is a single integer, return that integer.

Examples:

```shell
"1" -> 1
"456" -> 456
"-2" -> -2
```

### Requirement 1 – Addition

If there are two integers separated by a `+`, return their sum.

Examples:

```shell
"1+1" -> 2
"57+100" -> 157
"1000+0" -> 1000
```

### Requirement 2 – Subtraction

Handle `-` operations as well.

Examples:

```shell
"-2" -> -2
"4-2" -> 2
"40-2" -> 38
"-2+2" -> 0
"-4-10" -> -14
```

### Requirement 3 – Whitespace

Ignore all whitespace in the input string.

Examples:

```shell
" 1" -> 1
"1 " -> 1
"1 + 2" -> 3
" 45 - 60 " -> -15
```

## Future Scopes

Once the above are complete, feel free to extend the calculator step-by-step:

- Multiplication (`*`)
- Division (`/`)
- Floating point numbers
- Parentheses support
- Negative numbers inside parentheses
- Operator precedence
- Error handling for invalid expressions