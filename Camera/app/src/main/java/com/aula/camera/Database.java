package com.aula.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class Database {
    // Abrir database
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    public void uploadGaleria(Context context, ImageView foto, Map<String, String> docData) {

        Bitmap bitmap = ((BitmapDrawable) foto.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] databyte = baos.toByteArray();

        storage.getReference("galeria").child("salaf_" + System.currentTimeMillis() + ".jpg").putBytes(databyte)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(context, "Deu bom!", Toast.LENGTH_SHORT).show();
                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            docData.put("url", uri.toString());
                            Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                });
    }

    public void downloadGaleria(ImageView img, Uri urlFirebase) {
        img.setRotation(0);
        Glide.with(img.getContext()).asBitmap().load(urlFirebase).into(img);
    }

    public void getLastPic(ImageView img, String urlFirebase) {
        img.setRotation(0);
        storage.getReference(urlFirebase).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(img.getContext()).asBitmap().load(uri).into(img);
            }
        });
    }
}
