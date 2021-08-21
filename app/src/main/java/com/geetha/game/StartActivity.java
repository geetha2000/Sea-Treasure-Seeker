package com.geetha.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class StartActivity extends AppCompatActivity {
    Button play;

    ImageButton more , shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        play = findViewById(R.id.play);
        more = findViewById(R.id.more);
        shop = findViewById(R.id.shop);

              shop.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      startActivity(new Intent(StartActivity.this, ShopActivity.class));
                      finish();
                  }
              });

                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final PopupMenu popupMenu = new PopupMenu(StartActivity.this, more);
                        popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Intent intent, chooser;
                                int id = menuItem.getItemId();
                                if (id == R.id.feedback) {
                                    intent = new Intent(Intent.ACTION_SEND);
                                    intent.setData(Uri.parse("mailto:"));
                                    String[] to = {"navyag6470@gmail.com"}; //my email
                                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                                    intent.setType("message/rfc822");
                                    chooser = Intent.createChooser(intent, "Send Feedback");
                                    startActivity(chooser);
                                }
                                if (id == R.id.share) {
                                    intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/plain");
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Game");
                                    String sAux = "\n Let me Recommend this game \n\n";
                                    sAux = sAux + "kkk";
                                    intent.putExtra(Intent.EXTRA_TEXT, sAux);
                                    startActivity(Intent.createChooser(intent, "Share"));

                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });

    }
    public void play(View v)
    {
        Intent i = new Intent(this,StartActivity2.class);
        startActivity(i);
    }
}