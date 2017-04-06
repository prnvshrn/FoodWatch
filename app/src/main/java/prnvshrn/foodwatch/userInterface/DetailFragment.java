package prnvshrn.foodwatch.userInterface;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import prnvshrn.foodwatch.R;
import prnvshrn.foodwatch.appProcesses.ProjectVariables;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

//import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView DetailFoodItem, DetailFoodQuantity, DetailCalories, DetailTotalFat, DetailCholestrol, DetailSodium, DetailCarbs,DetailFibre,DetailSugar,DetailProtein;
    private PieChart pieChart;
    private SeekBar mSeekBarX, mSeekBarY;

    private ArrayList<Entry> entries ;
    private ArrayList<String> PieEntryLabels ;
    private PieDataSet pieDataSet ;
    private PieData pieData ;

    private OnFragmentInteractionListener mListener;

    private int[] pieChartValues = new int[5];


    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        DetailFoodItem = (TextView) view.findViewById(R.id.DetailFoodItem);

        DetailFoodItem.setText(ProjectVariables.food_item);

        DetailFoodQuantity = (TextView) view.findViewById(R.id.DetailFoodQuantity);
        DetailFoodQuantity.setText(ProjectVariables.food_quantity);

        DetailCalories = (TextView) view.findViewById(R.id.DetailCalories);
        DetailCalories.setText(ProjectVariables.nf_calories);

        DetailTotalFat = (TextView) view.findViewById(R.id.DetailTotalFat);
        DetailTotalFat.setText(ProjectVariables.nf_total_fat);


        DetailCholestrol = (TextView) view.findViewById(R.id.DetailCholestrol);
        DetailCholestrol.setText(ProjectVariables.nf_cholestrol);

        DetailSodium = (TextView) view.findViewById(R.id.DetailSodium);
        DetailSodium.setText(ProjectVariables.nf_sodium);

        DetailCarbs = (TextView) view.findViewById(R.id.DetailCarbs);
        DetailCarbs.setText(ProjectVariables.nf_total_carbohydrate);

        DetailFibre = (TextView) view.findViewById(R.id.DetailFibre);
        DetailFibre.setText(ProjectVariables.nf_dietary_fibre);

        DetailSugar = (TextView) view.findViewById(R.id.DetailSugar);
        DetailSugar.setText(ProjectVariables.nf_sugars);

        DetailProtein = (TextView) view.findViewById(R.id.DetailProtein);
        DetailProtein.setText(ProjectVariables.nf_protein);

        pieChart = (PieChart) view.findViewById(R.id.chart1);
        pieChart.setRotationEnabled(true);
        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();
        AddValuesToPIEENTRY();
        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);
        return view;
    }

    public void AddValuesToPIEENTRY(){
        String retreive[]=new String[2];
        retreive = ProjectVariables.nf_total_fat.split(":");
        Float value = Float.parseFloat(retreive[1]);
        entries.add(new BarEntry(value, 0));

        retreive = ProjectVariables.nf_sodium.split(":");
        value = Float.parseFloat(retreive[1]);
        entries.add(new BarEntry(value/1000, 0));

        retreive = ProjectVariables.nf_total_carbohydrate.split(":");
        value = Float.parseFloat(retreive[1]);
        entries.add(new BarEntry(value, 2));

        retreive = ProjectVariables.nf_sugars.split(":");
        value = Float.parseFloat(retreive[1]);
        entries.add(new BarEntry(value, 3));

        retreive = ProjectVariables.nf_protein.split(":");
        value = Float.parseFloat(retreive[1]);
        entries.add(new BarEntry(value, 4));


    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Total Fat");
        PieEntryLabels.add("Sodium");
        PieEntryLabels.add("Carbs");
        PieEntryLabels.add("Sugars");
        PieEntryLabels.add("Protein");


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
    }
}
