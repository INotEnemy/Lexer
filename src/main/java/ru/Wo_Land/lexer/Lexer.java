package ru.Wo_Land.lexer;

/**
 * @author (Wo_Land) 
 * @version (12/05/2018)
 */
import java.util.ArrayList;
public class Lexer
{

    private int [][]controlTable = //����� ����������� �������(������� ��������� � �������)
        {{1,6,1,4,6},//a
         {3,2,0,6,3},//b
         {0,6,6,6,0},//c
         {5,6,6,6,6}}; //$

    private int [] LCount;
    private int row =0; //������ ������� ������
    private int colum = 0;// ������ ������� ���������
    private int nextColum = 0;// ������ ��������� ��������� 

    private String input;   //�������������� ������� �������
    private String lineForOutputTable = ""; // ������� �������� �������, ��� ��������� � �������
    private String outputLine =""; //�������� ������ ������������ ������

    private char c[] ;

    private ArrayList [] outputTable;

    /**
     * ����������� ��� �������� ������ Lexer
     * 
     */
    public Lexer(String inputLine)
    {
        outputTable    = new ArrayList[3]; //������ ������ �� ��� ����� ���� ArrayList
        
        outputTable[0] = new ArrayList<String>(); // ������� � �������� ������� ������
        outputTable[1] = new ArrayList<String>(); // ������ ��� ��� ������������ ������� ���������
        outputTable[2] = new ArrayList<String>();

        LCount = new int[3]; //������ ��� ����������-�������� ��� �������� � ���������� ������������ ������

        this.input = inputLine+"$"; 
        
        solution();

    }

    /**
     * ���������, ����������� ������� ������� ������.
     * ����������� ��������� � ���������� � �������� ���������� ��������� ������� ������.
     */
    public void solution()
    {
        c = input.toCharArray();
        
        for(int i = 0; i < c.length; i++)
        {
                solutionForOneSymb(i);

                colum = nextColum; // ��������� ���������� ��������� 

                nextColum = controlTable[row][nextColum]; // ���������� ��������� ���������

                lineForOutputTable += c[i]; //����������� ������� ������� ������

                if(nextColum == 0) // ������� � ��������� ��������� ���������, ��� ������� ��������� � � ���������� �������� � ������� �����������
                {
                    addAndClear(colum);
                }
                if(nextColum == 5)// ������� � �������� ��������� ��������, ��� ��� �������� ������ ��������� ������� �������: $
                { 
                    succsessExit(i);
                }
                if(nextColum == 6)  error(i);                  
        }
    }
    
    /**
     * ���������, ���������� � �������� ��������� � �������:
     * � ����������� �� �������� ������� ������������ ��������� ������ �������, �, ��������������, ��������� ���������.
     * @param i - ������� �������� ������� ������
     */
    private void solutionForOneSymb(int i)
    {
        switch(c[i]) // ������������� ��������� ������ ������� �������
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
     * ��������� ��������� ���������� ������� � �������� ������� �
     * ������� ������������� ����������.
     * @param colum - ��������� �������� (������� controlTable)
     */
    private void addAndClear(int colum)
    {
        int x = generLexNum(colum);
        outputTable[x-1].add(LCount[x-1],lineForOutputTable); //������ � ������� ������ ������� ���� L1,2 ��� 3
        outputLine += "L"+x+"-"+LCount[x-1]+", "; //����������� � �������� ������������ ������ ��������� �������
        LCount[x-1]++; // ����������� ������� ���������� ������������ ������ 
        lineForOutputTable = ""; //������� ��������� ��� �������� ������� �������
    }
    
    /**
     * �������, ������������� ����� ����������� �������, ������ �� �������� ���������.
     * @param colum - ��������� �������� (������� controlTable)
     */
    private int generLexNum(int colum)
    {
       if (colum == 0) return 3;
       if (colum == 2) return 1;
       return 2;
    }
    
    /**
     * ��������� �������� � ���������� ������, �������� ����� � � ���������� �
     * �������� ������� � ������ �������������� ������.
     * @param i - ������� �������� ������� ������
     */
    private void succsessExit(int i)
    {
        if(i+1 == c.length)
        { 
            System.out.println("��������� ��������� ������ ��� ������"); 
            System.out.println("�� �����: " + input);
            System.out.println("������� ����������� ������:");
            printMatrix(outputTable);
            System.out.println("������ �������������� ������: "+outputLine);
        } 
        else System.out.println("��������� ��������� ������, �������� ������ ����� ������ '$', �� ����� �� ����� ������� ������� ");
        i = c.length; // ���������� ����� �� ����� � ������ solution()
    }
    
    private void error(int i)
    {
        System.out.println("�� �� ��������� ���� �� ������. �� ��� �������� ������ ��������� ������� �� "+i+" �������");
        System.out.println("��������� ������������ �� ������� ����� �������: "+lineForOutputTable);
        i = c.length; // ���������� ����� �� ����� � ������ solution()
    }

    private void illegalSymbError(int i)
    {
        System.out.println("������: "+c[i]+" ����������"); 
        System.out.println("������ ������������ ��� ����� ������� ������� � ������� �������");
        i = c.length; // ���������� ����� �� ����� � ������ solution()
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
