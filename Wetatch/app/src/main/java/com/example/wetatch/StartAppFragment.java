package com.example.wetatch;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartAppFragment extends Fragment {
    private static final String TAG = "!!!!!StartAppFragment: ";
    private static final String SAVE_KEY = "1";

    private boolean isExistTwoPlase;      //можно ли расположить рядом второй фрагмент

    private View viewInflater;              //ссылка на рабочий макет фрагмента 1
    private TextView editTextCityName;      //ссылка на поле ввода названия города в макете start_app
    private Switch switchPressure;          //ссылка на свич давление в макете start_app
    private Switch switchWind;              //ссылка на свич ветер в макете start_app
    private Button buttonShowWeather;       //кнопка вывода погоды города
    private Button buttonSaveCity;

    public StartAppFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_start_app, container, false);
        this.viewInflater = inflater.inflate(R.layout.fragment_start_app, container, false);

        editTextCityName = viewInflater.findViewById(R.id.editTextCityName);    //получить ссылку на название города
        switchPressure = viewInflater.findViewById(R.id.switchAtmos);
        switchWind = viewInflater.findViewById(R.id.switchWind);
        buttonShowWeather = viewInflater.findViewById(R.id.buttonShowWeather);
        buttonSaveCity = viewInflater.findViewById(R.id.button_save_city);
        SharedPreferences sharedPref = getActivity().getPreferences(MODE_PRIVATE);
        loadPreferences(sharedPref);

        Log.i(TAG, "onCreateView()");

        return viewInflater;
    }

    //activity создана, можно к ней обращаться
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //определим, модно ли вывести рядом второй фрагмент вывода
        View detailsFrame = getActivity().findViewById(R.id.out_info);  //существует ли поле для второго фрейма
        //getActivity - получить контекст activity, где расположен фрагмент

        //задается true или false в указатель isExistTwo
        isExistTwoPlase = detailsFrame != null &&
                detailsFrame.getVisibility() == View.VISIBLE;

        //Если можно расположить рядом фрагмент, то расположим его рядом                //?? кажется бесполезное условие
        if (isExistTwoPlase) {
            showOutInfo();
        }

        //обработка нажатия кнопки вывода параметров погоды
        buttonShowWeather.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //актуализация данных
                ActualChoiceState.setCurrentCityName(editTextCityName.getText().toString());
                ActualChoiceState.setSwitchPressure(switchPressure.isChecked());
                ActualChoiceState.setSwitchWindspeed(switchWind.isChecked());
                showOutInfo();      //метод вывода (либо в соседний фрагмент, либо в новую активити)
            }
        });

        //обработчик кнопки сохранение данных
        buttonSaveCity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPref = getActivity().getPreferences(MODE_PRIVATE);
                savePreferences(sharedPref);    // сохранить настройки
            }
        });
    }

    //сохранение позиции (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //метод вывода фрагмента (в случае если возможен вывод фрагмента на тот же экран, выводит на тот же,
    //если нельзя, то открыть вторую activity)
    private void showOutInfo() {
        //в случае если возможен вывод фрагмента на тот же экран, выводим на тот же
        if (isExistTwoPlase) {
            //Проверим, что фрагмент уже существует в activity ()
            OutInfoFragment detail = (OutInfoFragment)getFragmentManager().findFragmentById(R.id.out_info);        // информация о втором activity
            //Создаем новый фрагмент, если он не создавался или изменился город в поле ввода
            if(detail == null) {                                //если фрагмента нет                        //?? в моей программе не вызывается это условие, не удалось смодеоировать
                detail = OutInfoFragment.create();   //создаем фрагмент      create() - метод класса OutInfoFragment
            }
            detail.refresh();
        }
        else {                                              //если нельзя вывести фрагмент рядом, откроем вторую активити
            Intent intent = new Intent();
            intent.setClass(getActivity(), OutInfoActivity.class);  //этот интент предназначен классу OutInfoActivity, никаких параметров в интент, т.к. они есть в static
            startActivity(intent);
        }
    }

    // сохраняем настройки
    private void savePreferences(SharedPreferences sharedPref){
        String homeCity = editTextCityName.getText().toString();

        // для сохранения настроек надо воспользоваться классом Editor
        SharedPreferences.Editor editor = sharedPref.edit();

        // теперь в Editor установим значения
        editor.putString(SAVE_KEY, homeCity);

        // и сохраним файл настроек
        editor.apply();
    }

    private void loadPreferences(SharedPreferences sharedPref){
        // для получения настроек нет необходимости в Editor, получаем их прямо из SharedPreferences
        String valueFirst = sharedPref.getString(SAVE_KEY, "");

        editTextCityName.setText(valueFirst);
    }
}
