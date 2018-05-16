package ru.Wo_Land.lexer;

/**
 * @author (Wo_Land) 
 * @version (12/05/2018)
 */
import java.util.ArrayList;
public class Lexer
{

    private int [][]controlTable = //задаём управляющую таблицу(таблицу переходов и выходов)
        {{1,6,1,4,6},//a
         {3,2,0,6,3},//b
         {0,6,6,6,0},//c
         {5,6,6,6,6}}; //$

    private int [] LCount;
    private int row =0; //хранит текущий символ
    private int colum = 0;// хранит текущее состояние
    private int nextColum = 0;// хранит следующее состояние 

    private String input;   //обрабатываемая входная цепочка
    private String lineForOutputTable = ""; // текущая выходная лексема, для занесения в таблицу
    private String outputLine =""; //выходная строка кодированных лексем

    private char c[] ;

    private ArrayList [] outputTable;

    /**
     * Конструктор для объектов класса Lexer
     * 
     */
    public Lexer(String inputLine)
    {
        outputTable    = new ArrayList[3]; //создаём массив из трёх строк типа ArrayList
        
        outputTable[0] = new ArrayList<String>(); // заносим в элементы массива списки
        outputTable[1] = new ArrayList<String>(); // делаем это для динамичности второго измерения
        outputTable[2] = new ArrayList<String>();

        LCount = new int[3]; //создаём три переменные-счётчика для подсчёта и индексации распознанных лексем

        this.input = inputLine+"$"; 
        
        solution();

    }

    /**
     * Процедура, запускающий парсинг входной строки.
     * Посимвольно проверяем в соответсии с заданным регулярным выраженим входные данные.
     */
    public void solution()
    {
        c = input.toCharArray();
        
        for(int i = 0; i < c.length; i++)
        {
                solutionForOneSymb(i);

                colum = nextColum; // сохраняем предыдущее состояние 

                nextColum = controlTable[row][nextColum]; // определяем следующее состояние

                lineForOutputTable += c[i]; //приписываем лексеме текущий символ

                if(nextColum == 0) // переход в начальное состояние указывает, что лексема разобрана и её необходимо записать в таблицу кодирования
                {
                    addAndClear(colum);
                }
                if(nextColum == 5)// переход в конечное состояние означает, что был встречен символ окончания входной цепочки: $
                { 
                    succsessExit(i);
                }
                if(nextColum == 6)  error(i);                  
        }
    }
    
    /**
     * Процедура, работающий с таблицей переходов и выходов:
     * в зависимости от входного символа определяется следующая строка таблицы, и, соответственно, следующее состояние.
     * @param i - счётчик символов входнйо строки
     */
    private void solutionForOneSymb(int i)
    {
        switch(c[i]) // рассматриваем очередной символ входной цепочки
        {
            case 'a': row = 0; break;
            case 'b': row = 1; break;
            case 'c': row = 2; break;
            case '$': row = 3; break;
            default : illegalSymbError(i); nextColum = 7;
            break;
        }
    }
    
     /**
     * Процедура добавляет полученную лексему в выходную таблицу и
     * очищает промежуточные переменные.
     * @param colum - состояние автомата (столбец controlTable)
     */
    private void addAndClear(int colum)
    {
        int x = generLexNum(colum);
        outputTable[x-1].add(LCount[x-1],lineForOutputTable); //вносим в таблицу лексем лексему типа L1,2 или 3
        outputLine += "L"+x+"-"+LCount[x-1]+", "; //приписываем к выходной кодированной строке очередную лексему
        LCount[x-1]++; // увеличиваем счётчик количества распознанных лексем 
        lineForOutputTable = ""; //очищаем контейнер для хранения текущей лексемы
    }
    
    /**
     * Функция, возвразщающая номер разобранной лексемы, исходя из текущего состояния.
     * @param colum - состояние автомата (столбец controlTable)
     */
    private int generLexNum(int colum)
    {
       if (colum == 0) return 3;
       if (colum == 2) return 1;
       return 2;
    }
    
    /**
     * Процедура сообщает о завершении работы, подводит итоги о её завершении и
     * печатает таблицу и строку закодированных лексем.
     * @param i - счётчик символов входнйо строки
     */
    private void succsessExit(int i)
    {
        if(i+1 == c.length)
        { 
            System.out.println("Программа завершила работу без ошибок"); 
            System.out.println("Вы ввели: " + input);
            System.out.println("Таблица разобранных лексем:");
            printMatrix(outputTable);
            System.out.println("Строка закодированных лексем: "+outputLine);
        } 
        else System.out.println("Программа завершила работу, встиетив символ конца строки '$', не дойдя до конца входной цепочки ");
        i = c.length; // организуем выход из цикла в методе solution()
    }
    
    private void error(int i)
    {
        System.out.println("Вы не закончили одну из лексем. Не был встречен символ окончания лексемы на "+i+" позиции");
        System.out.println("Програама остановилась на разборе такой лексемы: "+lineForOutputTable);
        i = c.length; // организуем выход из цикла в методе solution()
    }

    private void illegalSymbError(int i)
    {
        System.out.println("Символ: "+c[i]+" недопустим"); 
        System.out.println("Будьте внимательнее при вводе входной цепочки и начните сначала");
        i = c.length; // организуем выход из цикла в методе solution()
    }

    private void printMatrix(ArrayList [] matrix)
    {
        for (int i = 0; i <3; i++) 
        {
            for (int j = 0; j <matrix[i].size(); j++) 
            {
                System.out.print(matrix[i].get(j)+ " |");
            }
            System.out.println();
        }
    }
}   
