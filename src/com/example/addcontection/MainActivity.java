package com.example.addcontection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_name, tv_email, tv_phone;
	private ImageView tv_head;
	private String name;
	private String email;
	private byte[] imageByte;
	private Bitmap bitmap;
	private String phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_email = (TextView) findViewById(R.id.tv_email);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_head = (ImageView) findViewById(R.id.tv_head);
	}
	public void click(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivityForResult(intent, 1);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, 
         Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (resultCode == 1) {
				if (requestCode == 1) {
					try {
						name = data.getStringExtra("name");
						email = data.getStringExtra("email");
						phone = data.getStringExtra("phone");
						imageByte = data.getByteArrayExtra("image");
						bitmap = BitmapFactory.decodeByteArray(imageByte,0, imageByte.length);
					} catch (Exception e) {
					}
					if (name != null) {
						tv_name.setText("ÐÕÃû	:" + name);
					}
					if (email != null) {
						tv_email.setText("email	:" + email);
					}
					if (phone != null) {
						tv_phone.setText("µç»°ºÅÂë	:" + phone);
					}
					if (bitmap != null) {
						tv_head.setImageBitmap(bitmap);
					}
				}
			}
		}
	}
}
