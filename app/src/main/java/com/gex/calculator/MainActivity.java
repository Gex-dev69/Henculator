package com.gex.calculator;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.suke.widget.SwitchButton;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;


// Use Nhentai Api wrapper
// change buttons to hentai pics
// loads up the nhentai
// ez


/* To Do:
    Fix the text "Doujin Mode ON"
    Make Sauce Method
    Make the API
 */
public class MainActivity extends AppCompatActivity {

    // Buttons
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button15;
    Button equal;
    Button minus;
    Button plus;
    Button Button0;
    Button Back_button;
    Button las;
    //test
    EditText edit_text,Equals;
    RelativeLayout relativeLayout;
    SwitchButton SSreplacement;
    LinearLayout mLinearLayout;
    ListView Lis;
    FrameLayout DD_B;
    // Mode
    int Doujin_mode = 0;


    public static int Sumitup(Editable obb) {
            String a = obb.toString();
            if(a.equals("")){
                return 0;
            }else {
                String operators[] = a.split("[0-9]+");
                String operands[] = a.split("[+-]");
                int agregate = Integer.parseInt(operands[0]);
                for (int i = 1; i < operands.length; i++) {
                    if (operators[i].equals("+"))
                        agregate += Integer.parseInt(operands[i]);
                    else
                        agregate -= Integer.parseInt(operands[i]);
                }
                return agregate;
            }

}
    public void Naughty(String Sauce){
        if(SSreplacement.isChecked()){
            // Function only if Heni is checked
            // Get Sauce
            relativeLayout = new RelativeLayout(this);
            relativeLayout.setBackgroundResource(R.drawable.backg); //or whatever your image is
            setContentView(relativeLayout);
        }
        System.out.println(Sauce);
    }
    public void button_font_setup(){
        // Fonts
        Typeface grunge_font = ResourcesCompat.getFont(this,R.font.grunge);
        Typeface chrumpy_font = ResourcesCompat.getFont(this,R.font.chrumpy);
        Typeface long_haul = ResourcesCompat.getFont(this,R.font.jersey);
        button1.setTypeface(long_haul);
        button2.setTypeface(long_haul);
        button3.setTypeface(long_haul);
        button4.setTypeface(long_haul);
        button5.setTypeface(long_haul);
        button6.setTypeface(long_haul);
        button7.setTypeface(long_haul);
        button8.setTypeface(long_haul);
        button9.setTypeface(long_haul);
        Button0.setTypeface(long_haul);
    }

    public void Setup(){
        // Change the whole Ui
        // Design
        // Fonts
        Typeface grunge_font = ResourcesCompat.getFont(this,R.font.grunge);
        Typeface chrumpy_font = ResourcesCompat.getFont(this,R.font.chrumpy);
        Typeface long_haul = ResourcesCompat.getFont(this,R.font.longhaul);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        edit_text.setText("Douji MODE ON");
        Doujin_mode = 1;
        mLinearLayout = (LinearLayout) findViewById(R.id.Background_image);
        mLinearLayout.setBackgroundResource(R.drawable.backg2); // to set background
        edit_text.setTextSize(50);
        edit_text.setTypeface(chrumpy_font);
        button_font_setup();
        bmb.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                edit_text.setText("");
            }
        }, 2000);
    }


    public void Api_Comp(){

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Downloader(String Sauce) throws URISyntaxException {
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);
        // download works!!!!!!!!!!!!  ez dub
        // get shit on biyatch
        // directory got some issues, will figure out when i have too
        String Url = "https://nhentai.net/g/" + Sauce + "/";
        int downloadId = PRDownloader.download(Url,
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),
                Paths.get(new URI(Url).getPath()).getFileName().toString())
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Toast.makeText(MainActivity.this, "Download Started", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {
                        Toast.makeText(MainActivity.this, "Download Paused", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Download Canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        //long progressPer = progress.currentBytes * 100 / progress.totalBytes;
                        //Equals.setText((int) progressPer);
                        // find a way for progress
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(MainActivity.this, "Download Finished", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
                        System.out.println(error);
                    }
                });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        button1 = findViewById(R.id.button12);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        button4 = findViewById(R.id.button5);
        button5 = findViewById(R.id.button6);
        button6 = findViewById(R.id.button7);
        button7 = findViewById(R.id.button8);
        button8 = findViewById(R.id.button9);
        button9 = findViewById(R.id.button14);
        equal = findViewById(R.id.button10);
        minus = findViewById(R.id.button13);
        plus = findViewById(R.id.button11);
        Button0 = findViewById(R.id.button0);
        Back_button = findViewById(R.id.button16);
        SSreplacement = (SwitchButton)findViewById(R.id.switch_button); // https://github.com/zcweng/SwitchButton
        edit_text = (EditText) findViewById(R.id.Calc);
        Equals = (EditText) findViewById(R.id.Sauce_pass);
        DD_B = (FrameLayout) findViewById(R.id.ff);

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setVisibility(View.INVISIBLE);
        // Boom Menu click event
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new SimpleCircleButton.Builder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    Lis.setVisibility(View.VISIBLE);
                    DD_B.setVisibility(View.VISIBLE);
                    boolean b = DD_B.requestFocus();
                    System.out.println(b);
                }
            }).normalImageRes(R.drawable.download).normalColor(Color.BLUE));
        }

        // List adapter, It works now????? why
        Lis = findViewById(R.id.Lis);
        Lis.setVisibility(View.INVISIBLE);
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        Lis.setAdapter(adapter);
        // https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
        // idk what i did here but it works, Don't touch , Don't even look this way

        DD_B.setBackgroundResource(R.drawable.backg);
        DD_B.setVisibility(View.INVISIBLE);

        // Add title of the download file
        arrayList.add("Download");
        adapter.notifyDataSetChanged();

        // Password String to Doujin
        String pass = "42069";

        // Preset Mode
        SSreplacement.setVisibility(View.INVISIBLE);
        Equals.setClickable(false);
        edit_text.setClickable(false);


        Equals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String cs = String.valueOf(Equals.getText());
                System.out.println(cs);
                if (cs.equals(pass)){
                    SSreplacement.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Test = String.valueOf(edit_text.getText());
                if(Test.length() > 0){
                    edit_text.setText(Test.substring(0,Test.length()-1));
                }else {
                    System.out.println("NOthing here");
                }
            }
        });
        Button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "9");
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!SSreplacement.isChecked()){
                    int x = Sumitup(edit_text.getText());
                    String y = String.valueOf(x);
                    Equals.setText(y);
                    edit_text.setText("");
                }else{
                    String sauce = String.valueOf(edit_text.getText());
                    if(sauce.length() != 6){
                        System.out.println("Invalid amount");
                        Toast.makeText(MainActivity.this,"Sauce Number Invalid",Toast.LENGTH_LONG).show();
                    }else{
                        try{
                            // Nhentai return links
                            // Add a "for loop" for the downloader, ez dub
                            Downloader(sauce);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "+");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_text.setText(edit_text.getText() + "-");
            }
        });
        SSreplacement.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked){
                String y = String.valueOf(Equals.getText());
                if(y.equals(pass)){
                    // pass identified, Do something
                    System.out.println("Auth identified");
                    Setup();
                }
            }
        });
    }

}