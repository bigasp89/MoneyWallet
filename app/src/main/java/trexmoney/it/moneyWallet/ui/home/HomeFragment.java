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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import trexmoney.it.moneyWallet.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private float[] yData = {80f,20f};
    private String[] xData = {"Uscite","Entrate"};
    PieChart pieChart;
    BarChart barChart;

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
        pieChart.setCenterText("-60 €");
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.animateXY(1000, 1000);
        addDataSetPie();

        barChart = root.findViewById(R.id.barchart);

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));


        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");


        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        barChart.animateY(5000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

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