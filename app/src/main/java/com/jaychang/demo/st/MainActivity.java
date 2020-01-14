package com.jaychang.demo.st;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaychang.st.OnTextClickListener;
import com.jaychang.st.OnTextLongClickListener;
import com.jaychang.st.Range;
import com.jaychang.st.SimpleText;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView textView = (TextView) findViewById(R.id.textView);

    String text = "This is a simple #foo @bar text \n SimpleText";
    String url = "https://github.com/jaychang0917/SimpleText";

    User foo = new User("1001", "foo");
    User bar = new User("1002", "bar");

    SimpleText simpleText = SimpleText.from(text)
      .allStartWith("#", "@")
      .tags(foo, bar)
      .textColor(R.color.link)
      .pressedTextColor(R.color.pressedText)
      .pressedBackground(R.color.pressedBg, 2)
      .onClick(textView, new OnTextClickListener() {
        @Override
        public void onClicked(CharSequence text, Range range, Object tag) {
          Toast.makeText(MainActivity.this, tag.toString(), Toast.LENGTH_SHORT).show();
        }
      })

      .first("simple").textColor(R.color.colorAccent)
      
      .first("SimpleText").bold().textColor(R.color.link).url(url)
      .onLongClick(textView, new OnTextLongClickListener() {
        @Override
        public void onLongClicked(CharSequence text, Range range, Object tag) {
          Toast.makeText(MainActivity.this, "[long click] to share " + tag.toString(), Toast.LENGTH_SHORT).show();
        }
      });

    textView.setText(simpleText);

    findViewById(R.id.frameLayout).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this, "framelayout", Toast.LENGTH_SHORT).show();
      }
    });

    findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(MainActivity.this, "textview", Toast.LENGTH_SHORT).show();
      }
    });

    TextView textView2 = findViewById(R.id.textView2);
    SimpleText simpleText2 = SimpleText.from("以上权限可在系统设置中关闭。请在前查看并 同意《用户协议》和《用户隐私》。")
            .first("《用户协议》","《用户隐私》")
            .onClick(textView2, new OnTextClickListener() {
              @Override
              public void onClicked(CharSequence text, Range range, Object tag) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
              }
            })
            .textColor(R.color.colorPrimary);
    textView2.setText(simpleText2);
  }
}
