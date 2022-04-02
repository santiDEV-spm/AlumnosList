package com.santidev.alumnoslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AlumnoAdapterList extends BaseAdapter {

    List<Alumno> alumnos;
    Context context;

    public AlumnoAdapterList(List<Alumno> alumnos, Context context){
        this.alumnos = alumnos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alumnos.size();
    }

    @Override
    public Alumno getItem(int i) {
        return alumnos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.list_item, null);

        Alumno alumnoActual = this.alumnos.get(i);

        TextView txt_nombre = (TextView) view.findViewById(R.id.txt_nombre);
        TextView txt_carrera = (TextView) view.findViewById(R.id.txt_carrera);
        TextView txt_matricula = (TextView) view.findViewById(R.id.txt_matricula);

        txt_nombre.setText(alumnoActual.getNombre());
        txt_carrera.setText(alumnoActual.getCarrera());
        txt_matricula.setText(alumnoActual.getMatricula());

        return view;
    }

    public void eliminarAlumno(int id){
        DataManager.delete(id);
    }
}
