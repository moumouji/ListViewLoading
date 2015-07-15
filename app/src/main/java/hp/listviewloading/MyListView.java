package hp.listviewloading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by hp on 2015/7/15.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {

    boolean isLoading = false;
    GetData listener;
    View loadingbar;
    //ListView
    public MyListView(Context context) {
        super(context);
        setOnScrollListener(this);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnScrollListener(this);
    }


    //OnScrollListener
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    //判断列表是否已经到底
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        //adapter或者列表数据有问题，就不进行操作
        if(getAdapter() == null) return;
        if(getAdapter().getCount() == 0) return;

        int lastVisibleItem = firstVisibleItem + visibleItemCount;
        /*
         *判断列表是否已经到底
         * isLoading用来判断当前是否在执行加载操作，避免重复加载
         */
        if(lastVisibleItem >= totalItemCount && !isLoading){
            removeFooterView(loadingbar);
            addFooterView(loadingbar);
            isLoading = true;
            listener.getData();
        }
    }

    //初始化ProgressBar
    public void setLoadingBar(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        loadingbar = (View) inflater.inflate(R.layout.loadingbar, null ,false);
        addFooterView(loadingbar);
    }

    //得到MainActivity中实现的GetData接口，以进行视图的更新
    public void setlistener(GetData listener){
        this.listener = listener;
    }

    public void updateLoadingStatus(){
        removeFooterView(loadingbar);
        isLoading = false;
    }
}
