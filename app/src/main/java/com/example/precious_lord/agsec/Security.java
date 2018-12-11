package com.example.precious_lord.agsec;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

public class Security extends AppCompatActivity {

    Spinner stateSpinner;
    ArrayAdapter<CharSequence> adapter;

    Button verifyLocation;
    Button advanced;
    Button mkVisibleVerifyBtn;

    LinearLayout verificationlyt;
    LinearLayout percentlyt;
    LinearLayout advancedlyt;

    EditText longitude, latitude;
    Button advancedcheck;

    String stateSelected = null;
    TextView anotherState;
    TextView predictionextra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.title_security);

        // For the zoomable image
        PhotoView photoView;
        photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.imgherdsmenattack);
        // For the zoomable image

        // Spinner adapterview implementation
        stateSpinner = findViewById(R.id.stateSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.state_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Select State")){

                }else {
                    Snackbar.make(view, parent.getItemAtPosition(position) + " selected", Snackbar.LENGTH_LONG).show();
                    stateSelected = parent.getItemAtPosition(position).toString().trim();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Spinner adapterview implementation


        // Initialize views
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        advancedcheck = findViewById(R.id.advancecheck);

        verifyLocation = findViewById(R.id.verifyLocation);
        advanced = findViewById(R.id.advanced);
        mkVisibleVerifyBtn = findViewById(R.id.mkVisibleVerifyBtn);

        verificationlyt = findViewById(R.id.verificationlyt);
        advancedlyt = findViewById(R.id.advancedlyt);
        anotherState = findViewById(R.id.anotherState);
        percentlyt = findViewById(R.id.percentlyt);
        predictionextra = findViewById(R.id.predictionextra);
        // Initialize views


        mkVisibleVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificationlyt.setVisibility(View.VISIBLE);
                mkVisibleVerifyBtn.setVisibility(View.GONE);
                percentlyt.setVisibility(View.GONE);
                advancedlyt.setVisibility(View.GONE);
            }
        });

        verifyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                percentlyt.setVisibility(View.VISIBLE);
                predictState();

//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Security.this);
//                View mView = getLayoutInflater().inflate(R.layout.popup_message, null);
//
//                TextView percentage = findViewById(R.id.percentage);
//                TextView descPredtn = findViewById(R.id.descPredtn);
//
//                mBuilder.setView(mView);
//                AlertDialog dialog = mBuilder.create();
//                dialog.show();

            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percentlyt.setVisibility(View.GONE);
                advancedlyt.setVisibility(View.VISIBLE);
                verificationlyt.setVisibility(View.GONE);
            }
        });

        advancedcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undefinedState();
                percentlyt.setVisibility(View.VISIBLE);
            }
        });
    }

    public void predictState(){
        double abia = 5.532003041;double abiaY = 7.486002487;double adamawa = 10.2703408;double adamawaY = 13.2700321;
        double akwaibom = 5.007996056;double akwaibomY = 7.849998524;double anambra = 6.210433572;double anambraY = 7.06999711;
        double bauchi = 11.68040977;double bauchiY = 10.19001339;double bayelsa = 85.3;double bayelsaY = 183.5;
        double benue = 7.190399596;double benueY = 8.129984089;double borno = 10.62042279;double bornoY = 12.18999467;
        double crossriver = 4.960406513;double crossriverY = 8.330023558;double delta = 5.890427265;double deltaY = 5.680004434;
        double ebonyi = 125.4;double ebonyiY = 154.5;double edo = 6.340477314;double edoY = 5.620008096;
        double enugu = 6.867034321;double enuguY = 7.383362995;double ekiti = 7.630372741;double ekitiY = 5.219980834;
        double gombe = 10.29044293;double gombeY = 11.16995357;double imo = 5.492997053;double imoY = 7.026003588;
        double jigawa = 11.7991891;double jigawaY = 9.350334607;double kaduna = 11.0799813;double kadunaY = 7.710009724;
        double kano = 11.99997683;double kanoY = 8.5200378;double katsina = 11.5203937;double katsinaY = 7.320007689;
        double kebbi = 12.45041445;double kebbiY = 4.199939737;double kogi = 7.800388203;double kogiY = 6.739939737;
        double kwara = 8.490010192;double kwaraY = 4.549995889;double lagos = 6.443261653;double lagosY = 3.391531071;
        double nasarawa = 8.490423603;double nasarawaY = 8.5200378;double niger = 10.4003587;double nigerY = 5.46993973;
        double ogun = 7.160427265;double ogunY = 3.350017455;double ondo = 7.250395934;double ondoY = 5.199982054;
        double osun = 7.629959329;double osunY = 4.179992634;double oyo = 7.970016092;double oyoY = 3.590002806;
        double plateau = 9.929973978;double plateauY = 8.890041055;double river = 4.810002257;double riverY = 7.010000772;
        double sokoto = 13.06001548;double sokotoY = 5.240031289;double taraba = 7.870409769;double tarabaY = 9.780012572;
        double yobe = 11.74899608;double yobeY = 11.96600457;double zamfara = 12.1704057;double zamfaraY = 6.659996296;
        double abuja = 9.083333149;double abujaY = 7.533328002;

        int percent=0;
        int percent0;int percent1;int percent2;int percent3;

        if (stateSelected.equals("Abia")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((abiaY-abia), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((abiaY-abia), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((abiaY-abia), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((abiaY-abia), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - abia)/abia) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - abia)/abia) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - abia)/abia) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - abia)/abia) * 100);
            }

            anotherState.setText(String.valueOf(percent));
//            percent = (int) Math.abs(((abia - delta)/abia) * 100);

        } else if (stateSelected.equals("Anambra")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((anambraY-anambra), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((anambraY-anambra), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((anambraY-anambra), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((anambraY-anambra), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - anambra)/anambra) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - anambra)/anambra) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - anambra)/anambra) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - anambra)/anambra) * 100);
            }
            anotherState.setText(String.valueOf(percent));


        }else if (stateSelected.equals("Rivers")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((riverY-river), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((riverY-river), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((riverY-river), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((riverY-river), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - river)/river) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - river)/river) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - river)/river) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - river)/river) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Lagos")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((lagosY-lagos), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((lagosY-lagos), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((lagosY-lagos), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((lagosY-lagos), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - lagos)/lagos) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - lagos)/lagos) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - lagos)/lagos) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - lagos)/lagos) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Imo")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((imoY-imo), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((imoY-imo), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((imoY-imo), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((imoY-imo), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - imo)/imo) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - imo)/imo) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - imo)/imo) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - imo)/imo) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Bayelsa")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((bayelsaY-bayelsa), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((bayelsaY-bayelsa), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((bayelsaY-bayelsa), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((bayelsaY-bayelsa), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - bayelsa)/bayelsa) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - bayelsa)/bayelsa) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - bayelsa)/bayelsa) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - bayelsa)/bayelsa) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Akwa Ibom")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((akwaibomY-akwaibom), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((akwaibomY-akwaibom), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((akwaibomY-akwaibom), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((akwaibomY-akwaibom), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - akwaibom)/akwaibom) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - akwaibom)/akwaibom) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - akwaibom)/akwaibom) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - akwaibom)/akwaibom) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Delta")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((deltaY-delta), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((deltaY-delta), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((deltaY-delta), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((deltaY-delta), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - delta)/delta) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - delta)/delta) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - delta)/delta) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - delta)/delta) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Benue")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((benueY-benue), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((benueY-benue), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((benueY-benue), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((benueY-benue), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - benue)/benue) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - benue)/benue) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - benue)/benue) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - benue)/benue) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Taraba")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((tarabaY-taraba), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((tarabaY-taraba), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((tarabaY-taraba), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((tarabaY-taraba), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - taraba)/taraba) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - taraba)/taraba) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - taraba)/taraba) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - taraba)/taraba) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Adamawa")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((adamawaY-adamawa), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((adamawaY-adamawa), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((adamawaY-adamawa), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((adamawaY-adamawa), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - adamawa)/adamawa) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - adamawa)/adamawa) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - adamawa)/adamawa) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - adamawa)/adamawa) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Nasarawa")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((nasarawaY-nasarawa), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((nasarawaY-nasarawa), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((nasarawaY-nasarawa), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((nasarawaY-nasarawa), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - nasarawa)/nasarawa) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - nasarawa)/nasarawa) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - nasarawa)/nasarawa) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - nasarawa)/nasarawa) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Kogi")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((kogiY-kogi), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((kogiY-kogi), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((kogiY-kogi), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((kogiY-kogi), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - kogi)/kogi) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - kogi)/kogi) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - kogi)/kogi) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - kogi)/kogi) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("CrossRiver")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((crossriverY-crossriver), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((crossriverY-crossriver), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((crossriverY-crossriver), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((crossriverY-crossriver), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - crossriver)/crossriver) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - crossriver)/crossriver) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - crossriver)/crossriver) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - crossriver)/crossriver) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Ebonyi")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((ebonyiY-ebonyi), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((ebonyiY-ebonyi), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((ebonyiY-ebonyi), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((ebonyiY-ebonyi), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - ebonyi)/ebonyi) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - ebonyi)/ebonyi) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - ebonyi)/ebonyi) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - ebonyi)/ebonyi) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Enugu")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((enuguY-enugu), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((enuguY-enugu), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((enuguY-enugu), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((enuguY-enugu), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - enugu)/enugu) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - enugu)/enugu) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - enugu)/enugu) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - enugu)/enugu) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Kaduna")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((kadunaY-kaduna), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((kadunaY-kaduna), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((kadunaY-kaduna), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((kadunaY-kaduna), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - kaduna)/kaduna) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - kaduna)/kaduna) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - kaduna)/kaduna) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - kaduna)/kaduna) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Katsina")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((katsinaY-katsina), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((katsinaY-katsina), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((katsinaY-katsina), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((katsinaY-katsina), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - katsina)/katsina) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - katsina)/katsina) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - katsina)/katsina) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - katsina)/katsina) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Plateau")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((plateauY-plateau), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((plateauY-plateau), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((plateauY-plateau), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((plateauY-plateau), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - plateau)/plateau) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - plateau)/plateau) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - plateau)/plateau) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - plateau)/plateau) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Zamfara")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((zamfaraY-zamfara), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((zamfaraY-zamfara), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((zamfaraY-zamfara), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((zamfaraY-zamfara), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - zamfara)/zamfara) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - zamfara)/zamfara) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - zamfara)/zamfara) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - zamfara)/zamfara) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Gombe")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((gombeY-gombe), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((gombeY-gombe), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((gombeY-gombe), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((gombeY-gombe), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - gombe)/gombe) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - gombe)/gombe) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - gombe)/gombe) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - gombe)/gombe) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Bauchi")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((bauchiY-bauchi), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((bauchiY-bauchi), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((bauchiY-bauchi), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((bauchiY-bauchi), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - bauchi)/bauchi) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - bauchi)/bauchi) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - bauchi)/bauchi) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - bauchi)/bauchi) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Yobe")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((yobeY-yobe), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((yobeY-yobe), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((yobeY-yobe), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((yobeY-yobe), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - yobe)/yobe) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - yobe)/yobe) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - yobe)/yobe) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - yobe)/yobe) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Borno")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((bornoY-borno), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((bornoY-borno), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((bornoY-borno), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((bornoY-borno), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - borno)/borno) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - borno)/borno) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - borno)/borno) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - borno)/borno) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Jigawa")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((jigawaY-jigawa), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((jigawaY-jigawa), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((jigawaY-jigawa), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((jigawaY-jigawa), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - jigawa)/jigawa) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - jigawa)/jigawa) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - jigawa)/jigawa) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - jigawa)/jigawa) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("F.C.T. Abuja")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((abujaY-abuja), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((abujaY-abuja), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((abujaY-abuja), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((abujaY-abuja), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - abuja)/abuja) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - abuja)/abuja) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - abuja)/abuja) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - abuja)/abuja) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Edo")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((edoY-edo), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((edoY-edo), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((edoY-edo), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((edoY-edo), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - edo)/edo) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - edo)/edo) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - edo)/edo) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - edo)/edo) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Niger")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((nigerY-niger), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((nigerY-niger), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((nigerY-niger), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((nigerY-niger), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - niger)/niger) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - niger)/niger) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - niger)/niger) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - niger)/niger) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Kwara")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((kwaraY-kwara), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((kwaraY-kwara), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((kwaraY-kwara), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((kwaraY-kwara), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - kwara)/kwara) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - kwara)/kwara) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - kwara)/kwara) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - kwara)/kwara) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Oyo")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((oyoY-oyo), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((oyoY-oyo), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((oyoY-oyo), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((oyoY-oyo), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - oyo)/oyo) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - oyo)/oyo) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - oyo)/oyo) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - oyo)/oyo) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Ekiti")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((ekitiY-ekiti), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((ekitiY-ekiti), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((ekitiY-ekiti), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((ekitiY-ekiti), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - ekiti)/ekiti) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - ekiti)/ekiti) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - ekiti)/ekiti) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - ekiti)/ekiti) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Ogun")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((ogunY-ogun), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((ogunY-ogun), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((ogunY-ogun), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((ogunY-ogun), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - ogun)/ogun) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - ogun)/ogun) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - ogun)/ogun) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - ogun)/ogun) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Ondo")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((ondoY-ondo), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((ondoY-ondo), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((ondoY-ondo), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((ondoY-ondo), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - ondo)/ondo) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - ondo)/ondo) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - ondo)/ondo) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - ondo)/ondo) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Kebbi")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((kebbiY-kebbi), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((kebbiY-kebbi), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((kebbiY-kebbi), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((kebbiY-kebbi), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - kebbi)/kebbi) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - kebbi)/kebbi) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - kebbi)/kebbi) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - kebbi)/kebbi) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Sokoto")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((sokotoY-sokoto), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((sokotoY-sokoto), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((sokotoY-sokoto), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((sokotoY-sokoto), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - sokoto)/sokoto) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - sokoto)/sokoto) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - sokoto)/sokoto) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - sokoto)/sokoto) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Osun")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((osunY-osun), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((osunY-osun), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((osunY-osun), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((osunY-osun), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - osun)/osun) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - osun)/osun) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - osun)/osun) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - osun)/osun) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Kano")){

            percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((kanoY-kano), 2)));
            percent1 = (int) Math.sqrt(Math.pow((kadunaY-kadunaY), 2) + (Math.pow((kanoY-kano), 2)));
            percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((kanoY-kano), 2)));
            percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((kanoY-kano), 2)));
            if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
                percent = (int) Math.abs(((percent0 - kano)/kano) * 100);
            }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
                percent = (int) Math.abs(((percent1 - kano)/kano) * 100);
            }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
                percent = (int) Math.abs(((percent2 - kano)/kano) * 100);
            }else{
                percent = (int) Math.abs(((percent3 - kano)/kano) * 100);
            }
            anotherState.setText(String.valueOf(percent));

        }else if (stateSelected.equals("Select State")){
                Toast.makeText(getApplicationContext(), "Please select a state", Toast.LENGTH_SHORT).show();
        }else{
            anotherState.setText("0");
        }


//        int percentvalue = Integer.parseInt(anotherState.getText().toString());
//
//        if (percentvalue < 50){
//            predictionextra.setText("A percentage safety score less than " + percentvalue +
//                    "% is not good enough. Be aware of your immediate environment. Be watchful, " +
//                    "and report any suspicious movements or persons");
//        }else if (percentvalue >= 50 && percentvalue <= 100){
//            predictionextra.setText("A percentage safet score of " + percentvalue + "% is okay." +
//                    "Just be careful and take care of your personal safety");
//        }else{
//            predictionextra.setText("You are comfortably safe!");
//        }

        if (percent > 60){
            predictionextra.setText(R.string.safe_advice);
        }else if (percent > 40 && percent < 60){
            predictionextra.setText(R.string.maybe_safe_advice);
        }else {
            predictionextra.setText(R.string.unsafe_advice);
        }

    }

    public void undefinedState(){
        double delta = 5.890427265;double deltaY = 5.680004434;
        double kaduna = 11.0799813;double kadunaY = 7.710009724;
        double niger = 10.4003587;double nigerY = 5.469939737;
        double benue = 7.190399596;double benueY = 8.129984089;

        int percent, percent0, percent1, percent2, percent3;

        double longt = Double.parseDouble(longitude.getText().toString());
        double lat = Double.parseDouble(latitude.getText().toString());

        percent0 = (int) Math.sqrt(Math.pow((deltaY-delta), 2) + (Math.pow((longt-lat), 2)));
        percent1 = (int) Math.sqrt(Math.pow((kadunaY-kaduna), 2) + (Math.pow((longt-lat), 2)));
        percent2 = (int) Math.sqrt(Math.pow((benueY-benue), 2) + (Math.pow((longt-lat), 2)));
        percent3 = (int) Math.sqrt(Math.pow((nigerY-niger), 2) + (Math.pow((longt-lat), 2)));
        if (percent0 > percent1 && percent0 > percent2 && percent0 > percent3){
            percent = (int) Math.abs(((percent0 - lat)/lat) * 100);
        }else if (percent1 > percent0 && percent1 > percent2 && percent1 > percent3){
            percent = (int) Math.abs(((percent1 - lat)/lat) * 100);
        }else if (percent2 > percent0 && percent2 > percent1 && percent2 > percent3){
            percent = (int) Math.abs(((percent2 - lat)/lat) * 100);
        }else{
            percent = (int) Math.abs(((percent3 - lat)/lat) * 100);
        }

        anotherState.setText(String.valueOf(percent));
        if (percent > 60){
            predictionextra.setText(R.string.safe_advice);
        }else if (percent > 40 && percent < 60){
            predictionextra.setText(R.string.maybe_safe_advice);
        }else {
            predictionextra.setText(R.string.unsafe_advice);
        }
    }
}
