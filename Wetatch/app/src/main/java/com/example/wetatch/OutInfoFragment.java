package com.example.wetatch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutInfoFragment extends Fragment {
    private static final String TAG = "!!!!!!OutInfoFragment: ";

    private View viewInflater;              //ссылка на рабочий макет фрагмента 2
    private TextView textViewCityName;      //ссылка на поле вывода названия города в макете start_app
    private TextView textViewTemperatureValue;  //ссылка на поле вывода температуры в макете out_info
    private TextView textViewOutAtmos;      //ссылка на поле выводадавления в макете start_app
    private TextView textViewOutWind;  //ссылка на поле вывода ветра в макете out_info

    //Фабричный метод create создает фрагмент
    public static OutInfoFragment create() {
        OutInfoFragment f = new OutInfoFragment();      //создание
        return f;
    }

    public OutInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.viewInflater = inflater.inflate(R.layout.fragment_out_info, container, false);

        textViewCityName = viewInflater.findViewById(R.id.textViewCityName);    //получить ссылку на название города
        textViewTemperatureValue = viewInflater.findViewById(R.id.textViewTemperatureValue);
        textViewOutAtmos = viewInflater.findViewById(R.id.textViewOutAtmos);
        textViewOutWind = viewInflater.findViewById(R.id.textViewOutWind);

        Log.i(TAG, "onCreateView()");

        return this.viewInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    //обновляет поля ввода
    public void refresh() {
        textViewCityName.setText(ActualChoiceState.getCurrentCityName());

        //тестовые результаты
        if(textViewCityName.getText().toString().equals("Kazan")) {
            textViewTemperatureValue.setText("-5");         //сюда можно поместить настоящий источник данных о температуре
        } else if(textViewCityName.getText().toString().equals("Moscow")) {
            textViewTemperatureValue.setText("-2");         //сюда можно поместить настоящий источник данных о температуре
        } else if(textViewCityName.getText().toString().equals("Peter")) {
            textViewTemperatureValue.setText("-9");         //сюда можно поместить настоящий источник данных о температуре
        }

        if(ActualChoiceState.isSwitchPressure()) {
            //тестовые результаты
            if(textViewCityName.getText().toString().equals("Kazan")) {
                textViewOutAtmos.setText("760 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Moscow")) {
                textViewOutAtmos.setText("740 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Peter")) {
                textViewOutAtmos.setText("780 мм рт. ст");         //сюда можно поместить настоящий источник данных о давлении
            }
        }

        if(ActualChoiceState.isSwitchWindspeed()) {
            //тестовые результаты
            if(textViewCityName.getText().toString().equals("Kazan")) {
                textViewOutWind.setText("5 м/с");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Moscow")) {
                textViewOutWind.setText("3 м/с");         //сюда можно поместить настоящий источник данных о давлении
            } else if(textViewCityName.getText().toString().equals("Peter")) {
                textViewOutWind.setText("10 м/с");         //сюда можно поместить настоящий источник данных о давлении
            }
        }
    }
}
