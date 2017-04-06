package prnvshrn.foodwatch.userInterface;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import prnvshrn.foodwatch.R;

public class MainActivity extends ActionBarActivity implements InformationFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener {

    Button InformationMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openInformationFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    public void onFragmentInteraction() {

    }

    public void openInformationFragment() {
        Fragment WelcomeFragment = new InformationFragment();
        FragmentManager fm= getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,WelcomeFragment);
        fragmentTransaction.commit();
    }

    public void openDetailFragment() {
        Fragment WelcomeFragment = new DetailFragment();
        FragmentManager fm= getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,WelcomeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        openInformationFragment();
    }
}
