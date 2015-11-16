package com.mycompany.importimage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

// Display the chosen image in full view
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getStringExtra("title");
        String imageDecodableString = getIntent().getStringExtra("imageDecodableString");
        Bitmap bitmap = getBitmapFromFile(imageDecodableString);

        setViewTitle(title);
        setViewImage(bitmap);
    }

    private void setViewImage(Bitmap bitmap) {
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }

    private void setViewTitle(String title) {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);
    }

    // scaled-down loading
    private Bitmap getBitmapFromFile(String imageDecodableString) {
        ScaledDownLoading scaledDownLoading = new ScaledDownLoading();
        return scaledDownLoading.decodeSampledBitmapFromFile(imageDecodableString, 250, 250);
    }

}
