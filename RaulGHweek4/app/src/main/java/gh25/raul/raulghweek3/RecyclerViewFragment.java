package gh25.raul.raulghweek3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gh25.raul.raulghweek3.pojo.Mascota;

/**
 * Created by Raul on 13/05/2016.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Mascota> datasetMascotas;

    boolean dataBasedExistVariable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        // TODO BORRAR ESTO
            // Load datasetMascotas
        // TODO BORRAR ESTO


/*

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView_List);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new RVAdapter(datasetMascotas, getActivity());
        mRecyclerView.setAdapter(mAdapter);
*/

        return v;
    }

}
