package jamiesmyth.theoraclemain;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import jamiesmyth.theoraclemain.databinding.ActivityFixtureStatBinding;

public class FixtureStatActivity extends AppCompatActivity {

    public static final String TAG = FixtureStatActivity.class.getSimpleName();

    // API Strings
    String fixturesApi;
    String homeTeamApi = "";
    String awayTeamApi = "";

    RequestQueue requestQueue;

    // Head to head TextViews
    //TextView overTwoGoals, overOneGoal;

    // Head to head ProgressBars
    //ProgressBar bttsProgress, overTwoProgress, overOneProgress;

    // Home Textviews
    //TextView homeBtts, homeOverTwoGoals, homeOverOneGoal, goalsForHome, goalsAgainstHome;

    // Home ProgressBars
    //ProgressBar homeBttsProgress, homeOverTwoProgress, homeOverOneProgress;

    // Away Textviews
    //TextView awayBtts, awayOverTwoGoals, awayOverOneGoal, goalsForAway, goalsAgainstAway;

    // Away ProgressBars
    //ProgressBar awayBttsProgress, awayOverTwoProgress, awayOverOneProgress;

    // Team names
    String homeTeam, awayTeam;

    GSONMain mGSONMain;
    Calculations mCalculations;

    ActivityFixtureStatBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fixture_stat);
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            fixturesApi = extras.getString("fixtureAPI");
            String fixtureName = extras.getString("fixtureName");
            getSupportActionBar().setTitle(fixtureName);
        }

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

//        // Get h2h text views
//        h2hBtts = (TextView) findViewById(R.id.Btts_Percentage_Text);
//        overTwoGoals = (TextView) findViewById(R.id.Over_Two_Percentage_Text);
//        overOneGoal = (TextView) findViewById(R.id.Over_One_Percentage_Text);
//
//        // Get h2h progress bars
//        bttsProgress = (ProgressBar) findViewById(R.id.Btts_Percentage_Bar);
//        overTwoProgress = (ProgressBar) findViewById(R.id.Over_Two_Percentage_Bar);
//        overOneProgress = (ProgressBar) findViewById(R.id.Over_One_Percentage_Bar);
//
//        // Get home text views
//        homeBtts = (TextView) findViewById(R.id.Btts_Home_Percentage_Text);
//        homeOverTwoGoals = (TextView) findViewById(R.id.Over_Two_Home_Percentage_Text);
//        homeOverOneGoal = (TextView) findViewById(R.id.Over_One_Home_Percentage_Text);
//        goalsForHome = (TextView) findViewById(R.id.Goals_For_Home_Percentage_Text);
//        goalsAgainstHome = (TextView) findViewById(R.id.Goals_Against_Home_Percentage_Text);
//
//        // Get home progress bars
//        homeBttsProgress = (ProgressBar) findViewById(R.id.Btts_Home_Percentage_Bar);
//        homeOverTwoProgress = (ProgressBar) findViewById(R.id.Over_Two_Home_Percentage_Bar);
//        homeOverOneProgress = (ProgressBar) findViewById(R.id.Over_One_Home_Percentage_Bar);
//
//        // Get away text views
//        awayBtts = (TextView) findViewById(R.id.Btts_Away_Percentage_Text);
//        awayOverTwoGoals = (TextView) findViewById(R.id.Over_Two_Away_Percentage_Text);
//        awayOverOneGoal = (TextView) findViewById(R.id.Over_One_Away_Percentage_Text);
//        goalsForAway = (TextView) findViewById(R.id.Goals_For_Away_Percentage_Text);
//        goalsAgainstAway = (TextView) findViewById(R.id.Goals_Against_Away_Percentage_Text);
//
//        // Get away progress bars
//        awayBttsProgress = (ProgressBar) findViewById(R.id.Btts_Away_Percentage_Bar);
//        awayOverTwoProgress = (ProgressBar) findViewById(R.id.Over_Two_Away_Percentage_Bar);
//        awayOverOneProgress = (ProgressBar) findViewById(R.id.Over_One_Away_Percentage_Bar);

        getBttsPercentage(fixturesApi);
    }

    // HEAD TO HEAD
    public void getBttsPercentage(String fixturesApi){

        mCalculations = new Calculations();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                fixturesApi, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
                        //Log.d(TAG, String.valueOf(mGSONMain.getStatFixtures().getFixtures().get(0).getLinks().getHomeFixtureLink().getFixtureHomeLink()));

                        if (mGSONMain.getStatFixtures().getCount() > 0){
                            int bttsPercentage = mCalculations.calculatePercentageOfBtts(response, true);
                            setPercentages(bttsPercentage, mBinding.headToHead.BttsPercentageText, mBinding.headToHead.BttsPercentageBar);
                        }
                        else {
                            mBinding.headToHead.BttsPercentageText.setText("Sorry No Data :(");
                        }

                        // Get Home and Away Apis
                        homeTeamApi = mGSONMain.getFixture().getLinks().getHomeFixtureLink().getFixtureHomeLink() + "/fixtures?timeFrame=p35";
                        awayTeamApi = mGSONMain.getFixture().getLinks().getAwayFixtureLink().getFixtureAwayLink() + "/fixtures?timeFrame=p35";


                        getOverTwoGoals(response);
                        getBttsHomeForm(homeTeamApi);
                        getBttsAwayForm(awayTeamApi);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }

        })
        {
            @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return getHttpHeaders();
        }};

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjReq);
    }

    public void getOverTwoGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getStatFixtures().getCount() > 0){
            int OverTwoPercentage = mCalculations.calculatePercentageOfOverTwo(response, true);
            setPercentages(OverTwoPercentage, mBinding.headToHead.OverTwoText, mBinding.headToHead.OverTwoPercentageBar);
        }
        else {
            mBinding.headToHead.OverTwoText.setText("Sorry No Data :(");
        }

        getOverOneGoal(response);

    }

    public void getOverOneGoal (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getStatFixtures().getCount() > 0){
            int OverOnePercentage = mCalculations.calculatePercentageOfOverOne(response, true);
            //overOneGoal.setText(OverOnePercentage + "% in " + mGSONMain.getStatFixtures().getCount() + " games.");
            setPercentages(OverOnePercentage, mBinding.headToHead.OverOneText, mBinding.headToHead.OverOnePercentageBar);
        }
        else {
            mBinding.headToHead.OverOnePercentageText.setText("Sorry No Data :(");
        }

    }

    // HOME FORM
    public void getBttsHomeForm(String homeTeamApi) {
        mCalculations = new Calculations();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                homeTeamApi, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
                        //Log.d(TAG, String.valueOf(mGSONMain.getStatFixtures().getCount()));
                        if (mGSONMain.getCount() > 0){
                            int bttsPercentage = mCalculations.calculatePercentageOfBtts(response, false);
                            setPercentages(bttsPercentage, mBinding.homeForm.BttsHomeText, mBinding.homeForm.BttsHomePercentageBar);
                        }
                        else {
                            mBinding.homeForm.BttsHomePercentageText.setText("Sorry No Data :(");
                        }

                        getOverTwoHomeGoals(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHttpHeaders();
            }};

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjReq);
    }

    public void getOverTwoHomeGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getCount() > 0){
            int OverTwoPercentage = mCalculations.calculatePercentageOfOverTwo(response, false);
            setPercentages(OverTwoPercentage, mBinding.homeForm.OverTwoHomePercentageText, mBinding.homeForm.OverTwoHomePercentageBar);
        }
        else {
            mBinding.homeForm.OverTwoHomePercentageText.setText("Sorry No Data :(");
        }

        getOverOneHomeGoals(response);
    }

    public void getOverOneHomeGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getCount() > 0){
            int OverOnePercentage = mCalculations.calculatePercentageOfOverOne(response, false);
            setPercentages(OverOnePercentage, mBinding.homeForm.OverOneHomePercentageText, mBinding.homeForm.OverOneHomePercentageBar);

            mCalculations.calculateGriffsRule(response);
        }
        else {
            mBinding.homeForm.OverOneHomePercentageText.setText("Sorry No Data :(");
        }

        getForHomeGoals(response);
    }

    public void getForHomeGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getCount() > 0){
            // NEED TOGET TEAM NAME
            //float avgGoalsFor = mCalculations.calculateGoalsFor(response, );
            //goalsForHome.setText(avgGoalsFor + "% in " + mGSONMain.getCount() + " games.");
        }
        else {
            //goalsForHome.setText("Sorry No Data :(");
        }

    }


    // AWAY FORM
    public void getBttsAwayForm(String awayTeamApi) {
        mCalculations = new Calculations();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                awayTeamApi, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
                        //Log.d(TAG, String.valueOf(mGSONMain.getStatFixtures().getCount()));
                        if (mGSONMain.getCount() > 0){
                            int bttsPercentage = mCalculations.calculatePercentageOfBtts(response, false);
                            setPercentages(bttsPercentage, mBinding.awayForm.BttsAwayPercentageText, mBinding.awayForm.BttsAwayPercentageBar);
                        }
                        else {
                            mBinding.awayForm.BttsAwayPercentageText.setText("Sorry No Data :(");
                        }

                        getOverTwoAwayGoals(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHttpHeaders();
            }};

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjReq);
    }

    public void getOverTwoAwayGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getCount() > 0){
            int OverTwoPercentage = mCalculations.calculatePercentageOfOverTwo(response, false);
            setPercentages(OverTwoPercentage, mBinding.awayForm.OverTwoAwayPercentageText, mBinding.awayForm.OverTwoAwayPercentageBar);
        }
        else {
            mBinding.awayForm.OverTwoAwayPercentageText.setText("Sorry No Data :(");
        }

        getOverOneAwayGoals(response);

    }

    public void getOverOneAwayGoals (JSONObject response) {
        mCalculations = new Calculations();

        Gson gson = new Gson();
        mGSONMain = gson.fromJson(response.toString(), GSONMain.class);
        if (mGSONMain.getCount() > 0){
            int OverOnePercentage = mCalculations.calculatePercentageOfOverOne(response, false);
            setPercentages(OverOnePercentage, mBinding.awayForm.OverOneAwayPercentageText, mBinding.awayForm.OverOneAwayPercentageBar);
        }
        else {
            mBinding.awayForm.OverOneAwayPercentageText.setText("Sorry No Data :(");
        }

    }

    // OTHER METHODS
    public void setPercentages(int percentage, TextView text, ProgressBar progress) {

        if (percentage >= 50) {
            text.setText(percentage + "%");
            text.setTypeface(null, Typeface.BOLD);
        }
        else {
            text.setText(percentage + "%");
        }
        progress.setProgress(percentage);
    }

    public Map<String, String> getHttpHeaders() {
        // Method for the API token
        Map<String, String> params = new HashMap<>();
        params.put("X-Auth-Token", "debf9352e2b745759c3eb424fc776d6d");
        return params;

    }
}
