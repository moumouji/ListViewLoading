package hp.listviewloading;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 2015/7/15.
 */
public class MyListAdapter extends BaseAdapter {

    private Context context;
    private Resources res;
    private ArrayList<ListValues> items = new ArrayList<ListValues>();
    private Bitmap bmp;

    public MyListAdapter(Context context, Resources res, ArrayList<ListValues> items){
        this.context = context;
        this.res = res;
        this.items = items;
        for(int i = 0;i < items.size(); i++){
            Log.i("adapterItems", items.get(i).toString());
        }
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listitem, null, false);
            viewHolder.imv = (ImageView)convertView.findViewById(R.id.imv);
            viewHolder.tv = (TextView)convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        bmp =BitmapFactory.decodeResource(res, items.get(position).getImage());
        //viewHolder.imv.setImageResource(items.get(position).getImage());
        viewHolder.imv.setImageBitmap(bmp);
        viewHolder.tv.setText(items.get(position).getTxt());
        return convertView;
    }

    //将列表项数据收集
    public void addItems(ArrayList<ListValues> newItems){
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}

class ViewHolder{
    ImageView imv;
    TextView tv;
}
