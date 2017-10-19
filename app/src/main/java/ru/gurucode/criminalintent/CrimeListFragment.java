package ru.gurucode.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

/**
 * Created by Leon on 18.10.2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list,
                container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);

        //Без LayoutManager RecyclerView не работает. LayoutManager управляет
        // позиционированием и определяет поведение прокуртки.
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        //ссылка на синглтон CrimeLab с данными
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        //передадим данные адаптеру.
        mAdapter = new CrimeAdapter(crimes);
        //Подключим адаптер к mCrimeRecyclerView.
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    //ВХ - view для элемента
    private class CrimeHolder extends RecyclerView.ViewHolder {
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
        }
    }

    //Адаптер, содержащий VH CrimeHolder
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        /*Вызывается mCrimeRecyclerView, когда нужно новое View*/
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //layoutInflater - используется для создания View
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            //Crime crime = mCrimes.get(position);
            //holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
