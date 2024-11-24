import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArbolBinario {

    private Nodo raiz;
    private int criterio;

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }

    public ArbolBinario() {
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void agregarNodo(Nodo n) {

        if (raiz == null) {
            raiz = n;
            return;
        }

        Nodo actual = raiz;
        Nodo padre;
        while (true) {
            padre = actual;
            if (n.getDocumento().equals(actual.getDocumento())) {
                //esta permitiendo repetidos
                actual = actual.derecho;
                if (actual == null) {
                    padre.derecho = n;
                    return;
                }
            } else if (Documento.esMayor(n.getDocumento(), actual.getDocumento(), criterio)) {
                actual = actual.derecho;
                if (actual == null) {
                    padre.derecho = n;
                    return;
                }
            } else {
                actual = actual.izquierdo;
                if (actual == null) {
                    padre.izquierdo = n;
                    return;
                }
            }
        }
    }

    

    public void mostrar(JTable tbl) {
        String[][] datos = null;
        if (raiz != null) {
            datos = new String[Documento.documentos.size()][Documento.encabezados.length];

            Nodo n = raiz;
            Nodo padre;
            int fila = -1;
            while (n != null) {
                if (n.izquierdo == null) {
                    fila++;
                    datos[fila][0] = n.getDocumento().getApellido1();
                    datos[fila][1] = n.getDocumento().getApellido2();
                    datos[fila][2] = n.getDocumento().getNombre();
                    datos[fila][3] = n.getDocumento().getDocumento();
                    n = n.derecho;
                } else {
                    padre = n.izquierdo;
                    while (padre.derecho != null && padre.derecho != n) {
                        padre = padre.derecho;
                    }
                    if (padre.derecho == null) {
                        padre.derecho = n;
                        n = n.izquierdo;
                    } else {
                        padre.derecho = null;
                        fila++;
                        datos[fila][0] = n.getDocumento().getApellido1();
                        datos[fila][1] = n.getDocumento().getApellido2();
                        datos[fila][2] = n.getDocumento().getNombre();
                        datos[fila][3] = n.getDocumento().getDocumento();
                        n = n.derecho;
                    }

                }
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, Documento.encabezados);
        tbl.setModel(dtm);
    }
    ArrayList<Documento> lista = new ArrayList<>();

    public void recorrido(Nodo n, String dato){
        
        if (n != null) {
            
            
            if (n.getDocumento() != null) {
                String nombre = n.getDocumento().getNombre();
                String apellido1 = n.getDocumento().getApellido1();
                String apellido2 = n.getDocumento().getApellido2();
                String documento = n.getDocumento().getDocumento();
                if (nombre.toLowerCase().startsWith(dato.toLowerCase()) || apellido1.toLowerCase().startsWith(dato.toLowerCase()) || apellido2.toLowerCase().startsWith(dato.toLowerCase()) ||  documento.toLowerCase().startsWith(dato.toLowerCase())) {
                    lista.add(n.getDocumento());
                }
            }
    
            
            recorrido(n.derecho, dato);
            recorrido(n.izquierdo, dato);  
        }
    }
    


public void mostrar_busqueda(JTable tbl, String dato) {
    recorrido(raiz, dato);
    String[][] datos = null;
    if (lista != null) {
        datos = new String[Documento.documentos.size()][Documento.encabezados.length];

        
        
        int fila = -1;

        for (int i = 0; i < lista.size(); i++) {
           
              
                    fila++;
                    datos[fila][0] = lista.get(i).getApellido1();
                    datos[fila][1] = lista.get(i).getApellido2();
                    datos[fila][2] = lista.get(i).getNombre();
                    datos[fila][3] = lista.get(i).getDocumento();
                    
                

            
        }
    }
    DefaultTableModel dtm = new DefaultTableModel(datos, Documento.encabezados);
    tbl.setModel(dtm);
}

}