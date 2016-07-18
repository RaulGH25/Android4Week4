package gh25.raul.raulghweek3;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;


import java.util.ArrayList;

import gh25.raul.raulghweek3.adapter.PageAdapter;
import gh25.raul.raulghweek3.restApi.EndpointsApi;
import gh25.raul.raulghweek3.restApi.adapter.RestApiAdapter;
import gh25.raul.raulghweek3.restApi.model.ServerUsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Email address which will receive the message
    private String emailReceiverDirection = "raulgh25d@gmail.com";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }


        // Set up the ViewPager
        setUpViewPager();





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        return true;
    }


    // ActionBar Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_star:
                Intent favoritosIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(favoritosIntent);
                return true;
            case R.id.button_contacto:
                Intent enviarcomentarioIntent = new Intent(getApplicationContext(),EnviarComentario.class);
                enviarcomentarioIntent.putExtra("emailReceiverDirection", emailReceiverDirection);
                startActivity(enviarcomentarioIntent);
                return true;
            case R.id.button_acercade:
                Intent acercadeIntent = new Intent(getApplicationContext(),BioDesarrollador.class);
                startActivity(acercadeIntent);
                return true;
            case R.id.button_guardarcuenta:
                Intent guardarcuentaIntent = new Intent(getApplicationContext(),GuardarCuenta.class);
                startActivity(guardarcuentaIntent);
                return true;
            case R.id.button_recibirnotificaciones:
                Toast.makeText(getApplicationContext(), " El usuario se registra en configurar cuenta ", Toast.LENGTH_LONG).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }



    // SetUp viewPager method

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_petface);
    }



}


