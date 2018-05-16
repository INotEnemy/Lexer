# Lexer

Учебный проект по дисциплине Системное Программное Обеспечение.

## Пример использования

```
$ java -jar LazyStart.jar "cccbac"
Программа завершила работу без ошибок
Вы ввели: cccbac$
Таблица разобранных лексем:

bac |
c |c |c |
Строка закодированных лексем: L3-0, L3-1, L3-2, L2-0, 
```

## Описание  

Данная программа разработана для проверки входной цепочки символов на соответствие регулярному выражению и разбиению её на лексемы.

Регулярное выражение: `(c)+|(ab)+b|(ba)+c`. 
При вводе неверной входной цепочки, программа подскажет Вам что именно вы сделали не так и попросит начать всё с начала.

## Сборка проекта

### Требования к сборке
  
    maven 3.5.3

### Процесс сборки

    Для сборки проекта нам потребуются его исходники. Выбирем удобную нам директорию и пишем 
    `git clone https://github.com/INotEnemy/Lexer.git`  
    Затем используем команду `mvn compile` всего скачаного прокета(находимся в папке Lexer). Если всё хорошо, то идём дальше.
    Если при компиляции выдаёт такую ошибку `No compiler is provided in this environment. Perhaps you are running on a JRE rather than a    JDK?`, то выполняем следующее:
    `mvn -version` после выполнения этой команды должна распечататься информация о версии Maven. Вот что получилось в моём случае:  
    ```
    Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-24T22:49:05+03:00)
    Maven home: C:\1\bin\..
    Java version: 1.8.0_172, vendor: Oracle Corporation
    Java home: C:\Program Files\Java\jre1.8.0_172
    Default locale: ru_RU, platform encoding: Cp1251
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
    ```
    Нас интересует четвёртая строка, а именно:  
    `Java home: C:\Program Files\Java\jre1.8.0_172`
    Это то, где находятся файлы java на моём компьютере. Нам нужно изменить привязку с JRE на JDK. Обычно они лежат где-то рядом.  
    Выполняем такую команду для смены java home:  
    `set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_172`  
    вставляя полный путь из четвёртой строки и заменяя jre1 на jdk1. Мы всё починили. Выполняем `mvn compile` ещё раз.Идём дальше.  
    Теперь, когда мы компилировали проект, нам необходимо собрать его в исполняемый файл. Для этого используем команду:  
    `mvn package`  
    У нас собрался Jar-файл и поместился в папку target под названием lexer-1.0.
  
### Процесс запуска

    Теперь, когда у нас есть jar файл его очень просто запустить. Находясь в директории Jar-файла(либо указывая его полный путь) выполним команду:  
    `java -jar lexer-1.0 "c"`  
    у нас запустилась программа с самым простым аргументом, переданным в качестве параметра "c". Вот что получилось, в результате:
    ```
    Программа завершила работу без ошибок
    Вы ввели: c$
    Таблица разобранных лексем:


    c |
    Строка закодированных лексем: L3-0,
    ```
    Была разобрана лексема, заданная с учётом регулярного выражения.

По всем вопросам писать на почту Vladiok2@mail.ru
