package com.santidev.alumnoslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editNombre, editCarrera, editMatricula;
    Button btnInsertar, btnActualizar;
    ListView listView;
    DataManager dm;

    AlumnoAdapterList adapterList;
    List<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DataManager(this);

        editNombre = (EditText) findViewById(R.id.edit_nombre);
        editCarrera = (EditText) findViewById(R.id.edit_carrera);
        editMatricula = (EditText) findViewById(R.id.edit_matricula);
        listView = (ListView) findViewById(R.id.list_view);

        btnInsertar = (Button) findViewById(R.id.btn_agregar);
        btnActualizar = (Button) findViewById(R.id.btn_actualizar);
        btnActualizar.setVisibility(View.GONE);

        adapterList = new AlumnoAdapterList(dm.createListAlumnos(), getApplicationContext());
        listView.setAdapter(adapterList);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editNombre.getText().toString();
                String carrera = editCarrera.getText().toString();
                String matricula = editMatricula.getText().toString();

                if(!nombre.equals("") && !carrera.equals("") && !matricula.equals("")) {
                    Alumno alumno = new Alumno(nombre, carrera, matricula);
                    dm.insert(alumno);
                    AlumnoAdapterList adapterList = new AlumnoAdapterList(dm.createListAlumnos(), getApplicationContext());
                    listView.setAdapter(adapterList);
                    Toast.makeText(getApplicationContext(), "Se ha insertado a " + alumno.getNombre(), Toast.LENGTH_SHORT).show();

                    editNombre.setText("");
                    editCarrera.setText("");
                    editMatricula.setText("");
                }else {
                    Toast.makeText(getApplicationContext(), "Ingresa todos los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alumnos = dm.createListAlumnos();
                Alumno current = alumnos.get(i);
                VerAlumnoDialog verAlumnoDialog = new VerAlumnoDialog();
                verAlumnoDialog.setAlumno(current);
                verAlumnoDialog.show(getFragmentManager(), "Ver Alumno");
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        alumnos = dm.createListAlumnos();
        adapterList = new AlumnoAdapterList(alumnos, getApplicationContext());
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(adapterList.getItem(info.position).getNombre());
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        alumnos = dm.createListAlumnos();
        Alumno actual = alumnos.get(info.position);

        if(item.getItemId() == R.id.item_eliminar){
            adapterList.eliminarAlumno(actual.getId());
            listView.setAdapter(adapterList);
            adapterList = new AlumnoAdapterList(dm.createListAlumnos(),getApplicationContext());
            listView.setAdapter(adapterList);
            adapterList.notifyDataSetChanged();
        }else  if(item.getItemId() == R.id.item_editar){
            editNombre.setText(actual.getNombre());
            editCarrera.setText(actual.getCarrera());
            editMatricula.setText(actual.getMatricula());

            btnActualizar.setVisibility(View.VISIBLE);
            btnInsertar.setVisibility(View.GONE);

            btnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Alumno modificado = actual;
                    modificado.setNombre(editNombre.getText().toString());
                    modificado.setCarrera(editCarrera.getText().toString());
                    modificado.setMatricula(editMatricula.getText().toString());

                    dm.update(modificado);
                    adapterList.notifyDataSetChanged();
                    adapterList = new AlumnoAdapterList(dm.createListAlumnos(),getApplicationContext());
                    listView.setAdapter(adapterList);
                    btnInsertar.setVisibility(View.VISIBLE);
                    btnActualizar.setVisibility(View.GONE);

                    editNombre.setText("");
                    editCarrera.setText("");
                    editMatricula.setText("");


                }
            });
        }

        return super.onContextItemSelected(item);
    }
}