package com.termproject.route.route;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {

    private Context mContext;
    private List<RouteData> routeList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView city,textView;
        public MapView mapView;

        public MyViewHolder(View view){
            super(view);
            city = (TextView) view.findViewById(R.id.city);
            textView=(TextView)view.findViewById(R.id.route_info);
            mapView = (MapView)view.findViewById(R.id.map);
        }
    }

    public RouteAdapter(Context mContext,List<RouteData> routeList){
        this.mContext=mContext;
        this.routeList=routeList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sharing,parent,false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position){
        RouteData routeData = routeList.get(position);
        holder.textView.setText(routeData.getName());
        holder.city.setText(routeData.getNumOfMap()+" some");

        //loading album cover using Glide library

    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{

        public MyMenuItemClickListener(){

        }
        public boolean onMenuItemClick(MenuItem menuItem){
            switch (menuItem.getItemId()){
                case R.id.action_add_favourite:
                    Toast.makeText(mContext,"Add to favourite",Toast.LENGTH_SHORT).show();

                default:
            }
            return false;
        }
    }
    @Override
    public int getItemCount(){
        return routeList.size();
    }
}
