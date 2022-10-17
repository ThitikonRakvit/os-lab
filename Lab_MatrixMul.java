import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args){
        int[][] inputA = {{5,6,7},
                          {4,8,9}
                        };
        int[][] inputB = {{6,4},
                          {5,7},
                          {1,1}
                         };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        
        for(int i = 0; i < matC_r; i++){
            for(int j = 0; j < matC_c; j++){
                Thread t = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
                t.start();
                try{/*Q5*/
                    t.join();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        matC.show();
    }   
}

class MatrixMulThread implements Runnable{
    int processing_row;
    int processing_col;
    MyData datA, datB, datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c){
        this.processing_row  = tRow;
        this.processing_col = tCol;
        this.datA = a;
        this.datB = b;
        this.datC = c;
    }

    public void run(){
        for(int i = 0; i < datB.data[0].length; i++){
            datC.data[processing_row][i] = 0;
            for(int j = 0; j < datA.data[processing_row].length; j++){
                datC.data[processing_row][i] += datA.data[processing_row][j] * datB.data[j][i];
            }
        }
    }
}

class MyData{
    int[][] data;

    MyData(int[][] m){
        data = m;
    }

    MyData(int r, int c){
        data = new int[r][c];
        for(int[] aRow : data){
            Arrays.fill(aRow, 9);
        }
    }

    void show(){
        System.out.println(Arrays.deepToString(data));
    }
}