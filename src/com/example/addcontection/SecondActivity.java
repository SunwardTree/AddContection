package com.example.addcontection;

import java.io.ByteArrayOutputStream;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends Activity {
	private EditText et_name, et_phone, et_email;
	private ImageView select_ico;
	private byte[] bitmapToBytes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_email = (EditText) findViewById(R.id.et_email);
		select_ico = (ImageView) findViewById(R.id.iv_select);
		select_ico.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, 1);
			}
		});
	}
	public void complete(View view) {
		String name = et_name.getText().toString().trim();
		String phone = et_phone.getText().toString().trim();
		String email = et_email.getText().toString().trim();
		Intent intent = new Intent();
		intent.putExtra("name", name);
		intent.putExtra("phone", phone);
		intent.putExtra("email", email);
		intent.putExtra("image", bitmapToBytes);
		setResult(1, intent);
		this.finish();
	}
	private byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			select_ico.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			Bitmap image = ((BitmapDrawable) select_ico.getDrawable()).getBitmap();
			bitmapToBytes = Bitmap2Bytes(image);
		}
	}
}
