package ru.mirea.zimin.mireaproject.ui.parameters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ru.mirea.zimin.mireaproject.R;

import static android.content.Context.MODE_PRIVATE;

public class Parameters extends Fragment {
    private TextView textStory;
    private RadioButton radioButtonDark;
    private RadioButton radioButtonLight;
    private EditText editNewGreeting;
    private Button buttonChange;
    private String note;
    private SharedPreferences preferences;
    private final String SAVED_GREETING = "saved_greeting";
    private final String SAVED_COLOR = "saved_color";
    private String color;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parameters, container, false);

        textStory = root.findViewById(R.id.text_story);
        radioButtonDark = root.findViewById(R.id.button_dark_theme);
        radioButtonLight = root.findViewById(R.id.button_light_theme);
        editNewGreeting = root.findViewById(R.id.edit_new_greeting);
        buttonChange = root.findViewById(R.id.button_change);
        preferences = getActivity().getPreferences(MODE_PRIVATE);

        radioButtonDark.setOnClickListener(radioButtonListener);
        radioButtonLight.setOnClickListener(radioButtonListener);
        buttonChange.setOnClickListener(changeClickListener);

        if (!preferences.getString(SAVED_GREETING, "Empty").equals("Empty"))
            textStory.setText(preferences.getString(SAVED_GREETING, "Empty"));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        color = preferences.getString(SAVED_COLOR, "Empty");
        if (color != null) {

            switch (color) {
                case "dark":
                    getView().setBackgroundColor(getResources().getColor(R.color.dark));
                    break;
                case "light":
                    getView().setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                    break;
            }
        }
    }

    View.OnClickListener radioButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton clickedButton = (RadioButton) v;
            switch (clickedButton.getId()) {
                case R.id.button_dark_theme:
                    getView().setBackgroundColor(getResources().getColor(R.color.dark));
                    color = "dark";
                    break;
                case R.id.button_light_theme:
                    getView().setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                    color = "light";
                    break;
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SAVED_COLOR, color);
            editor.apply();
        }
    };

    View.OnClickListener changeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!editNewGreeting.getText().toString().equals("")) {
                note = editNewGreeting.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SAVED_GREETING, note);
                editor.apply();
                textStory.setText(note);
            }
        }
    };
}