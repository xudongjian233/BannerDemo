package tv.litui.bannerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Banner banner = findViewById(R.id.banner);

        List<String> list = new ArrayList<>();

        list.add("啦啦啦啦");
        list.add("哈哈哈");
        list.add("emmmmm");

        banner.setImageUrlList(list);

    }
}
