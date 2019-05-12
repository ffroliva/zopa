# Zopa Technical Test (Java)

This take home test was developed by *Flavio Oliva*.

# Requirements

In order to run the quote.exe file you  first need to execute the following command:

```sh
mvn clean install
```

# Running the code

the quote.exe file is mainly the following command in an .exe file:

```sh
java -jar target/homework-1.0-SNAPSHOT.jar %1 %2
```
P.S.: If you prefer you can ignore the quote.exe file and execute the above command straight from the project's root directory.

# Monthly Repayment Formula.

From my research the formula that most accurately calculates the monthly repayment based on the given example was the following:

```
                       J
  M  =  P  x ------------------------
               1  - ( 1 + J ) ^ -N
```
 WHERE:
 P = principal, the initial amount of the loan
I = the annual interest rate (from 1 to 100 percent)
L = length, the length (in years) of the loan, or at least the length over which the loan is amortized.
The following assumes a typical conventional loan where the interest is compounded monthly. 
First I will define two more variables to make the calculations easier:
J = monthly interest in decimal form = I / (12 x 100)
N = number of months over which loan is amortized = L x 12
 
 Please, have a look at the link bellow for more details about the above formula.
 http://www.hughcalc.org/formula.php

 Any Questions, please don't resitate on contacting me.