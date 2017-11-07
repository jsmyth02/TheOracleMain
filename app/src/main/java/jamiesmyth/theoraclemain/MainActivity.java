package jamiesmyth.theoraclemain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ExpandableRelativeLayout selectLeagues, selectWeek, selectFixture, expandableLayout4, expandableLayout5;
    private ListView leaguesListView;
    private ListView matchdayListView;
    private ListView fixtureListView;

    int numberOfWeeksLeague;

    String fixtureApi = "";

    List<String> leagueNamesList = new ArrayList<String>();
    List<String> matchdayList = new ArrayList<String>();
    List<String> matchdayHomeTeams = new ArrayList<String>();
    List<String> matchdayAwayTeams = new ArrayList<String>();
    List<String> fixtureList = new ArrayList<String>();

    RequestQueue requestQueue;

    Button selectLeagueButton;
    Button matchdayButton;
    Button fixtureButton;

    JSONObject responseData;

    // Instansiated Classes
    CreateAPIAddress createAPI = new CreateAPIAddress();
    GSONMain gsonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaguesListView = (ListView) findViewById(R.id.leagueNames);
        matchdayListView = (ListView) findViewById(R.id.numberOfMatchdaysList);
        fixtureListView = (ListView) findViewById(R.id.matchdayFixturesList);

        selectLeagueButton = (Button) findViewById(R.id.leagueSelectButton);
        matchdayButton = (Button) findViewById(R.id.matchdayButton);
        fixtureButton = (Button) findViewById(R.id.fixtureButton);

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        setupLeaguesList();

    }

    public void expandableButton1(View view) {
        selectLeagues = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        selectLeagues.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        selectWeek = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        selectWeek.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        selectFixture = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        selectFixture.toggle(); // toggle expand and collapse
    }

    public void setupLeaguesList()
    {
        leagueNamesList.add("Premiership");
        leagueNamesList.add("Championship");
        leagueNamesList.add("League One");
        leagueNamesList.add("League Two");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                leagueNamesList );

        leaguesListView.setAdapter(arrayAdapter);

        leaguesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedLeague =(String) (leaguesListView.getItemAtPosition(myItemInt));
                selectLeagueButton.setText(selectedLeague);
                numberOfMatchdays(selectedLeague);
                matchdayButton.setEnabled(true);
                setupMatchdays(selectedLeague);
                selectLeagues.toggle();
            }
        });

    }

    public void numberOfMatchdays(String league)
    {
        switch (league) {
            case "Premiership":
                numberOfWeeksLeague = 38;
                break;
            case "Championship":
                numberOfWeeksLeague = 46;
                break;
            case "League One":
                numberOfWeeksLeague = 46;
                break;
            case "League Two":
                numberOfWeeksLeague = 46;
                break;
        }


    }

    public void setupMatchdays(final String competition)
    {
        ArrayAdapter<String> arrayAdapterMatchday = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                matchdayList );

        arrayAdapterMatchday.clear();

        for (int i = 0; i < numberOfWeeksLeague; i++)
        {
            matchdayList.add(Integer.toString(i + 1));
        }

        matchdayListView.setAdapter(arrayAdapterMatchday);

        matchdayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedMatchday =(String) (matchdayListView.getItemAtPosition(myItemInt));
                matchdayButton.setText(selectedMatchday);
                String fixtureListApiCall = createAPI.getFixtureListAPI(selectedMatchday, competition);
                getFixtures(fixtureListApiCall);
                selectWeek.toggle();
            }
        });
    }

    public void getFixtures(String fixturesListApiUrl) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                fixturesListApiUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        responseData = response;
                        fixtureButton.setEnabled(true);
                        createWeeksFixtures(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjReq);
    }

    public void createWeeksFixtures (JSONObject response) {

        matchdayHomeTeams.clear();
        matchdayAwayTeams.clear();

        Gson gson = new Gson();
        gsonMain = gson.fromJson(response.toString(), GSONMain.class);
        for (int i = 0; i < gsonMain.getFixtures().size(); i++) {
            matchdayHomeTeams.add(gsonMain.getFixtures().get(i).getHomeTeamName());
            matchdayAwayTeams.add(gsonMain.getFixtures().get(i).getAwayTeamName());
        }

        setupFixtures();
    }

    public void setupFixtures() {

        ArrayAdapter<String> fixtureArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                fixtureList );

        fixtureArrayAdapter.clear();

        setupFixtureList();

        fixtureListView.setAdapter(fixtureArrayAdapter);

        fixtureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFixture =(String) (fixtureListView.getItemAtPosition(myItemInt));
                //int selectedFixturePosition = myItemInt;
                getFixureApi(myItemInt);
                fixtureButton.setText(selectedFixture);
                selectFixture.toggle();
                // CHANGE HERE
                Intent myIntent = new Intent(MainActivity.this, FixtureStatActivity.class);
                myIntent.putExtra("fixtureAPI", fixtureApi);
                myIntent.putExtra("fixtureName", selectedFixture);
                startActivity(myIntent);
            }
        });
    }

    public void getFixureApi (int testingPos) {

        Gson gson = new Gson();
        gsonMain = gson.fromJson(responseData.toString(), GSONMain.class);
        fixtureApi = gsonMain.getFixtures().get(testingPos).getLinks().getFixtureLink().getFixtureSelfLink();
    }

    public void setupFixtureList() {

        fixtureList.clear();

        for (int i = 0; i < matchdayHomeTeams.size(); i++) {
            fixtureList.add(matchdayHomeTeams.get(i) + " v " + matchdayAwayTeams.get(i));
            Log.d(TAG, fixtureList.get(i));
        }
    }
}
