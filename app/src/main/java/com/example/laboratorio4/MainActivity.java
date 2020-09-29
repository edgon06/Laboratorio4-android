package com.example.laboratorio4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String provincias[],paises[], tipoEntrega;

    EditText txtNombre, txtApellido, txtDireccion1, txtDireccion2, txtCiudad, txtZip;
    TextView  txtSubtotalValor, txtEnvioValor, txtTotalValor;
    RadioGroup rgMetodoEntrega;
    RadioButton rdbEntregaNormal, rdbEntregaExpress, rdbEntregaPremium;
    Spinner comboProvincia, comboPais;
    Button btnProcesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Referencias a las vistas

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDireccion1 = findViewById(R.id.txtDireccion1);
        txtDireccion2 = findViewById(R.id.txtDireccion2);

        txtCiudad = findViewById(R.id.txtCiudad);
        txtZip = findViewById(R.id.txtZip);

        // Inicializar ComboBoxes
        provincias = getResources().getStringArray(R.array.provincias);
        ArrayAdapter<String> adaptadorProvincias = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, provincias);

        paises = getResources().getStringArray(R.array.paises);
        ArrayAdapter<String> adaptadorPais = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, paises);

        comboProvincia = findViewById(R.id.comboProvincia);
        comboPais = findViewById(R.id.comboPais);

        comboProvincia.setAdapter(adaptadorProvincias);
        comboPais.setAdapter(adaptadorPais);


        rdbEntregaNormal = findViewById(R.id.rdbEntregaNormal);
        rdbEntregaExpress = findViewById(R.id.rdbEntregaExpress);
        rdbEntregaPremium = findViewById(R.id.rdbEntregaPremium);

        txtSubtotalValor = findViewById(R.id.txtSubtotalValor);
        txtEnvioValor  = findViewById(R.id.txtEnvioValor);
        txtTotalValor = findViewById(R.id.txtTotalValor);

        btnProcesar  = findViewById(R.id.btnProcesar);

        asignarEventoEntrega(rdbEntregaNormal);
        asignarEventoEntrega(rdbEntregaExpress);
        asignarEventoEntrega(rdbEntregaPremium);
        asignarEvento(btnProcesar);
    }

    public void asignarEvento(final Button vista){
        vista.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                try{
                    mostrarDialogo();
                }catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void asignarEventoEntrega(final RadioButton vista){
        vista.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                try{

                    tipoEntrega = vista.getText().toString();
                    mostrarTotales(tipoEntrega);

                }catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void mostrarTotales(String tipoEntrega){

        String entregaNormal = getText(R.string.entregaNormal).toString(), entregaExpress=getText(R.string.entregaExpress).toString(), entregaPremium=getText(R.string.entregaPremium).toString();
        if(tipoEntrega.equals(entregaNormal))
        {
            txtSubtotalValor.setText(getText(R.string.precioNormal).toString());
            txtEnvioValor.setText(getText(R.string.precioNormal).toString());
            txtTotalValor.setText(getText(R.string.precioNormal).toString());

        }else if (tipoEntrega.equals(entregaExpress)){
            txtSubtotalValor.setText(getText(R.string.precioExpress).toString());
            txtEnvioValor.setText(getText(R.string.precioExpress).toString());
            txtTotalValor.setText(getText(R.string.precioExpress).toString());

        }else if (tipoEntrega.equals(entregaPremium)){
            txtSubtotalValor.setText(getText(R.string.precioPremium).toString());
            txtEnvioValor.setText(getText(R.string.precioPremium).toString());
            txtTotalValor.setText(getText(R.string.precioPremium).toString());
        }else {
            Toast.makeText(MainActivity.this, getText(R.string.mensajeRadioButtomsIncorrecto).toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void mostrarDialogo(){

        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(R.string.tituloDialogo);
        dialogo.setCancelable(true);
        // Impresión de los datos del usuario.
        dialogo.setMessage(
                getText(R.string.nombre).toString() + ": " +txtNombre.getText().toString() + "\n" +
                getText(R.string.apellido).toString() + ": " +txtApellido.getText().toString()+ "\n" +
                getText(R.string.direccion1).toString() + ": " +txtDireccion1.getText().toString()+ "\n" +
                getText(R.string.direccion2).toString() + ": " +txtDireccion2.getText().toString()+ "\n" +
                getText(R.string.ciudad).toString() + ": " +txtCiudad.getText().toString()+ "\n" +
                getText(R.string.zip).toString() + ": " +txtZip.getText().toString()+ "\n" +
                getText(R.string.pais).toString() + ": " +comboPais.getSelectedItem().toString()+ "\n" +
                getText(R.string.provincia).toString() + ": " +comboProvincia.getSelectedItem().toString()+ "\n" +
                getText(R.string.tipoEntrega).toString() + ": " +tipoEntrega

        );

        dialogo.setPositiveButton(getText(R.string.btnOk).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, getText(R.string.mensajeProcesar).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialogo.setNegativeButton(getText(R.string.btnNo).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, getText(R.string.mensajeCancelar).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialogo.show();
    }
}
