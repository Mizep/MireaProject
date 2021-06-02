package ru.mirea.zimin.mireaproject.ui.history;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.List;

import ru.mirea.zimin.mireaproject.R;


public class MyDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        EditText valueKey = view.findViewById(R.id.editTextDialog);
        builder.setTitle("Добавить заметку в истории?")
                .setView(view)
                .setMessage("Введите заметку")
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton("Сохранить", (dialog, id) -> {
                    Toast.makeText(getContext(), "История сохранена",
                            Toast.LENGTH_SHORT).show();
                    ru.mirea.zimin.mireaproject.ui.history.AppDatabase db = ru.mirea.zimin.mireaproject.ui.history.App.getInstance().getDatabase();
                    ru.mirea.zimin.mireaproject.ui.hihistories.HistoryDao historyDao = db.storyDao();
                    ru.mirea.zimin.mireaproject.ui.history.Cell cell = new ru.mirea.zimin.mireaproject.ui.history.Cell();
                    cell.text = String.valueOf(valueKey.getText());
                    historyDao.insert(cell);
                    dialog.cancel();
                })
                .setNegativeButton("Отмена",
                        (dialog, id) -> {
                            dialog.cancel();
                        })
                .setNeutralButton("Очистить истории",
                        (dialog, id) -> {
                            Toast.makeText(getContext(), "Истории очищены",
                                    Toast.LENGTH_SHORT).show();
                            ru.mirea.zimin.mireaproject.ui.history.AppDatabase db = ru.mirea.zimin.mireaproject.ui.history.App.getInstance().getDatabase();
                            ru.mirea.zimin.mireaproject.ui.hihistories.HistoryDao historyDao = db.storyDao();
                            List<ru.mirea.zimin.mireaproject.ui.history.Cell> cells = historyDao.getAll();
                            for (ru.mirea.zimin.mireaproject.ui.history.Cell cell : cells){
                                historyDao.delete(cell);
                            }
                            dialog.cancel();
                        });
        return builder.create();
    }
}
