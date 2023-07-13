package com.transmitsecurity.drsandroidsdk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.transmit.accountprotection.TSAccountProtection;
import com.transmit.accountprotection.ITransmitSecurityTriggerActionEventCallback;
import com.transmit.accountprotection.TransmitSecurityTriggerActionResponse;
import com.transmit.accountprotection.api.ActionEventOptions;
import com.transmit.accountprotection.errors.TransmitSecurityAccountProtectionError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userIDET;
    private Spinner spinner;
    private Button setBtn;
    private Button clearBtn;
    private Button actionBtn;
    private String userId ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setOnClicks();
    }

    private void initViews() {
        spinner = findViewById(R.id.planets_spinner);
        userIDET = findViewById(R.id.simpleEditText);
        setBtn = findViewById(R.id.set_user_btn);
        clearBtn = findViewById(R.id.clear_user_button);
        actionBtn = findViewById(R.id.action_button);
    }

    private void setOnClicks() {
        setBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        actionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        userId = ((EditText) findViewById(R.id.simpleEditText)).getText().toString();
        switch (v != null ? v.getId() : 0) {
            case R.id.set_user_btn:
                setUserId(userId);
                break;
            case R.id.clear_user_button:
                clearUserId();
                break;
            case R.id.action_button:
                triggerActionEvent();
                break;
        }
    }

    private void setUserId(String userId) {
        if (userId.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.simpleEditText),
                    getString(R.string.main_illegal_userid), Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            TSAccountProtection.setUserID(userId);
        }
    }

    private void clearUserId() {
        ((EditText) findViewById(R.id.simpleEditText)).setText(null);
        TSAccountProtection.clearUser();
    }


    private void triggerActionEvent() {
        String action = spinner.getSelectedItem().toString();
        TSAccountProtection.triggerAction(action, new ActionEventOptions() {
            @Override
            public String getClaimUserId() {
                return "ClaimUserId";
            }

            @Override
            public String getCorrelationId() {
                return "CorrelationId";
            }

            @Override
            public String getReferenceUserId() {
                return "ReferenceUserId";
            }
        }, new ITransmitSecurityTriggerActionEventCallback() {
            @Override
            public void onResponse(TransmitSecurityTriggerActionResponse transmitSecurityTriggerActionResponse) {
                String token = transmitSecurityTriggerActionResponse.token();
                ((TextView) findViewById(R.id.output)).setText(token);
            }

            @Override
            public void onFailed(TransmitSecurityAccountProtectionError transmitSecurityAccountProtectionError) {
                String error = transmitSecurityAccountProtectionError.getErrorMessage();
                ((TextView) findViewById(R.id.output)).setText(error);
            }
        });
    }


    }


