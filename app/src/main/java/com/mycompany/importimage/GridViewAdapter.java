package com.mycompany.importimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            row = inflateView(parent);
            holder = new ViewHolder();
            setViewForHolder(row, holder);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        // pass data to holder
        ImageItem item = (ImageItem) data.get(position);
        setTitleForHolder(holder, item);
        setImageForHolder(holder, item);
        return row;
    }

    private void setTitleForHolder(ViewHolder holder, ImageItem item) {
        holder.imageTitle.setText(item.getTitle());
    }

    private void setImageForHolder(ViewHolder holder, ImageItem item) {
        String imageDecodableString = item.getImageDecodableString();
        ScaledDownLoading scaledDownLoading = new ScaledDownLoading();
        Bitmap bitmap = scaledDownLoading.decodeSampledBitmapFromFile(imageDecodableString, 100, 100);
        holder.image.setImageBitmap(bitmap);
    }

    private void setViewForHolder(View row, ViewHolder holder) {
        holder.imageTitle = (TextView) row.findViewById(R.id.text);
        holder.image = (ImageView) row.findViewById(R.id.image);
    }

    private View inflateView(ViewGroup parent) {
        View row;LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

}
