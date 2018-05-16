# Lexer

Educational project on discipline " System software"

## Description

The program is designed to check the input character chain for compliance with the regular expression and its division into tokens.

Regular Expression: 
```
(c)+|(ab)+b|(ba)+c
```

If you enter the wrong chain, the program will tell you what you did wrong and offer to start all over again.

## Build requirements

- `jdk 1.8+`
- `maven 3.5.3+`
- `maven-jar-plugin 3.1.0+`

## Building

```
git clone https://github.com/INotEnemy/Lexer.git
cd Lexer/
mvn compile
mvn package
```
  
## Running

```
java -jar target/lexer-1.0.jar "cccbac"
Программа завершила работу без ошибок
Вы ввели: cccbac$

Таблица разобранных лексем:
bac |
c |c |c |
Строка закодированных лексем: L3-0, L3-1, L3-2, L2-0, 
```
