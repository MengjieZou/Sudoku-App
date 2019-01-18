package de.tu_clausthal.in.informatikwerkstatt.sudoku2;

public class Game {
    //数独初始化数据的基础
    private final String str = "360000000004230800000004200070460003820000014500013020001900000007048300000000045";
    //数组长度81，把字符串拆分，放到数组里

    private int sudoku[] = new int[9 * 9];

    //用于存储每个单元格已经用过的数据
    //三维数组，第一维存放九宫格x轴坐标(长度为9)，第二维存放九宫格y轴坐标，第三位存放这个位置已经不可用的数据
    private int used[][][] = new int[9][9][];



    public Game() {
        //在构造函数中被调用，赋值给sudoku成员变量数组
        sudoku = fromPuzzlesString(str);
        calculateAllUsedTiles();
    }

    //根据九宫格里x轴和y轴的位置返回一个整形
    private int getTile(int x, int y) {
        return sudoku[y * 9 + x];
        //如 y=3 x=1,3*9+1=28，即第28个数 7
    }

    //根据九宫格里x轴和y轴的位置返回一个字符串
    String getTileString(int x, int y) {
        //根据九宫格中的坐标，返回该座标所应该填写的数字
        int v = getTile(x, y);
        if (v == 0) {
            return "";//得到的值是0返回一个空字符串，不填写
        } else
            return String.valueOf(v);
    }

    //根据一个字符串数据，生成一个整形数组，所谓数独游戏的初始化数据
    protected int[] fromPuzzlesString(String src) {//生成一个长度为81的整形数组
        int[] sudo = new int[src.length()];

        for (int i = 0; i < sudo.length; i++) {
            //charAt(i)把i位置的字符取出来
            //字符串charAt - 0 变成一个整形数字，然后存放在整形数组里，
            //循环结束得到一个数组，与字符串里的数组一一对应

            sudo[i] = src.charAt(i) - '0';

        }
        return sudo;
    }
    //用于计算所有单元格对应的不可用数据
    public void calculateAllUsedTiles(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                used[x][y]=  calculateUsedTiles(x,y);
            }
        }
    }
    //取出某一单元格中不可用数据
    public int[] getUsedTilesByCoor(int x,int y){//Coor坐标，根据x y轴坐标得到这个单元格不可用数据
        return used[x][y];
    }

    //计算某一个单元格中已经不可用的数据
    public int[] calculateUsedTiles(int x, int y) {
        //所有用过的数字都存放在c数组里面
        int c[] = new int[9];
        //计算在y轴方向哪些数据不可用
        for (int i = 0; i < 9; i++) {
            if (i == y) //已选中的格子
                  continue;
                int t = getTile(x, i);//x轴坐标为x,y轴坐标为i的格子
                if (t != 0)
                    c[t - 1] = t;//把t赋值给数组里t-1位置
                    //即已经填好数字的单元格放在数组里面

            }

        //计算在x轴方向哪些数据不可用
        //用法和y轴一样
        for (int i = 0; i < 9; i++) {
            if (i == x)
                continue;
                int t = getTile(i, y);
                if (t != 0)
                    c[t - 1] = t;

        }

        //在每个小九宫格数字不能重复
        int startx = (x/3)*3;
        int starty = (y/3)*3;
        for (int i = startx; i<startx+3;i++){

            for (int j = starty;j<starty+3;j++)
            {
                if (i==startx&&j==starty)
                    continue;
                int t = getTile(i,j);
                if (t != 0)
                    c[t - 1] = t;
            }
        }

        //compress
        //把c数组里的0去掉
        int nused = 0;
        for (int t : c){
            if (t != 0)
               nused++;
        }
        int c1[] = new int[nused];
        nused = 0;
        for (int t : c){
            if (t != 0)
                c1[nused++] = t;
        }
        //返回已经压缩过的c1
        return c1;
    }


    protected boolean setTileIfValid(int x, int y, int value) {
        int tiles[] = getUsedTiles(x,y);//这个格子已经使用过的数字
        if (value!=0){
            for (int tile:tiles){
                if (tile==value)//判断输入的数字和已经使用过的数字是否相等
                    return false; //相等是假，不想等则可以填进去

            }
        }
        setTile(x,y,value);//把用户输入的数据设置到相应位置上
        calculateAllUsedTiles();//重新计算所有单元格对应不可用数字
        return true;

    }
    protected int[] getUsedTiles(int x, int y) {
        return used[x][y];
    }
    private void setTile(int x, int y, int value) {
        sudoku[ y * 9 +x] = value;
    }

}