package com.hardboy.tobuyamovieticket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button bt_login = (Button) findViewById(R.id.buttons);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView TV1=(TextView) findViewById(R.id.textView);
                TV1.setText("Идет загрузка... Ждите!");
                request_net get_token_net = new request_net(main.this){
                    @Override
                    public void get_Token_complete(boolean result){

                        String str = "";
                        // Парсим данные
                        try{
                            JSONObject dataJsonObj = new JSONObject(message);
                            JSONArray items = dataJsonObj.getJSONArray("Films");

                            for(int i = 0 ; i < items.length() ; i++){
                                JSONObject item = items.getJSONObject(i); // выделяем очередной элемент списка
                                str = str +"\n id: "+ item.getString("id");
                                str = str +"\n Название: "+ item.getString("title");
                                str = str +"\n Описание: "+ item.getString("description");
                                str = str +"\n Режиссер: "+ item.getString("author");
                                str = str +"\n ============================";
                            }

                        } catch(Exception e){
                            e.printStackTrace();
                        }


                        // Обрабатываем данные с сервера

                        TextView TV=(TextView) findViewById(R.id.textView);
                        TV.setText(str);
                    }

                };
                get_token_net.get_Token_now("", "");

            }
        });

    }
}
