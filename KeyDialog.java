package de.tu_clausthal.in.informatikwerkstatt.sudoku2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

//该类用于实现Dialog,实现自定义的对话框功能
public class KeyDialog extends Dialog {
  //存放代表对话框中按钮的对象（存在view数组里）
  private final View keys[] = new View[9];
  private final int used[]; //成员变量，保存已经使用过的数字
    private MyView myView;

    //构造函数的第二个参数中保存着当前单元格已经使用过的数字
    public KeyDialog(Context context, int[] used, MyView myView) {

        super(context);
        this.myView=myView;
        this.used = used;
    }

    //当一个Dialog第一次显示的时候，会调用其onCreate方法
  @Override
  protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      //设置对话框标题
      setTitle("KeyDialog");
      //用于为该dialog设置布局文件 三行三列
      setContentView(R.layout.keypad);
      findViews();
      //便利整个used数组
      for (int i = 0;i<used.length;i++){
          if (used[i]!=0)//不为0说明已经被使用了
          {
              System.out.println(used[i]);
              keys[used[i]-1].setVisibility(View.INVISIBLE);//设置可见度为不可见
          }
      }
      //为对话框中所有按钮设置监听器
      setListeners();

  }



    //找出九个按钮对象
    private void findViews() {
        keys[0]=findViewById(R.id.keypad1);
        keys[1]=findViewById(R.id.keypad2);
        keys[2]=findViewById(R.id.keypad3);
        keys[3]=findViewById(R.id.keypad4);
        keys[4]=findViewById(R.id.keypad5);
        keys[5]=findViewById(R.id.keypad6);
        keys[6]=findViewById(R.id.keypad7);
        keys[7]=findViewById(R.id.keypad8);
        keys[8]=findViewById(R.id.keypad9);
    }

    //通知MyView对象,刷新整个九宫格显示的数据
    private void returnResult(int tile) {
        //MyView view = new MyView(getContext());
        myView.setSelectedTile(tile);//通过此刷新
        dismiss();//取消对话框的显示

    }
    private void setListeners() {

        for (int i = 0; i<keys.length; i++)
        {
            final int t = i+1; //t是用户选择的数据
            //为数组里每个对象设置见监听器
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(t);
                }

            });

        }
    }
    
}
