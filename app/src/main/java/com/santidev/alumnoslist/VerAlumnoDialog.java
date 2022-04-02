package com.santidev.alumnoslist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerAlumnoDialog extends DialogFragment {

    private Alumno alumno;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.detalle_alumno, null);

        TextView txtNombre = (TextView) dialog.findViewById(R.id.txt_nombre);
        TextView txtCarrera = (TextView) dialog.findViewById(R.id.txt_carrera);
        TextView txtMatricula = (TextView) dialog.findViewById(R.id.txt_matricula);
        FloatingActionButton fab = (FloatingActionButton) dialog.findViewById(R.id.btn_cerrar);

        txtNombre.setText(this.alumno.getNombre());
        txtCarrera.setText(this.alumno.getCarrera());
        txtMatricula.setText(this.alumno.getMatricula());

        builder.setView(dialog).setMessage("Alumno: ");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return builder.create();
    }

    public void setAlumno(Alumno alumnoSelecionado){
        this.alumno = alumnoSelecionado;
    }
}
