package itch2.estrada.examen2_2_13550397;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Producto> lista=new ArrayList<>();
    ArrayList<String> prod;
    String uri;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.lista);
       // ObtenerDatos();
      //  actualizarLista();
    }



    @Override
    protected void onStart() {
        super.onStart();
        ObtenerDatos();
        actualizarLista();
    }

    @Override
    protected void onStop() {
        super.onStop();
        prod.clear();
        lista.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        prod.clear();
        lista.clear();
    }



    public JSONObject cargarArchivo() throws JSONException {
        StringBuilder text = new StringBuilder();
        try {
            File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(sdcard,"producto.json");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                //Log.i("Test", "text : "+text+" : end");
                text.append('\n');
            } }
        catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(text.toString());
        return obj;
    }

    public void ObtenerDatos(){
        try {
            JSONObject obj = cargarArchivo();
            JSONArray productos = obj.getJSONArray("Productos");
            for (int i = 0; i < productos.length(); i++) {
               JSONObject p = productos.getJSONObject(i);
               JSONObject prod = p.getJSONObject("Productos_lista");
               String Producto = prod.getString("Producto");
               String Proveedor = prod.getString("Proveedor");
               int Cantidad = prod.getInt("Cantidad");
               String Tipo = prod.getString("Tipo");
               String Color = prod.getString("Color");
                lista.add(new Producto(Producto,Proveedor,Tipo,Color,Cantidad));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void actualizarLista(){
        prod=new ArrayList<>();
        int a = 1;
        for (Producto c:lista){
            prod.add("Producto "+ a);
            prod.add("Nombre: "+c.Producto);
            prod.add("Proveedor: "+c.Proveedor);
            prod.add("Tipo: "+c.Tipo);
            prod.add("Color: "+c.Color);
            prod.add("Cantidad: "+String.valueOf(c.Cantidad));
            a++;
        }
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,prod);
        lv.setAdapter(adapter);
    }




}
