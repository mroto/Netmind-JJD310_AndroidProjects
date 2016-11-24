/*La aplicación a implementar incluye dos elementos Activity con layouts muy simples, aunque
diferentes.
En el primer caso la App Bar incluye la tradicional Action Bar,
mientras que en el segundo alberga la novedosa Toolbar, más flexible y robusta.

Además, en lo referente a menús contextuales,
el primer layout incluye un clásico menú contextual flotante,
mientras que el segundo implementa lo que se conoce como Contextual Action Mode, y que
convive con la Toolbar.

Por último, a una de las opciones del segundo layout se le ha agregado un pop-up menú, mostrando
así una de las clásicas alternativas que hay en Android para presentar información adicional al usuario. */

package com.mroto.practica8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //registramos el longclick (MENU CONTEXTUAL FLOTANTE):
        RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.activity_main);
        this.registerForContextMenu(layout);
    }

    //inflate menu: (ACTION BAR)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = this.getMenuInflater();
        mMenuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //menu button actions: (ACTION BAR)
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main_add)    {
            Toast.makeText(this,"Add clicked",Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId() == R.id.menu_main_dislike)    {
            Toast.makeText(this,"Dislike clicked",Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId() == R.id.menu_main_like)    {
            Toast.makeText(this,"Like clicked",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    //MENU CONTEXTUAL FLOTANTE:
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mContMenuInflater = this.getMenuInflater();
        mContMenuInflater.inflate(R.menu.menu_main,menu);
    }

    //MENU CONTEXTUAL FLOTANTE actions:
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main_add)    {
            Toast.makeText(this,"Add clicked (context)",Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId() == R.id.menu_main_dislike)    {
            Toast.makeText(this,"Dislike clicked (context)",Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId() == R.id.menu_main_like)    {
            Toast.makeText(this,"Like clicked (context)",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }



}
