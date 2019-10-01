package com.example.cuamatzi.a2015;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre,apellido;
    TextView textView;
    DB  controlador ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.Nombre);
        apellido = findViewById(R.id.Apellido);
        textView = findViewById(R.id.listatextview);

        controlador = new DB(this,"",null,1);

    }

    public void btn_click(View view) {
        switch (view.getId()){
                case R.id.agregar:
                    try {

                        controlador.insertar_estudiante(nombre.getText().toString(),apellido.getText().toString());
                        Toast.makeText(MainActivity.this,"Registro exitoso",Toast.LENGTH_LONG).show();


                    }catch (SQLiteException e){

                        Toast.makeText(MainActivity.this,"EXISTENTE",Toast.LENGTH_LONG).show();

                    }
                 break;
            case R.id.eliminar:

                controlador.eliminarestudiante(nombre.getText().toString());
                Toast.makeText(MainActivity.this,"Eliminado exitosamente",Toast.LENGTH_LONG).show();

             break;
            case R.id.actualizar:

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("INGRESA EL NUEVO NOMBRE");

                final  EditText nuevoNombre = new EditText(this);
                dialog.setView(nuevoNombre);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        controlador.actualizarestudiante(nombre.getText().toString(),nuevoNombre.getText().toString());

                    }
                });
                dialog.show();


             break;
            case R.id.mostrar:

                controlador.listarestudiante(textView);

                break;

        }
    }
}
