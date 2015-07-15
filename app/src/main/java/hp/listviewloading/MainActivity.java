package hp.listviewloading;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements GetData{

    private int count = 1;
    private MyListAdapter adapter;
    private MyListView listView;
    private ArrayList<ListValues> items,newitems;
    private int[] picture = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化列表数据
        items = itemData();
        listView = (MyListView)findViewById(R.id.MyList);
        adapter = new MyListAdapter(getApplicationContext(), items);
        listView.setAdapter(adapter);
        //初始化ProgressBar
        listView.setLoadingBar(getApplicationContext());
        /*
         *把GetData接口作为对象传给MyListView
         * 这样做是因为数据的更新要在MyListAdapter中使用notifyDataSetChanged()操作
         * 而是否拉到加载数据的最底端要在MyListView中onScroll中判定
         * 因此需要一个东西，可以对特定的listViewhe adapter进行操作
         * 实现接口会是一个较为方便的做法
         */
        listView.setlistener(this);
    }

    //获得listView中的数据
    private ArrayList<ListValues> itemData(){
        newitems = new ArrayList<ListValues>();
        for(int i = 0; i < picture.length; i++){
            String txt = "第 " + count + " 页，第 " + (i+1) + " 项";
            newitems.add(new ListValues(picture[i], txt));
        }
        for(int i = 0;i < newitems.size(); i++){
            Log.i("newitemsValues",newitems.get(i).toString());
        }
        count ++;
        return newitems;
    }

    /*
     *MainActivity实现的接口GetData
     * listView.updateLoadingStatus()有两步操作
     * 一是隐藏Progressbar
     * 二是将当前的isLoading设为false，表示当前并没有处于价值中的状态
     * adapter.addItems(items)也分两步
     * 一是将新的IteamValues加入ListView的数据列表中
     * 二是通知adapter去更改界面显示
     */
    @Override
    public void getData() {
        new Handler(){}.postDelayed(new Runnable() {
            @Override
            public void run() {
                items = itemData();
                listView.updateLoadingStatus();
                adapter.addItems(items);
            }
        }, 2000);
    }
}
