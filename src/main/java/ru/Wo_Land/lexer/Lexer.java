package ru.Wo_Land.lexer;

/**
 * @author (Wo_Land) 
 * @version (12/05/2018)
 */
import java.util.ArrayList;
public class Lexer
{
    /*
     задаём управляющую таблицу (таблицу переходов и выходов)
     0 - состояние окончания разбора лексемы
     5 - состояние успешного окончание программы
     6 - состояние ошибки
     1 - 4 рабочие состояния
    */
    
    private int [][]controlTable = 
        {{1,6,1,4,6}, // a
         {3,2,0,6,3}, // b
         {0,6,6,6,0}, // c
         {5,6,6,6,6}};  // $

    private int[] LCount;
    private int row = 0; 
    private int colum = 0; 
    private int nextColum = 0; 

    private String input;   
    private String lineForOutputTable = ""; 
    private String outputLine = ""; 

    private char c[];

    private ArrayList[] outputTable;

    /**
     * Конструктор для объектов класса Lexer
     * @param inputLine - вводимая цепочка символов
     */
    public Lexer(String inputLine)
    {
        outputTable    = new ArrayList[3]; 
        
        outputTable[0] = new ArrayList<String>(); 
        outputTable[1] = new ArrayList<String>(); 
        outputTable[2] = new ArrayList<String>();

        LCount = new int[3]; 

        this.input = inputLine + "$"; 
        
        solution();

    }

    /**
     * Процедура, запускающая парсинг входной строки.
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

                lineForOutputTable + = c[i]; // приписываем лексеме текущий символ
            
                if(nextColum == 0) addAndClear(colum);
            
                if(nextColum == 5) succsessExit(i);
   
                if(nextColum == 6) error(i);                  
        }
    }
    
    /**
     * Процедура, работающая с таблицей переходов и выходов:
     * в зависимости от входного символа определяется следующая строка таблицы, 
     * и, соответственно, следующее состояние.
     * @param i - счётчик символов входной строки
     */
    private void solutionForOneSymb(int i)
    {
        switch(c[i]) // рассматриваем очередной символ входной цепочки
        {
            case 'a': row = 0; break;
            case 'b': row = 1; break;
            case 'c': row = 2; break;
            case '$': row = 3; break;
            default : illegalSymbError(i); 
                      nextColum = 7;
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
        outputTable[x - 1].add(LCount[x - 1],lineForOutputTable); 
        outputLine + = "L" + x + "-" + LCount[x - 1] + ", "; 
        LCount[x - 1]++; 
        lineForOutputTable = ""; 
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
        if(i + 1 == c.length)
        { 
            System.out.println("Программа завершила работу без ошибок"); 
            System.out.println("Вы ввели: " + input);
            System.out.println("Таблица разобранных лексем:");
            printMatrix(outputTable);
            System.out.println("Строка закодированных лексем: " + outputLine);
        } 
        else System.out.println("Программа завершила работу, встретив символ конца строки '$', не дойдя до конца входной цепочки");
        i = c.length; // организуем выход из цикла в методе solution()
    }
    
    private void error(int i)
    {
        System.out.println("Вы не закончили одну из лексем. Не был встречен символ окончания лексемы на " + i + " позиции");
        System.out.println("Программа остановилась на разборе такой цепочки: " + lineForOutputTable);
        i = c.length; 
    }

    private void illegalSymbError(int i)
    {
        System.out.println("Символ: " + c[i] + " недопустим"); 
        System.out.println("Будьте внимательнее при вводе входной цепочки и начните заново");
        i = c.length; 
    }

    private void printMatrix(ArrayList [] matrix)
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j <matrix[i].size(); j++) 
                System.out.print(matrix[i].get(j)+ " |");
            
            System.out.println();
        }
    }
}
