package org.mossin.com.appbook.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mossin on 6/12/14.
 */
public class NavigationAdapter extends BaseAdapter {
   private Activity activity;
   ArrayList<ItmObject> arrayItems;

    public NavigationAdapter(Activity activity,ArrayList<ItmObject> arraylista){
        super();
        this.activity = activity;
        this.arrayItems = arraylista;

    }


    //retorna el tamaño total del ItemObj
    @Override
    public int getCount() {
        return arrayItems.size();
    }
//Retorna objeto ItemObject del arra List
    @Override
    public Object getItem(int position) {
        return arrayItems.get(position);
    }
//Rtorna la posicion
    @Override
    public long getItemId(int position) {
        return position;
    }
    //Clase que represeta a la fila
    public static  class Fila{
        TextView titulo_item;
        ImageView icono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Fila view;
        LayoutInflater inflator = activity.getLayoutInflater();
        if(convertView == null) {
            view = new Fila();
            //Creo objeto Item y lo objeto con array
            ItmObject itm = arrayItems.get(position);
            convertView = inflator.inflate(R.layout.itm, null);
            //Titulo
            view.titulo_item = (TextView) convertView.findViewById(R.id.title_item);
            //seteo el campo titulo el nombre correspondiente del object
            view.titulo_item.setText(itm.getTitulo());
            //Icono
            view.icono = (ImageView) convertView.findViewById(R.id.icon);
            //Seteo el icno
            view.icono.setImageResource(itm.getIcono());
            convertView.setTag(view);
        }else{
            view = (Fila)convertView.getTag();

        }
        return convertView;
    }
}
