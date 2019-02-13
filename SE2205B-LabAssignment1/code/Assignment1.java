import java.util.Scanner;
import java.io.File;

public class Assignment1
{
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size) {

    if(size <= 1) {
      int[][] C = new int[1][1];
      C[0][0] = A[0][0] * B[0][0];
      return C;
    }
    else {
      int newSize = size / 2;
      int[][] A00 = new int[newSize][newSize];
      int[][] A11 = new int[newSize][newSize];
      int[][] B00 = new int[newSize][newSize];
      int[][] B11 = new int[newSize][newSize];
      for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
          A00[i][j] = A[i][j];
          A11[i][j] = A[i + newSize][j + newSize];
          B00[i][j] = B[i][j];
          B11[i][j] = B[i + newSize][j + newSize];
        }

      }
      int[][][] M = new int[7][newSize][newSize];
      M[0] = denseMatrixMult(sum(A, A, 0, 0, newSize, newSize, newSize), sum(B, B, 0, 0, newSize, newSize, newSize), size/2);
      M[1] = denseMatrixMult(sum(A, A, newSize, 0, newSize, newSize, newSize), B00, size/2);
      M[2] = denseMatrixMult(A00, sub(B, B, 0, newSize, newSize, newSize, newSize), size/2);
      M[3] = denseMatrixMult(A11, sub(B, B, newSize, 0, 0, 0, newSize), size/2);
      M[4] = denseMatrixMult(sum(A, A, 0, 0, 0, newSize, newSize), B11, size/2);
      M[5] = denseMatrixMult(sub(A, A, newSize, 0, 0, 0, newSize), sum(B, B, 0, 0, 0, newSize, newSize), size/2);
      M[6] = denseMatrixMult(sub(A, A, 0, newSize, newSize, newSize, newSize), sum(B, B, newSize, 0, newSize, newSize, newSize), size/2);
      int[][] C = new int[size][size];
      for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
          C[i][j] = M[0][i][j] + M[3][i][j] - M[4][i][j] + M[6][i][j];
          C[i][j + newSize] = M[2][i][j] + M[4][i][j];
          C[i + newSize][j] = M[1][i][j] + M[3][i][j];
          C[i + newSize][j + newSize] = M[0][i][j] - M[1][i][j] + M[2][i][j] + M[5][i][j];
        }
      }
      return C;
    }
  }

  public int [][]sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int [][]c = new int[n][n];
    for(int i = 0; i < n; i++)
    {
      for (int x = 0; x < n; x++)
      {
        c[x][i] = A[x1 + x][y1 + i] + B[x2 + x][y2 + i];
      }
    }
    return c;
  }
  public int [][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int [][]c = new int[n][n];
    for(int i = 0; i< n; i++)
    {
      for (int x = 0; x < n; x++)
      {
        c[x][i] = A[x1 + x][y1 + i] - B[x2 + x][y2 + i];
      }
    }
    return c;
  }

  public int[][] initMatrix(int n) {
    int[][] c = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int k = 0; k < n; k++) {
        c[i][k] = 0;
      }
    }
    return c;
  }

  public void printMatrix(int n, int[][] A) {
    int k;
    System.out.print(n);
    System.out.print("x");
    System.out.print(n);
    System.out.print(" Matrix");
    for (int i = 0; i< n; i++)
    {
      k = 0;
      System.out.println();
      for(k = 0; k < n; k++)
      {
        System.out.print(A[i][k]);
        System.out.print(" ");
      }
    }
    System.out.println();
  }
  public int[][] readMatrix(String filename, int n) throws Exception {
    File file = new File(filename);
    Scanner sc = new Scanner(file);
    int[][] c = new int[n][n];
    for (int x = 0; x< n; x++)
    {
      for(int l = 0; l<n; l++) {
        c[x][l] = sc.nextInt();
      }
    }
    return c;
  }
}