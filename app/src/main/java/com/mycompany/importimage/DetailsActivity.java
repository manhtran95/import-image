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
        // scaled-down loading
        ScaledDownLoading scaledDownLoading = new ScaledDownLoading();
        Bitmap bitmap = scaledDownLoading.decodeSampledBitmapFromFile(imageDecodableString, 250, 250);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }

}
