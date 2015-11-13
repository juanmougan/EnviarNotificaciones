package ar.edu.uca.ingenieria.enviar_notificaciones;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;
import ar.edu.uca.ingenieria.enviar_notificaciones.processor.SubscriptionListProcessor;
import ar.edu.uca.ingenieria.enviar_notificaciones.processor.SubscriptionListProcessorMockImpl;


public class MainActivity extends ActionBarActivity {

    // TODO inject this
    // TODO 2: do I need a processor or just a service?
    private SubscriptionListProcessor subscriptionListProcessor =
            new SubscriptionListProcessorMockImpl();
    private Spinner spinner;
    private Button button;
    private EditText notificationTitle;
    private EditText notificationMessage;
    private SubscriptionList selectedSubscriptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSubscriptionListSpinner();
        setUpSendButton();
        setUpTitleAndMessage();
    }

    private void setUpTitleAndMessage() {
        notificationTitle = (EditText) findViewById(R.id.notification_title);
        notificationMessage = (EditText) findViewById(R.id.notification_message);
    }

    private void setUpSendButton() {
        button = (Button) findViewById(R.id.send_button);
        button.setOnClickListener(new SendButtonOnClickListener());
    }

    private void setUpSubscriptionListSpinner() {
        spinner = (Spinner) findViewById(R.id.subscription_list_spinner);
        loadSubscriptionListSpinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubscriptionList = (SubscriptionList) parent.getItemAtPosition(position);
                Log.d("MainActivity listener", "Selected: " + selectedSubscriptionList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("MainActivity listener", "Nothing selected");
            }
        });
    }

    private void loadSubscriptionListSpinner() {
        SubscriptionList[] subscriptionLists = this.subscriptionListProcessor
                .getSubscriptionListNames();
        ArrayAdapter<SubscriptionList> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, subscriptionLists);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    private void sendMessage(Message message) {
        // TODO hit an async service here...
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show();
    }

    private class SendButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Message message = new Message(MainActivity.this.notificationTitle.getText().toString(),
                    MainActivity.this.notificationMessage.getText().toString(),
                    MainActivity.this.selectedSubscriptionList);
            Log.d("MainActivityBtnClick", "About to send: " + message.toString());
            MainActivity.this.sendMessage(message);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
