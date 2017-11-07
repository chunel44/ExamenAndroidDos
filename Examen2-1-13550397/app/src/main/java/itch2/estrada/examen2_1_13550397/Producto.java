package itch2.estrada.examen2_1_13550397;

/**
 * Created by root on 6/11/17.
 */

public class Producto {

    String Producto, Proveedor, Tipo, Color;
    int Cantidad;

    public Producto(String producto, String proveedor, String tipo, String color, int cantidad) {
        Producto = producto;
        Proveedor = proveedor;
        Tipo = tipo;
        Color = color;
        Cantidad = cantidad;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }


}
