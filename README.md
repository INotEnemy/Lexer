# Lexer

Учебный проект по дисциплине "Системное Программное Обеспечение"

## Описание

Программа разработана для проверки входной цепочки символов на соответствие регулярному выражению и разбиению её на лексемы.

Регулярное выражение: 
```
(c)+|(ab)+b|(ba)+c
```

При вводе неверной входной цепочки, программа подскажет Вам что именно вы сделали не так и попросит начать всё с начала.

## Требования к сборке

- `jdk 1.8+`
- `maven 3.5.3+`
- `maven-jar-plugin 3.1.0+`

## Сборка

```
git clone https://github.com/INotEnemy/Lexer.git
cd Lexer/
mvn compile
mvn package
```
  
## Запуск

```
java -jar target/lexer-1.0.jar "cccbac"
Программа завершила работу без ошибок
Вы ввели: cccbac$

Таблица разобранных лексем:
bac |
c |c |c |
Строка закодированных лексем: L3-0, L3-1, L3-2, L2-0, 
```
