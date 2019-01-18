package de.tu_clausthal.in.informatikwerkstatt.sudoku2;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.quicksettings.Tile;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    private  Game game = new Game();
    //方格长宽
    private float width;
    private float height;
    //选定的坐标
    private int selectedX;
    private int selectedY;


    // MyGame mGame=new MyGame();

    //获得屏幕尺寸
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //每一个小格的长宽
        this.width=w/9f;
        this.height=h/9f;
    }


    //绘图函数
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景
        Paint bgPaint=new Paint();
        bgPaint.setColor(getResources().getColor(R.color.sudoku_background));
        canvas.drawRect(0,0,getWidth(),getHeight(), bgPaint);
        //线条画笔
        Paint darkPaint =new Paint();
        darkPaint.setColor(getResources().getColor(R.color.sudoku_dark));
        Paint hilitePaint =new Paint();
        hilitePaint.setColor(getResources().getColor(R.color.sudoku_hilite));
        Paint lightPaint =new Paint();
        lightPaint.setColor(getResources().getColor(R.color.sudoku_light));
        //绘制线条
        for(int i=0;i<9;i++){
            canvas.drawLine(0, i*height,getWidth(),i*height,lightPaint);
            canvas.drawLine(0, i*height+1,getWidth(),i*height+1,hilitePaint);
            canvas.drawLine(i*width, 0,i*width,getHeight(),lightPaint);
            canvas.drawLine(i*width+1,0,i*width+1,getHeight(),hilitePaint);
            if(i%3==0){
                canvas.drawLine(0, i*height,getWidth(),i*height,darkPaint);
                canvas.drawLine(0, i*height+3,getWidth(),i*height+3,hilitePaint);
                canvas.drawLine(i*width, 0,i*width,getHeight(),darkPaint);
                canvas.drawLine(i*width+3, 0,i*width+3,getHeight(),hilitePaint);
            }
        }
        //绘制数字
        Paint numberPaint =new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setTextSize(height*0.75f);
        numberPaint.setTextAlign(Paint.Align.CENTER);

        //调节文字居中
        Paint.FontMetrics fMetrics=numberPaint.getFontMetrics();
        float x=width/2;
        float y=height/2-(fMetrics.ascent+fMetrics.descent)/2;
        //canvas.drawText("1",4*width+x,y,numberPaint);
        super.onDraw(canvas);

        for(int i=0;i<9;i++) //控制x轴位置
        {
            for(int j=0;j<9;j++)//控制y轴位置
            {//i和j分别是x和y轴坐标，宽和高让他居中
              canvas.drawText(game.getTileString(i, j), i*width+x, j*height+y, numberPaint);
            }
        }
        super.onDraw(canvas);
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      if(event.getAction() != MotionEvent.ACTION_DOWN) //判断是否是点击按下的动作
      {
          return super.onTouchEvent(event);
      }
      //判断用户点击的位置在九宫格内坐标
        selectedX =(int) (event.getX()/width);//点击的x轴坐标除单元格宽度，变成整形
        selectedY = (int) (event.getY()/height);

        //这个坐标已经使用过的数字
        int used[] = game.getUsedTilesByCoor(selectedX,selectedY);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<used.length;i++){
            //System.out.println(used[i]);
            sb.append(used[i]);
        }
        KeyDialog keyDialog = new KeyDialog(getContext(),used,this);
        keyDialog.show();

//        //生成一个layoutInflater对象
//        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
//        //利用layoutInflater对象根据布局文件生成一个View
//        View layoutView = layoutInflater.inflate(R.layout.dialog,null);
//        //从生成好的textView中取出相应控件
//        TextView textView = (TextView)layoutView.findViewById(R.id.usedTextId);
//        //设置textView内容
//        textView.setText(sb.toString());
//        //生成一个对话框的Builder对象
//        AlertDialog.Builder buider = new AlertDialog.Builder(this.getContext());
//        //设置对话框显示内容
//        buider.setView(layoutView);
//        //生成对话框对象，并将其显示出来
//        AlertDialog dialog = buider.create();
//        dialog.show();

        return true;

    }
    //刷新
    public void setSelectedTile(int tile) {
        if (game.setTileIfValid(selectedX,selectedY,tile)){
            invalidate();
        }

    }

}
