# String Calculator Kata

This repository contains a **String Calculator** implemented using the principles of **Test-Driven Development (TDD)** and **Incremental Design**.

The goal of this kata is not to write a complete calculator from scratch right away, but to **evolve the design step-by-step**, writing only the code necessary to pass the current tests. This encourages thoughtful, test-first development.

## 🧠 What is the String Calculator?

It's a simple calculator that takes a **string as input** and **evaluates it as a mathematical expression**, supporting operations like addition and subtraction.

However, there's a **twist**:  
🚫 You **cannot** use built-in evaluation methods like `eval()` or any equivalent in your programming language.

## ✅ Goals

- Practice Test-Driven Development (TDD)
- Learn incremental design by implementing one small feature at a time
- Reinforce clean code practices and simplicity

## 🚦 Steps of Implementation

> Follow these steps one at a time. Only write the code necessary to make the current test pass. Don't over-engineer!

### 🔢 Step 0 – Integers

If the input is a single integer, return that integer.

Examples:

```shell
"1" -> 1
"456" -> 456
"-2" -> -2
```

### ➕ Step 1 – Addition

If there are two integers separated by a `+`, return their sum.

Examples:

```shell
"1+1" -> 2
"57+100" -> 157
"1000+0" -> 1000
```

### ➖ Step 2 – Subtraction

Handle `-` operations as well.

Examples:

```shell
"-2" -> -2
"4-2" -> 2
"40-2" -> 38
"-2+2" -> 0
"-4-10" -> -14
```

### 🧼 Step 3 – Whitespace

Ignore all whitespace in the input string.

Examples:

```shell
" 1" -> 1
"1 " -> 1
"1 + 2" -> 3
" 45 - 60 " -> -15
```

## 🔧 Future Steps (Optional Enhancements)

Once the above are complete, feel free to extend the calculator step-by-step:

- Multiplication (`*`)
- Division (`/`)
- Floating point numbers
- Parentheses support
- Negative numbers inside parentheses
- Operator precedence
- Error handling for invalid expressions

⚠️ **Remember**: Take it slow. Add one feature at a time. Write a failing test, then the simplest code to pass it.

## 🚫 Restrictions

You are **not allowed** to use:

- `eval()`
- Any built-in expression parser or compiler
- Reflection or unsafe code hacks

The purpose is to **implement parsing and evaluation manually**.

## 🤝 Contributing

Feel free to fork this repo and try the kata yourself. If you'd like to add examples or improvements to this README or the implementation, open a PR!
