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

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.List;

import ar.edu.uca.ingenieria.enviar_notificaciones.config.WebServiceModule;
import ar.edu.uca.ingenieria.enviar_notificaciones.exception.NotificationException;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.MessageManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.NotificationCallback;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.SubscriptionListManager;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.mock.MessageManagerMockImpl;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.Message;
import ar.edu.uca.ingenieria.enviar_notificaciones.model.SubscriptionList;
import ar.edu.uca.ingenieria.enviar_notificaciones.manager.mock.SubscriptionListManagerMockImpl;


public class MainActivity extends ActionBarActivity {

    // TODO move to string files
    private static final String ERROR_FETCHING_LISTS = "Error al leer las listas de envío del backend: ";
    private static final String ERROR_SENDING_MSG = "Error al enviar el mensaje: ";
    private static final String MESSAGE_SENT_OK = "El mensaje se envió exitosamente: ";
    private SubscriptionListManager subscriptionListManager; //= new SubscriptionListManagerMockImpl();
    private MessageManager messageManager; //= new MessageManagerMockImpl();
    private EditText notificationTitle;
    private EditText notificationMessage;
    private SubscriptionList selectedSubscriptionList;
    private SubscriptionList[] subscriptionLists;       // The adapter needs an Array, not a List...

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectManagers();
        loadSubscriptionLists();
        setUpSendButton();
        setUpTitleAndMessage();
    }

    private void setUpTitleAndMessage() {
        notificationTitle = (EditText) findViewById(R.id.notification_title);
        notificationMessage = (EditText) findViewById(R.id.notification_message);
    }

    private void setUpSendButton() {
        Button button = (Button) findViewById(R.id.send_button);
        button.setOnClickListener(new SendButtonOnClickListener());
    }

    private void setUpSubscriptionListSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.subscription_list_spinner);
        ArrayAdapter<SubscriptionList> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, subscriptionLists);
        // Using a simple layout for the Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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

    private void loadSubscriptionLists() {

        NotificationCallback<List<SubscriptionList>> subscriptionListCallback = new NotificationCallback<List<SubscriptionList>>() {
            @Override
            public void onSuccess(List<SubscriptionList> result) {
                // Convert to array
                MainActivity.this.subscriptionLists = result.toArray(new SubscriptionList[result.size()]);
                // TODO pass the SL as a parameter instead
                MainActivity.this.setUpSubscriptionListSpinner();
            }

            @Override
            public void onFailure(NotificationException ex) {
                Toast.makeText(MainActivity.this, ERROR_FETCHING_LISTS + ex.getCause() + " - " + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                // TODO pass an empty array to the spinner set up here
                MainActivity.this.subscriptionLists = new SubscriptionList[0];
            }
        };
        this.subscriptionListManager.getSubscriptionLists(subscriptionListCallback);

        //new SubscriptionListManagerMockImpl().getSubscriptionLists(subscriptionListCallback);

    }

    private void sendMessage(Message message) {
        this.messageManager.sendMessage(message, new NotificationCallback<Message>() {
            @Override
            public void onSuccess(Message result) {
                Toast.makeText(MainActivity.this, MESSAGE_SENT_OK + result.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(NotificationException ex) {
                Toast.makeText(MainActivity.this, ERROR_SENDING_MSG + ex.getCause() + " - " + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
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

    private void injectManagers() {
        Injector injector = Guice.createInjector(new WebServiceModule());
        this.subscriptionListManager = injector.getInstance(SubscriptionListManager.class);
        this.messageManager = injector.getInstance(MessageManager.class);
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
