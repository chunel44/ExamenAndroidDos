package itch2.estrada.examen2_1_13550397;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText producto, cantidad, proveedor, tipo, color;
    String Producto, Cantidad, Proveedor, Tipo, Color;
    Button guardar;
    Producto p;
    static ArrayList<Producto> lista=new ArrayList<>();
    JSONArray jarray;
    JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        producto = (EditText) findViewById(R.id.editText);
        proveedor = (EditText) findViewById(R.id.editText2);
        cantidad = (EditText) findViewById(R.id.editText4);
        tipo = (EditText) findViewById(R.id.editText5);
        color = (EditText) findViewById(R.id.editText6);
        guardar = (Button) findViewById(R.id.button);



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               lista.add(new Producto(producto.getText().toString(),proveedor.getText().toString(),tipo.getText().toString(), color.getText().toString(), Integer.parseInt(cantidad.getText().toString())));
                try {
                    obj = ArraytoJson(lista);
                    Log.i("prueba",obj.toString());
                    guardarArchivo();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public JSONObject ArraytoJson( ArrayList<Producto> productos)
            throws JSONException {

        JSONObject jResult = new JSONObject();

        JSONArray jArray = new JSONArray();

        for (int i = 0; i < productos.size(); i++) {
            JSONObject jGroup = new JSONObject();
            jGroup.put("Producto", productos.get(i).getProducto());
            jGroup.put("Proveedor", productos.get(i).getProveedor());
            jGroup.put("Cantidad", productos.get(i).getCantidad());
            jGroup.put("Tipo", productos.get(i).getTipo());
            jGroup.put("Color", productos.get(i).getColor());

            JSONObject jOuter = new JSONObject();
            jOuter.put("Productos_lista", jGroup);

            jArray.put(jOuter);
        }

        jResult.put("Productos", jArray);
        return jResult;
    }

    private void guardarArchivo(){

        if (isExternalStorageWritable()) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "producto.json");
            try {
                FileOutputStream f = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(f);
                pw.println(obj.toString());
                pw.flush();
                pw.close();
                f.close();
                Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Log.e("TAG", "Error " + e.getMessage());
            } catch (IOException e) {
                Log.e("TAG", "Error " + e.getMessage());
            }
        }
    }



    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
