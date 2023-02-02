package edu.princeton.cs.algs4.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBacktrace {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        char[][] ans = solveSudoku(board);
        System.out.println("ans: "+Arrays.deepToString(ans));
        System.out.println("len: "+ans.length);
    }

    public static boolean isValid(char[][] board, int[] position, char value) {
        int pos_x=position[0];
        int pos_y=position[1];

        char[] pos_row=board[pos_x];
        char[] pos_col=new char[9];
        for(int i=0;i<9;i++) {
            pos_col[i]=board[i][pos_y];
        }
        if(Arrays.binarySearch(pos_row, value)>=0||Arrays.binarySearch(pos_col, value)>=0) {
            return false;
        }

        int area3x3_x=pos_x/3*3;
        int area3x3_y=pos_y/3*3;
        char[] area3x3_batch=new char[9];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                area3x3_batch[i*3+j] = board[area3x3_x+i][area3x3_y+j];
            }
        }
        if(Arrays.binarySearch(area3x3_batch, value)>=0) {
            return false;
        }

        return true;
    }

    public static int[] getNextPosition(char[][] board, int[] position) {
        int next_x=position[0];
        int next_y=position[1];
        try {
            while (board[next_x][next_y] != '.') {
                next_y += 1;
                if (next_y>=board.length) {
                    next_x += 1;
                    next_y = 0;
                }
                if (next_x>=board.length || next_y>=board.length) {
                    return new int[]{-1, -1};
                }
            }
        }  catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("aaaaaaaaaaaaaaa");
        }
        return new int[]{next_x, next_y};
    }

    private static char[][] solveSudoku(char[][] board) {
        back(board, new int[]{0,0});
        return board;
    }

    private static boolean back(char[][] board, int[] position) {
        if(position[0]==-1&&position[1]==-1) {
            System.out.println("successful");
            return true;
        }
        int pos_x=position[0];
        int pos_y=position[1];
        char pos_value=board[pos_x][pos_y];
        if(pos_value=='.') {
            for (int index=1;index>=1&&index<=10;index++) {
                char value=(char)(index+'0');
                if (isValid(board, position, value)) {
                    board[pos_x][pos_y]=value;
                    int[] next_position=getNextPosition(board, position);
                    if (back(board, next_position)) {
                        return true;
                    } else {
                        board[pos_x][pos_y]='.';
                    }
                }
            }
        } else {
            int[] next_pos=getNextPosition(board, position);
            back(board, next_pos);
        }
        return false;
    }
}
