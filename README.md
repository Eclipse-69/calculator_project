Console Calculator in Java
 Description
This is a console-based calculator that supports basic arithmetic operations and additional mathematical functions.
The calculator includes a history feature to store previous calculations and error handling for invalid inputs (e.g., division by zero).

 Features
Supports basic operations:

Addition (+), Subtraction (-), Multiplication (*), Division (/), Modulus (%).
Additional functions:
power(base, exponent) — Exponentiation.
sqrt(number) — Square root.
abs(number) — Absolute value.
round(number) — Rounding to the nearest integer.
Error handling (e.g., division by zero, invalid expressions).
Calculation history (history command).
Exit option (exit command).
How to Use?
Run the program (javac Calculator.java and java Calculator).
Enter a mathematical expression:


34 + (76 - 45) * 2 - abs(-5)
View the result and choose to continue or exit.
Use history to see past calculations or exit to close the program.
Requirements
Java 8 or higher.
Example Usage

Welcome to the Calculator!
Enter an expression: 3 + 5 * 2
Result: 13.0
Enter an expression: sqrt(25)
Result: 5.0
Enter an expression: history
Calculation History:
1. 3 + 5 * 2 = 13.0
2. sqrt(25) = 5.0
Enter an expression: exit
Thank you for using the Calculator!
