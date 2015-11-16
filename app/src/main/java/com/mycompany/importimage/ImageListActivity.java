package com.mycompany.importimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private ArrayList<ImageItem> imageItems; // list of images
    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imageItems = new ArrayList<ImageItem>();

        setAdapterForGridView();

        // clicking on the image will display the image in full view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                Intent detailsIntent = createIntent(item);
                startActivity(detailsIntent);
            }

            @NonNull
            private Intent createIntent(ImageItem item) {
                Intent detailsIntent = new Intent(ImageListActivity.this, DetailsActivity.class);
                detailsIntent.putExtra("title", item.getTitle());
                detailsIntent.putExtra("imageDecodableString", item.getImageDecodableString());
                return detailsIntent;
            }
        });
    }

    private void setAdapterForGridView() {
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, imageItems);
        gridView.setAdapter(gridAdapter);
    }

    public void loadImageFromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    // handle results from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                String imageDecodableString = getImageDecodableString(data);
                addImageToList(imageDecodableString);
            } else {
                displayNotPickedMessage();
            }
        } catch (Exception e) {
            displayErrorMassge();
        }
    }

    private void displayErrorMassge() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show();
    }

    private void displayNotPickedMessage() {
        Toast.makeText(this, "You haven't picked Image",
                Toast.LENGTH_LONG).show();
    }

    private void addImageToList(String imageDecodableString) {
        int size = imageItems.size();
        imageItems.add(new ImageItem(imageDecodableString, "Image#" + size));
    }

    private String getImageDecodableString(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getCursor(selectedImage, filePathColumn);
        int columnIndex = getColumnIndex(filePathColumn[0], cursor);
        String imageDecodableString = cursor.getString(columnIndex);
        cursor.close();
        return imageDecodableString;
    }

    private int getColumnIndex(String columnName, Cursor cursor) {
        cursor.moveToFirst();

        return cursor.getColumnIndex(columnName);
    }

    private Cursor getCursor(Uri selectedImage, String[] filePathColumn) {
        return getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
    }

}
