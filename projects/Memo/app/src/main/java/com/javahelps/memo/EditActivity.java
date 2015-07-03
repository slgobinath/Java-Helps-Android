package com.javahelps.memo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.javahelps.memo.db.DatabaseAccess;


public class EditActivity extends ActionBarActivity {
    private EditText etText;
    private Button btnSave;
    private Button btnCancel;
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.etText = (EditText) findViewById(R.id.etText);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            memo = (Memo) bundle.get("MEMO");
            if(memo != null) {
                this.etText.setText(memo.getText());
            }
        }

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });
    }

    public void onSaveClicked() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        if(memo == null) {
            // Add new memo
            Memo temp = new Memo();
            temp.setText(etText.getText().toString());
            databaseAccess.save(temp);
        } else {
            // Update the memo
            memo.setText(etText.getText().toString());
            databaseAccess.update(memo);
        }
        databaseAccess.close();
        this.finish();
    }

    public void onCancelClicked() {
        this.finish();
    }
}
