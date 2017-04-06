package prnvshrn.foodwatch.userInterface;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import prnvshrn.foodwatch.R;
import prnvshrn.foodwatch.appProcesses.URLfunctions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AutoCompleteTextView InformationAutoCompleteTextView;
    Button DetailsButton;
    private ProgressDialog progressDialog;
    public static String food_item=" ";

    private OnFragmentInteractionListener mListener;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        View view = inflater.inflate(R.layout.information_fragment, container, false);
        InformationAutoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.InformationAutoCompleteTextView);
        String[] arr = { "Cheddar cheese", "Parmesan Cheese","Avocado",
                "Broccoli", "Bacon","Beef","Penne Pasta"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item, arr);

        InformationAutoCompleteTextView.setThreshold(2);
        InformationAutoCompleteTextView.setAdapter(adapter);

        DetailsButton = (Button) view.findViewById(R.id.DetailsButton);
        DetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //URLfunctions.getFoodData(InformationAutoCompleteTextView.getText().toString());
               // mListener.openDetailFragment();
                food_item = InformationAutoCompleteTextView.getText().toString();
                new LoadViewTask().execute();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.openDetailFragment();
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
        public void openDetailFragment();
    }

    private class LoadViewTask extends AsyncTask<Void, Integer, Void>
    {
        protected void onPreExecute()
        {
            //Create a new progress dialog
            progressDialog = new ProgressDialog(getContext());
            //Set the progress dialog to display a horizontal progress bar
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //Set the dialog title to 'Loading...'
            progressDialog.setTitle("Loading...");
            //Set the dialog message to 'Loading application View, please wait...'
            progressDialog.setMessage("Please Wait");
            //This dialog can't be canceled by pressing the back key
            progressDialog.setCancelable(false);
            //This dialog isn't indeterminate
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressNumberFormat("");  // or
            progressDialog.setProgressPercentFormat(null);
            //Display the progress dialog
            progressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            try
            {synchronized (this){
                URLfunctions.getFoodData(food_item);
            }
            }
            catch(Exception ex)
            {

            }
            return null;
        }

        //Update the progress
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            //set the current progress of the progress dialog
            progressDialog.setProgress(values[0]);
        }

        //after executing the code in the thread
        @Override
        protected void onPostExecute(Void result)
        {
            //close the progress dialog
            progressDialog.dismiss();
            mListener.openDetailFragment();
        }
    }
}
