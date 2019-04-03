package mis.tvscs.com.deepak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import mis.tvscs.com.deepak.adapter.RecyclerAdapter;
import mis.tvscs.com.deepak.model.Model;
import mis.tvscs.com.deepak.retrofit.MyCallBack;
import mis.tvscs.com.deepak.retrofit.ServiceGenerator;
import mis.tvscs.com.deepak.retrofit.WebService;
import mis.tvscs.com.deepak.utils.CommonMethods;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AppCompatActivity mActivity;
    private Model model;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        callBankService();
    }

    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void callBankService() {
        WebService webService = ServiceGenerator.createService(WebService.class);

        Call<Model> modelCall = webService.gerDatafromServer();

        modelCall.enqueue(new MyCallBack<Model>(this,
                getResources().getString(R.string.progress_please_wait), true) {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                super.onResponse(call, response);

                try {
                    Model model = response.body();
                    if (model != null) {

                        setRecyclerAdapter(model);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                super.onFailure(call, t);
                CommonMethods.alertException(MainActivity.this, t);
            }
        });
    }

    private void setRecyclerAdapter(Model model) {
        recyclerAdapter = new RecyclerAdapter(MainActivity.this, model);
        mRecyclerView.setAdapter(recyclerAdapter);
    }
}
