package com.example.quotescreator;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Features extends AppCompatActivity {
    private static ImageView img;
    private static TextView txtonImg, txtAuthor, txtImg, txtChangeColor, txtShare;
    private static FloatingActionButton fabParent, fabaddImage, fabChangeColor, fabShare;
    private static Boolean isAllFabVisible;
    private static RelativeLayout relativeLayout;
    private static Button btnSave;
    private static int SELECT_PICTURE = 200;
    private static int defaultColor;
    private static final String tag = "Features";
    private static Uri selectedImageUri;
    Context inContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.features);

        // registering all the views with their ids
        img = (ImageView) findViewById(R.id.imgView);
        txtonImg = (TextView) findViewById(R.id.txtOnImage);
        txtAuthor = (TextView) findViewById(R.id.txtAuthorName);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        // Registering fabs
        fabParent = (FloatingActionButton) findViewById(R.id.FabParent);
        fabaddImage = (FloatingActionButton) findViewById(R.id.FabAddImage);
        fabChangeColor = (FloatingActionButton) findViewById(R.id.FabEdit);
        fabShare = (FloatingActionButton) findViewById(R.id.FabShare);
        btnSave = (Button) findViewById(R.id.btnSaved);

        // Also register the Textviews.
        txtImg = (TextView) findViewById(R.id.AddImagetxt);
        txtChangeColor = (TextView) findViewById(R.id.changeTxtColor);
        txtShare = (TextView) findViewById(R.id.txtShare);

        // Now set all the Fabs and all  texts  as gone
        fabaddImage.setVisibility(View.GONE);
        fabChangeColor.setVisibility(View.GONE);
        fabShare.setVisibility(View.GONE);
        txtImg.setVisibility(View.GONE);
        txtChangeColor.setVisibility(View.GONE);
        txtShare.setVisibility(View.GONE);


        // data recieved from the main activity
        txtonImg.setText(getIntent().getStringExtra("quote text"));
        txtAuthor.setText(getIntent().getStringExtra("Author_name"));

        txtonImg.setTextColor(getResources().getColor(R.color.white));
        txtAuthor.setTextColor(getResources().getColor(R.color.white));


        defaultColor = ContextCompat.getColor(Features.this, R.color.design_default_color_primary);

        // set isAllfabsVisible to false
        isAllFabVisible = false;



        /*we will only make all the fabs and action name visible when the FabParent is
        clicked*/
        // Parent floating button click listener
        fabParent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isAllFabVisible) {
                    //when all the fabs becomes true make all the fabs and
                    // action name Visible
                    fabaddImage.show();
                    fabChangeColor.show();
                    fabShare.show();
                    txtImg.setVisibility(View.VISIBLE);
                    txtChangeColor.setVisibility(View.VISIBLE);
                    txtShare.setVisibility(View.VISIBLE);

                    isAllFabVisible = true;

                } else {
                    // when isallfabsvisible becomes true makes all the fabs and action name gone.
                    fabaddImage.setVisibility(View.GONE);
                    fabChangeColor.setVisibility(View.GONE);
                    fabShare.setVisibility(View.GONE);
                    txtImg.setVisibility(View.GONE);
                    txtChangeColor.setVisibility(View.GONE);
                    txtShare.setVisibility(View.GONE);

                    isAllFabVisible = false;


                }
            }
        });
        //AddImage listener
        // click on addimage to add  image from the gallery to the quotes.
        fabaddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker(v);
            }
        });

        //Save Button Listener
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SaveImage(view);
            }
        });

        // listener for changing text color
        fabChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        // listener for sharing image
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareImage();
            }
        });

    }

    // this method will be triggered when the user clicks on the add image fab(float action button).
    public void ImagePicker(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICTURE);
    }

    // this method will be triggered when the user selects the picture;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                selectedImageUri = data.getData();

                Picasso.get().load(selectedImageUri).noPlaceholder().centerCrop().fit()
                        .into((ImageView) findViewById(R.id.imgView));
            }

        }
    }

    // Method to save Image in the Storage.
    public void SaveImage(View view) {
        // coneverting relative layout in image  to save as we have to save image with text written
        // on it i.e we have to save the whole layou
        Bitmap bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path.getAbsolutePath(), "/Quotes" + ".jpg");
        dir.mkdir();
        String Filename = String.format("%d.jpg", System.currentTimeMillis());
        File Outfile = new File(dir, Filename);
        try {
            FileOutputStream fos = new FileOutputStream(Outfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_SHORT).show();


    }

    // create bitmap of layout
    public Bitmap createdBitmap(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        return bitmap;
    }

    //color picker method to change color of text
    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(Features.this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                txtonImg.setTextColor(defaultColor);
                txtAuthor.setTextColor(defaultColor);

            }
        });
        colorPicker.show();

    }

    // method for sharing the image
    public void ShareImage() {

        Bitmap inImage = createdBitmap(relativeLayout);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), inImage, "Title", null);
        if (path == null) {
            System.out.println("Please add the image to the quote");
        }
        Uri imageUri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(intent, "Share By"));
    }
}





