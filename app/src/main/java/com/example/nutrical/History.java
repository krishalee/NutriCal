package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.nutrical.DataBaseHelper.NEWCALORIES_TABLE;
import static com.example.nutrical.Home.getDailyDateTime;

public class History extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    Button button1, button2;
    static Button resetbutton;
    ListView calList;
    static ListView dailycalList;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
    LineChart lineChart;
    TextView textView;

    static int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);

        button1 = findViewById(R.id.btn_calories);
        button2 = findViewById(R.id.btn_daily);
        resetbutton = findViewById(R.id.resetdaily);
        calList = findViewById(R.id.calList);
        dailycalList = findViewById(R.id.dailycalList);
        lineChart = findViewById(R.id.lineChart);
        textView = findViewById(R.id.textView24);

        getSupportActionBar().setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(0, 0);

        showAllCal();
        button1.setEnabled(false);
        lineChart();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calList.setVisibility(View.VISIBLE);
                dailycalList.setVisibility(View.GONE);
                resetbutton.setVisibility(View.GONE);
                lineChart.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                showAllCal();
                button2.setEnabled(true);
                button1.setEnabled(false);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(true);
                button2.setEnabled(false);
                lineChart.setVisibility(View.VISIBLE);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
                if (dataBaseHelper.sumCalories() == 0) {
                    resetbutton.setEnabled(false);
                } else
                    resetbutton.setEnabled(true);
                resetbutton.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                calList.setVisibility(View.GONE);
                dailycalList.setVisibility(View.VISIBLE);

                showAllDailyCal();
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(History.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Reset daily counter?")
                        .setContentText("The progress will reset and previous progress will be recorded")
                        .setConfirmText("Reset")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Successful")
                                        .setContentText("Progress reset")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                addDailyInfo();
                                showAllDailyCal();
                                x = 0;
                                dataBaseHelper.resetCalories();
                                resetbutton.setEnabled(false);
                                lineChart();
                            }
                        })
                        .show();
            }
        });
    }

    public void showAllCal() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
        List<newcaloriesModel> everyone = dataBaseHelper.getEveryone();

        ArrayAdapter caloriesArrayAdapter = new ArrayAdapter<newcaloriesModel>(History.this, android.R.layout.simple_list_item_1, everyone);
        calList.setAdapter(caloriesArrayAdapter);
        calList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new SweetAlertDialog(History.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("What would you like to do?")
                        .setConfirmText("Delete")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted")
                                        .setConfirmText("Dismiss")
                                        .setConfirmClickListener(null)
                                        .showCancelButton(false)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                newcaloriesModel clickedcalorie = (newcaloriesModel) parent.getItemAtPosition(position);
                                dataBaseHelper.deleteCalorie(clickedcalorie);
                                showAllCal();
                            }
                        })
                        .setCancelButton("Edit", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                openDialog();
                                newcaloriesModel clickedcalorie = (newcaloriesModel) parent.getItemAtPosition(position);
                                dataBaseHelper.deleteCalorie(clickedcalorie);
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });
    }

    public void showAllDailyCal() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
        List<newdailycaloriesModel> everyone = dataBaseHelper.getDailyCalories();

        ArrayAdapter caloriesDailyArrayAdapter = new ArrayAdapter<newdailycaloriesModel>(History.this, android.R.layout.simple_list_item_1, everyone);
        dailycalList.setAdapter(caloriesDailyArrayAdapter);
    }

    public void openDialog() {
        ExampleDialog2 exampleDialog = new ExampleDialog2();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyText(double calories, String notes) {
        Home home = new Home();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
        newcaloriesModel newcaloriesModel = new newcaloriesModel();
        dataBaseHelper.deleteCalorie(newcaloriesModel);
        newcaloriesModel = new newcaloriesModel(-1, calories, home.getDateTime() + " (edited)",notes);
        boolean success = dataBaseHelper.addOneCal(newcaloriesModel);
        Toast.makeText(History.this, "Edited " + success, Toast.LENGTH_SHORT).show();
        showAllCal();
        dataBaseHelper.close();
    }

    public void addDailyInfo() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(History.this);
        newdailycaloriesModel newdailycaloriesModel;

        newdailycaloriesModel = new newdailycaloriesModel(-1, dataBaseHelper.getBMR(),
                dataBaseHelper.sumCalories(), getDailyDateTime());

        boolean success = dataBaseHelper.addOneDailyCal(newdailycaloriesModel);
        dataBaseHelper.close();
    }

    private ArrayList<Entry> lineChartDataSet() {
        ArrayList<Entry> dataSet = new ArrayList<Entry>();
        Cursor dailyCalcount = dataBaseHelper.getDataDailyCal();

        if (dailyCalcount.getCount() == 0) {
            //ERROR MESSAGE
        }
        while (dailyCalcount.moveToNext()) {
            dataSet.add(new Entry(x++, (float) dailyCalcount.getDouble(2)));
        }
        return dataSet;
    }

    public void lineChart() {
        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(), "Daily Caloriie Chart");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        lineChart.getDescription().setEnabled(false);
        lineChart.setNoDataText("Reset your Daily Calories to add something in the chart!");
        lineChart.setNoDataTextColor(Color.BLACK);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);


        lineChart.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        lineChart.getRenderer().getPaintRender().setShadowLayer(5, 5, 5, Color.GRAY);

        if (x > 10) {
            lineDataSet.setDrawValues(false);
        } else
            lineDataSet.setDrawValues(true);

        lineChart.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineDataSet.setColor(Color.parseColor("#f85f6a"));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCircleColor(Color.parseColor("#f85f6a"));
        lineDataSet.setCircleHoleColor(Color.WHITE);
        lineDataSet.setLineWidth(4);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineChart.invalidate();
    }


}
