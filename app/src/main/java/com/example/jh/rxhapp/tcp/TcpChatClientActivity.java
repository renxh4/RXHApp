package com.example.jh.rxhapp.tcp;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jh.rxhapp.R;

public class TcpChatClientActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEditText;
    private Button mClient;
    private Button mServer;
    private StringBuffer mStringBuffer;


    public Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            String mssage = (String) msg.obj;
            mStringBuffer.append("\n" + mssage);
            mTextView.setText(mStringBuffer);
        }
    };
    private TcpChatServer mTcpChatServer;
    private TcpChatClient mTcpChatClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_chat_client);
        initView();
        mTcpChatServer = new TcpChatServer(handler);
        mTcpChatServer.start();
        mTcpChatClient = new TcpChatClient(handler);
        mTcpChatClient.start();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tcp_chat_text);
        mEditText = (EditText) findViewById(R.id.tcp_chat_edit);
        mClient = (Button) findViewById(R.id.tcp_chat_button);
        mServer = (Button) findViewById(R.id.tcp_chat_button1);
        mStringBuffer = new StringBuffer();
        mClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = mEditText.getText().toString().trim();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        mTcpChatClient.sendMessage(trim);
                    }
                };
                mEditText.setText("");
                Thread thread = new Thread(runnable);
                if (thread.isAlive()) {
                    thread.run();
                } else {
                    thread.start();
                }

                hideSoftInput();
            }
        });
        mServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = mEditText.getText().toString().trim();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        mTcpChatServer.sendMessage(trim);
                    }
                };
                mEditText.setText("");
                Thread thread = new Thread(runnable);
                if (thread.isAlive()) {
                    thread.run();
                } else {
                    thread.start();
                }

                hideSoftInput();
            }
        });
    }

    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
