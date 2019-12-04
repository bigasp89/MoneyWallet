package trexmoney.it.moneyWallet.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import trexmoney.it.moneyWallet.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private float[] yData = {80f,20f};
    private String[] xData = {"Uscite","Entrate"};
    PieChart pieChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.tv_mese_corrente);
        homeViewModel.getMeseCorrente().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        pieChart= root.findViewById(R.id.PieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(70f);
        pieChart.setCenterTextSize(16);
        pieChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart.setCenterText("-600 €");
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        addDataSetPie();
        return root;
    }
    private void addDataSetPie(){
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();
        for(int i = 0; i < yData.length; i++){
            yEntry.add(new PieEntry(yData[i], i));
        }
        for(int i = 0; i < xData.length; i++){
            xEntry.add(xData[i]);
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntry,"Bilancio");
        pieDataSet.setSliceSpace(2);
        //non mostrare le percentuali nelle fette
        pieDataSet.setDrawValues(false);
        //add color to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        pieDataSet.setColors(colors);
        //create pie data obj
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.getDescription().setEnabled(false);
    }
}