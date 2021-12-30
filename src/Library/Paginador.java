/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Stiven Arboleda
 */
public class Paginador<T> {
    
    private final List<T> _dataList;
    private final JLabel _label;
    private static int maxreg, _reg_por_pagina, pageCount, numPagi = 1;
    
    public Paginador(List<T> datalist, JLabel label, int reg_por_pagina){
        
        _dataList = datalist;
        _label = label;
        _reg_por_pagina = reg_por_pagina;
        cargarDatos();
    }
    
    private void cargarDatos(){
        numPagi = 1;
        maxreg = _dataList.size();
        pageCount = (maxreg / _reg_por_pagina);
        
        if((maxreg % _reg_por_pagina) > 0){
            pageCount += 1; 
        }
        _label.setText("Página 1/" + pageCount);
    }
    public int primero(){
        numPagi = 1;
        _label.setText("Página " + numPagi + "/" + pageCount);
        return numPagi;
    }
    
    public int anterior(){
        if(numPagi > 1){
            numPagi -= 1;
            _label.setText("Página " + numPagi + "/" + pageCount);
        }
        return numPagi;
    }
    
    public int next(){
        if(numPagi == pageCount){
            numPagi -= 1;
        }
        if(numPagi < pageCount){
            numPagi += 1;
             _label.setText("Página " + numPagi + "/" + pageCount);
        }
        return numPagi;
    }
    
    public int ultimo(){
        numPagi = pageCount;
        _label.setText("Página " + numPagi + "/" + pageCount);
        return numPagi;
    }
}
